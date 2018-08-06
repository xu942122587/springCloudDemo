package com.ruixun.sincfin.biz.module.user.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.UserBankClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.basic.mapper.BankMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.pay.fuiou.FuiouPayHelper;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayRequest;
import com.ruixun.sincfin.biz.pay.fuiou.model.FuiouPayResponse;
import com.ruixun.sincfin.common.util.BeanHelper;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.BankEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;
import com.ruixun.sincfin.domain.enums.UserBankBindStatusEnum;
import com.ruixun.sincfin.domain.query.BankQuery;
import com.ruixun.sincfin.domain.query.UserBankQuery;

/**
 * @author t
 * @date 2018/5/23 14:47
 */
@RestController
@RequestMapping("userBank")
public class UserBankService implements UserBankClient {

	private Logger LOGGER = LoggerFactory.getLogger(UserBankService.class);
	
    @Resource
    private UserBankMapper userBankMapper;
    @Resource
    private BankMapper bankMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private FuiouPayHelper fuiouPayHelper;

    /**
     * 添加
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public UserBankDto add(@RequestBody UserBankDto dto) {
        UserBankEntity entity = new UserBankEntity();
        BeanHelper.copyProperties(entity, dto);
        userBankMapper.insertSelective(entity);
        dto.setId(entity.getId());
        return dto;
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public UserBankDto update(@RequestBody UserBankDto dto) {
        UserBankEntity entity = new UserBankEntity();
        BeanHelper.copyProperties(entity, dto);
        userBankMapper.updateByPrimaryKeySelective(entity);
        return dto;
    }

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public UserBankDto getById(@RequestParam Long id) {

        UserBankEntity entity = userBankMapper.selectByPrimaryKey(id);
        if (entity == null) {
            return null;
        }
        UserBankDto dto = new UserBankDto();
        BeanHelper.copyProperties(dto, entity);

        return dto;
    }


    @RequestMapping("listPageByCondition")
    public Pagination<UserBankDto> listPageByCondition(@RequestBody UserBankQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<UserBankDto> userBankDtoList = userBankMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(userBankDtoList);

        return new Pagination(query, userBankDtoList, (int) pageInfo.getTotal());
    }
    
    
    
    
    
    @RequestMapping("listByUser")
    public Pagination<UserBankDto> listByUser(@RequestBody UserBankQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<UserBankEntity> userBankEntityList = userBankMapper.listByUser(query);
        PageInfo pageInfo = new PageInfo(userBankEntityList);

        List<UserBankDto> userBankDtoList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(userBankEntityList)) {
            return new Pagination(query, userBankDtoList, 0);
        }
        // get the bank amount limit by bank code
        List<String> codes = userBankEntityList.stream().map(UserBankEntity::getBankCode).collect(Collectors.toList());

        BankQuery bankQuery = new BankQuery();
        bankQuery.setCodes(codes);
        List<BankDto> bankDtoList = bankMapper.listByCondition(bankQuery);

        Map<String, BankDto> codeMap = bankDtoList.stream().collect(Collectors.toMap(BankDto::getCode, e -> e));

        AccountEntity accountEntity = accountMapper.getByUserId(query.getUserId());
        
        userBankEntityList.forEach(userBankEntity -> {
            UserBankDto userBankDto = new UserBankDto();
            BeanHelper.copyProperties(userBankDto, userBankEntity);
            
            if (codeMap.containsKey(userBankEntity.getBankCode())) {
                BankDto bankDto = codeMap.get(userBankEntity.getBankCode());
                userBankDto.setLimitSingle(bankDto.getLimitSingle());
                userBankDto.setLimitDay(bankDto.getLimitDay());
                userBankDto.setLimitMonth(bankDto.getLimitMonth());
                userBankDto.setIcon(bankDto.getIcon());
            }
            userBankDto.setRealName(StringUtils.realNameEncryption(userBankDto.getRealName()));
            userBankDto.setIdCardNo(StringUtils.cardNoEncryption(userBankDto.getIdCardNo()));
//            Long canWithdrawAmount = SincfinUtils.canWithdrawAmount(userBankDto, accountEntity);
//            if(canWithdrawAmount>0){
            if (userBankEntity.getTotalRecharge() > userBankEntity.getTotalWithdraw()) {
            	userBankDto.setCanUntie(true);
            }else{
            	userBankDto.setCanUntie(false);
            }
            userBankDtoList.add(userBankDto);

            
        });

        return new Pagination(query, userBankDtoList, (int) pageInfo.getTotal());
    }


    /**
     * 查找用户最近使用的一张银行卡
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getRecentUpdate", method = RequestMethod.GET)
    public UserBankDto getRecentUpdate(@RequestParam("userId") Long userId) {
        UserBankEntity entity = userBankMapper.selectRecentUpdate(userId);
        if (entity == null) {
            return null;
        }
        UserBankDto dto = new UserBankDto();
        BeanHelper.copyProperties(dto, entity);
        if(StringUtils.isNotEmpty(dto.getBankCode())){
        	BankEntity bankEntity = bankMapper.getBankByCode(dto.getBankCode());
        	if(bankEntity!=null){
        		dto.setLimitSingle(bankEntity.getLimitSingle());
            	dto.setLimitDay(bankEntity.getLimitDay());
            	dto.setLimitMonth(bankEntity.getLimitMonth());
            	dto.setIcon(bankEntity.getIcon());
        	}
        }
        return dto;
    }


    /**
     * 根据id查找可用的银行卡
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "getAvailable", method = RequestMethod.GET)
    public UserBankDto getAvailable(@RequestParam("id") Long id) {
        UserBankEntity entity = userBankMapper.getAvailable(id);
        if (entity == null) {
            return null;
        }
        UserBankDto dto = new UserBankDto();
        BeanHelper.copyProperties(dto, entity);
        if(StringUtils.isNotEmpty(dto.getBankCode())){
        	BankEntity bankEntity = bankMapper.getBankByCode(dto.getBankCode());
        	if(bankEntity!=null){
        		dto.setLimitSingle(bankEntity.getLimitSingle());
            	dto.setLimitDay(bankEntity.getLimitDay());
            	dto.setLimitMonth(bankEntity.getLimitMonth());
            	dto.setIcon(bankEntity.getIcon());
        	}
        }
        return dto;
    }
    
    
    
    @RequestMapping(value="unbind",method = RequestMethod.POST)
	public void unbind(@RequestParam("id")Long id){
    	UserBankEntity entity = userBankMapper.selectByPrimaryKey(id);
    	if(StringUtils.isEmpty(entity.getFuiouProtocolNo())){
    		entity.setBindStatus(UserBankBindStatusEnum.UNBIND.getCode());
			userBankMapper.updateByPrimaryKeySelective(entity);
    	}else{
    		FuiouPayRequest fuiouPayRequest = new FuiouPayRequest();
        	fuiouPayRequest.setUserId(entity.getFuiouUserId());
        	fuiouPayRequest.setProtocolNo(entity.getFuiouProtocolNo());
        	try {
        		FuiouPayResponse response = fuiouPayHelper.unbindAction(entity.getUserId(), fuiouPayRequest);
    			
        		if (response.getResponseCode().equals("0000")) {
        			entity.setBindStatus(UserBankBindStatusEnum.UNBIND.getCode());
        			userBankMapper.updateByPrimaryKeySelective(entity);
                } else{
                	entity.setFuiouCode(response.getResponseCode());
                	entity.setFuiouMsg(response.getResponseMsg());
                	userBankMapper.updateByPrimaryKeySelective(entity);
                }
    		} catch (Exception e) {
    			LOGGER.error("recharge fail msg : " + e.getMessage());
    			e.printStackTrace();
    		}
    	}
    	
    	
    }

}
