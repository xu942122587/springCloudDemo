package com.ruixun.sincfin.biz.module.account.mapper;

import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.query.AccountQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
	
    int insert(AccountEntity record);

    int insertSelective(AccountEntity record);

    AccountEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountEntity record);

    int updateByPrimaryKey(AccountEntity record);

    List<AccountEntity> listByCondition(AccountQuery query);

    AccountEntity getByUserId(Long userId);

    int updateWithdrawAmount(@Param("userId") Long userId,
                             @Param("amount") Long amount);

    int updateWalletBalance(@Param("userId") Long userId,
                             @Param("amount") Long amount);

    /**
     * 本息方式还款
     * 1、wallet_balance(钱包余额) + amount
     * 2、current_investment_amount(在投金额) - principal
     * 3、wait_interest(待收收益) - interest
     * 3、received_interest(已收收益) + interest
     * @param userId
     * @param amount 还款金额 = (还款本金 + 还款利息)
     * @param principal 还款本金
     * @param interest 还款利息
     * @return
     */
    int updateRepayment(@Param("userId") Long userId, @Param("amount") Long amount,
                        @Param("principal") Long principal, @Param("interest") Long interest);
    /**
     * 用户投资变更账户信息
     * @param paymentAmount 支付金额
     * @param amount 投资金额
     * @param interest 投资的代收利息
     * @param bonusAmount 投资使用的红包金额
     * @return
     */
	int updateAmountFromInvestment(@Param("userId") Long userId , @Param("paymentAmount")Long paymentAmount, @Param("amount")Long amount, @Param("interest")Long interest, @Param("bonusAmount")Long bonusAmount);
}