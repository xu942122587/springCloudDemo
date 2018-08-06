package com.ruixun.sincfin.biz.module.account.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.AccountWithdrawClient;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountMoneyRecordMapper;
import com.ruixun.sincfin.biz.module.account.mapper.AccountWithdrawMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserBankMapper;
import com.ruixun.sincfin.biz.module.user.service.UserBankChangeService;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.AccountMoneyRecordEntity;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.enums.AccountWithdrawStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.query.AccountWithdrawQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tiea on 2018/5/25.
 */
@RestController
@RequestMapping("accountWithdraw")
public class AccountWithdrawService implements AccountWithdrawClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private AccountWithdrawMapper accountWithdrawMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private UserBankMapper userBankMapper;
    @Resource
    private AccountMoneyRecordMapper accountMoneyRecordMapper;

    @RequestMapping("listPageByCondition")
    public Pagination<AccountWithdrawDto> listPageByCondition(@RequestBody AccountWithdrawQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AccountWithdrawDto> accountWithdrawDtoList = accountWithdrawMapper.listByCondition(query);
        PageInfo pageInfo = new PageInfo(accountWithdrawDtoList);

        return new Pagination(query, accountWithdrawDtoList, (int) pageInfo.getTotal());

    }


    /**
     * 管理平台 提现列表
     * @param query
     * @return
     */
    @RequestMapping("listManagerPage")
    public Pagination<AccountWithdrawDto> listManagerPage(@RequestBody AccountWithdrawQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<AccountWithdrawDto> accountWithdrawDtoList = accountWithdrawMapper.listManagerByCondition(query);
        PageInfo pageInfo = new PageInfo(accountWithdrawDtoList);

        return new Pagination(query, accountWithdrawDtoList, (int) pageInfo.getTotal());

    }

    @RequestMapping("auditPass")
    @Transactional
    public int updateAuditPass(@RequestParam("idList") List<Long> idList,
                         @RequestParam("auditorId") Long auditorId) {

        checkAuditStatus(listByIdList(idList), AccountWithdrawStatusEnum.PENDING.getCode());
        return accountWithdrawMapper.updatePass(idList, auditorId);
    }

    @RequestMapping("auditReject")
    @Transactional
    public int updateAuditReject(@RequestParam("idList") List<Long> idList,
                           @RequestParam("auditorId") Long auditorId) {
        List<AccountWithdrawDto> accountWithdrawDtoList = listByIdList(idList);
        checkAuditStatus(accountWithdrawDtoList, AccountWithdrawStatusEnum.PENDING.getCode());

        // todo 批量处理优化
        accountWithdrawDtoList.forEach( dto -> {
            userBankMapper.updateTotalWithdraw(dto.getUserBankId(), - dto.getAmount());
            accountMapper.updateWalletBalance(dto.getUserId(), dto.getAmount());
            accountMapper.updateWithdrawAmount(dto.getUserId(), - dto.getAmount());
        });

        return accountWithdrawMapper.updateReject(idList, auditorId);
    }

    @RequestMapping("auditSuccess")
    @Transactional
    public int updateAuditSuccess(@RequestParam("idList") List<Long> idList,
                            @RequestParam("auditorId") Long auditorId) {

        List<AccountWithdrawDto> accountWithdrawDtoList = listByIdList(idList);
        checkAuditStatus(accountWithdrawDtoList, AccountWithdrawStatusEnum.PASS.getCode());

        // todo 批量处理优化
        accountWithdrawDtoList.forEach( dto -> {
            long amount = dto.getAmount();
            Long userId = dto.getUserId();
            AccountEntity accountEntity = accountMapper.getByUserId(userId);
            logger.info("提现金额：{}	当前账户{}",amount,JSON.toJSONString(accountEntity));
            AccountMoneyRecordEntity accountMoneyRecordEntity = new AccountMoneyRecordEntity();
            accountMoneyRecordEntity.setUserId(userId);
            accountMoneyRecordEntity.setAccountId(dto.getAccountId());
            accountMoneyRecordEntity.setType(AccountMoneyRecordTypeEnum.WITHDRAW.getCode());
            accountMoneyRecordEntity.setObjectId(dto.getId() + "");
            accountMoneyRecordEntity.setTitle(AccountMoneyRecordTypeEnum.WITHDRAW.getText());
            accountMoneyRecordEntity.setAmount(amount);
            accountMoneyRecordEntity.setBalance(accountEntity.getWalletBalance());
            int i=accountMapper.updateWithdrawAmount(userId, - amount);
            if(i==1) {
            	accountMoneyRecordMapper.insertSelective(accountMoneyRecordEntity);
            }
        });
        return accountWithdrawMapper.updateSuccess(idList, auditorId);
    }


    private List<AccountWithdrawDto> listByIdList(List<Long> idList) {
        AccountWithdrawQuery query = new AccountWithdrawQuery();
        query.setIdList(idList);
        return accountWithdrawMapper.listByCondition(query);
    }

    private void checkAuditStatus(List<AccountWithdrawDto> accountWithdrawDtoList, String expectStatus) {
        accountWithdrawDtoList.forEach( dto -> {
            if (StringUtils.isEmpty(dto.getStatus())
                    || !expectStatus.equals(dto.getStatus())) {
                throw new BizException(ApiStatusEnum.ACCOUNT_WITHDRAW_STATUS_ERROR);
            }
        });
    }


}
