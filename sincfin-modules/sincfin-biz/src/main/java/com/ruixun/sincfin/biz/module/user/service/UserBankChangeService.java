package com.ruixun.sincfin.biz.module.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.UserBankChangeClient;
import com.ruixun.sincfin.biz.module.file.service.FileObjectService;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankChangeMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserMapper;
import com.ruixun.sincfin.biz.pay.fuiou.FuiouPayHelper;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayRequest;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayResponse;
import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.client.message.PushMessageClient;
import com.ruixun.sincfin.common.MessageContent;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.UserBankChangeDto;
import com.ruixun.sincfin.domain.entity.UserBankChangeEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.entity.UserEntity;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.enums.UserBankChangeStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.UserBankChangeQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tiea on 2018/6/4.
 */
@RestController
@RequestMapping("userBankChange")
public class UserBankChangeService implements UserBankChangeClient {

    private Logger logger = LoggerFactory.getLogger(UserBankChangeService.class);

    @Resource
    private UserBankChangeMapper userBankChangeMapper;
    @Resource
    private UserBankMapper userBankMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private FileObjectService fileObjectService;
    @Resource
    private FuiouPayHelper fuiouPayHelper;

    @Autowired
    private PushMessageClient messageClient;

    @RequestMapping("listPageByCondition")
    public Pagination<UserBankChangeDto> listPageByCondition(@RequestBody UserBankChangeQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<UserBankChangeDto> userBankChangeDtoList = userBankChangeMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(userBankChangeDtoList);

        return new Pagination(query, userBankChangeDtoList, (int) pageInfo.getTotal());
    }

    @RequestMapping("getById")
    public UserBankChangeDto getById(@RequestParam("id") Long id) {
        UserBankChangeEntity userBankChangeEntity = userBankChangeMapper.selectByPrimaryKey(id);
        if (userBankChangeEntity == null) {
            return null;
        }
        UserBankChangeDto userBankChangeDto = new UserBankChangeDto();
        BeanHelper.copyProperties(userBankChangeDto, userBankChangeEntity);
        UserEntity userEntity = userMapper.selectByPrimaryKey(userBankChangeDto.getUserId());

        userBankChangeDto.setUserRealName(userEntity.getRealName());
        userBankChangeDto.setUserMobile(userEntity.getMobile());
        userBankChangeDto.setUserIdCardNo(userEntity.getIdCardNo());

        if (StringUtils.isNotEmpty(userBankChangeEntity.getIdCardFaceImage())) {
            userBankChangeDto.setIdCardFaceImageUrl(
                    fileObjectService.getPrivateFileUrl(userBankChangeEntity.getIdCardFaceImage()));
        }
        if (StringUtils.isNotEmpty(userBankChangeEntity.getIdCardBackImage())) {
            userBankChangeDto.setIdCardBackImageUrl(
                    fileObjectService.getPrivateFileUrl(userBankChangeEntity.getIdCardBackImage()));
        }

        return userBankChangeDto;
    }


    @RequestMapping("updateAuditPass")
    @Transactional
    public int updateAuditPass(@RequestParam("id") Long id,
                         @RequestParam("auditorId") Long auditorId) {

        UserBankChangeEntity userBankChangeEntity = userBankChangeMapper.selectByPrimaryKey(id);
        checkUserBankChangeAudit(userBankChangeEntity);

        // 将于提现额度有关的字段转移到新卡上
        userBankMapper.updateUserBankChange(userBankChangeEntity.getBeforeUserBankId(),
                userBankChangeEntity.getAfterUserBankId());

        // 解绑需要变更的卡
        int result = userBankMapper.updateChangeUnbind(userBankChangeEntity.getBeforeUserBankId());
        if (result == 0) {
            throw new BizException(ApiStatusEnum.USER_BANK_STATUS_EXCEPTION);
        }
        result = userBankChangeMapper.updateAuditPass(id, auditorId);

        try {
            UserBankEntity entity = userBankMapper.selectByPrimaryKey(userBankChangeEntity.getBeforeUserBankId());
            FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
            fuiouPayRequest.setUserId(entity.getFuiouUserId());
            fuiouPayRequest.setProtocolNo(entity.getFuiouProtocolNo());
            FuiouPayResponse response = fuiouPayHelper.unbindAction(entity.getUserId(), fuiouPayRequest);
            if (!response.getResponseCode().equals("0000")) {
                UserBankEntity updateEntity = new UserBankEntity();
                updateEntity.setId(entity.getId());
                updateEntity.setFuiouCode(response.getResponseCode());
                updateEntity.setFuiouMsg(response.getResponseMsg());
                userBankMapper.updateByPrimaryKeySelective(updateEntity);
            }
        } catch (Exception e) {
            logger.error("fuiou pay unbind error", e);
        }

        try {
            UserEntity userEntity = userMapper.selectByPrimaryKey(userBankChangeEntity.getUserId());
            String content = MessageContent.getUserBankChangePassContent();
            PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
                    Arrays.asList(userEntity.getMobile()), content);
            messageClient.send(message);
        } catch (Exception e) {
            logger.error("user bank change pass msn send fail", e);
        }
        return result;
    }

    @RequestMapping("updateAuditReject")
    @Transactional
    public int updateAuditReject(@RequestParam("id") Long id,
                           @RequestParam("auditorId") Long auditorId,
                           @RequestParam("remark") String remark) {
        UserBankChangeEntity userBankChangeEntity = userBankChangeMapper.selectByPrimaryKey(id);
        checkUserBankChangeAudit(userBankChangeEntity);
        // 拒绝变更，将之前解绑中改为绑定
        int result = userBankMapper.updateChangeBind(userBankChangeEntity.getBeforeUserBankId());
        if (result == 0) {
            throw new BizException(ApiStatusEnum.USER_BANK_STATUS_EXCEPTION);
        }
        return userBankChangeMapper.updateAuditReject(id, auditorId, remark);

    }

    private void checkUserBankChangeAudit(UserBankChangeEntity userBankChangeEntity) {
        if (userBankChangeEntity == null) {
            throw new BizException(ApiStatusEnum.USER_BANK_CHANGE_NOT_EXIST);
        }
        if (!userBankChangeEntity.getStatus().equals(
                UserBankChangeStatusEnum.PENDING.getCode())) {
            throw new BizException(ApiStatusEnum.USER_BANK_CHANGE_STATUS_NOT_PENDING);
        }
    }
    
    
    
    @RequestMapping(value="changeCardApply",method = RequestMethod.POST)
	public void changeCardApply(@RequestParam("bankCodeId")Long bankCodeId, @RequestParam("disBankCodeId")Long disBankCodeId, 
			@RequestParam("idCardFace")String idCardFace, @RequestParam("idCardBack")String idCardBack){
    	
    	UserBankEntity userBankEntity = userBankMapper.selectByPrimaryKey(bankCodeId);
    	userBankEntity.setBindStatus(UserBankBindStatusEnum.UNBINDING.getCode());
    	userBankMapper.updateByPrimaryKeySelective(userBankEntity);
    	
    	UserBankEntity disUserBankEntity = userBankMapper.selectByPrimaryKey(disBankCodeId);
    	
    	UserBankChangeEntity userBankChangeEntity = new UserBankChangeEntity();
    	userBankChangeEntity.setUserId(userBankEntity.getUserId());
    	userBankChangeEntity.setBeforeUserBankId(userBankEntity.getId());
    	userBankChangeEntity.setBeforeBankCardNo(userBankEntity.getBankCardNo());
    	userBankChangeEntity.setBeforeBankName(userBankEntity.getBankName());
    	userBankChangeEntity.setAfterUserBankId(disUserBankEntity.getId());
    	userBankChangeEntity.setAfterBankCardNo(disUserBankEntity.getBankCardNo());
    	userBankChangeEntity.setAfterBankName(disUserBankEntity.getBankName());
    	userBankChangeEntity.setIdCardBackImage(idCardBack);
    	userBankChangeEntity.setIdCardFaceImage(idCardFace);
    	userBankChangeEntity.setStatus(UserBankChangeStatusEnum.PENDING.getCode());
    	
    	userBankChangeMapper.insertSelective(userBankChangeEntity);
    }

}
