package com.ruixun.sincfin.common.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.datetime.joda.MillisecondInstantPrinter;

import com.ruixun.sincfin.domain.dto.AccountDto;
import com.ruixun.sincfin.domain.dto.UserBankDto;
import com.ruixun.sincfin.domain.entity.AccountEntity;
import com.ruixun.sincfin.domain.entity.UserBankEntity;

public class SincfinUtils {
	
	/**
     * 计算预计到账日期，（提现功能的提示信息）
     * @param date
     * @return
     */
    public static Date accountingDate(Date date){
        int week = Integer.parseInt(DateUtils.getWeekOfDate(date));
        Calendar calendar = Calendar.getInstance();
        Calendar paymentDate = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            paymentDate.setTime(date);
        }
        //周一-周五  0:00-15:00发起提现                    显示时间为当天18:00
        //周一-周四  15:00-23:59:59发起提现             显示时间为次日18:00
        //周五15:00-23:59:59及周六周日发起提现  显示时间为次周一18:00
        paymentDate.set(Calendar.HOUR_OF_DAY, 18);
        paymentDate.set(Calendar.MINUTE, 0);
        paymentDate.set(Calendar.SECOND, 0);
        paymentDate.set(Calendar.MILLISECOND, 0);
        if(week >=1 && week <=4){
            if(calendar.get(Calendar.HOUR_OF_DAY) >= 15){
                paymentDate.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
            }
        }else{
            if(!(calendar.get(Calendar.HOUR_OF_DAY) < 15 && week == 5)){
                if(week ==0){
                    paymentDate.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
                }else{
                    paymentDate.set(Calendar.DATE, calendar.get(Calendar.DATE)+(8-week));
                }
            }
        }
        return paymentDate.getTime();
    }
    
    /**
     * 银行卡可提现额度
     * @param userBank
     * @param accountDto
     * @return
     */
    public static Long canWithdrawAmount(UserBankDto userBank,AccountDto accountDto){
    	//确定可提现金额
        Long canWithdrawAmount = 0L;
        if(accountDto.getWalletBalance()!=0){
        	/**
        	 * 如果余额不等于零，计算该卡的提现额度
        	 * 可提现额度 = 银行卡总充值-总提现+账号的已收利息+账号优惠券收益
        	 */
        	canWithdrawAmount = userBank.getTotalRecharge()-userBank.getTotalWithdraw()+accountDto.getReceivedInterest()+accountDto.getCouponBonusIncome();
        	if(canWithdrawAmount>accountDto.getWalletBalance()){
        		canWithdrawAmount = accountDto.getWalletBalance();
        	}
        }
        if(canWithdrawAmount<0L){
        	canWithdrawAmount = 0L;
        }
        return canWithdrawAmount;
    }
    
    
    
    /**
     * 银行卡可提现额度
     * @param userBank
     * @param accountDto
     * @return
     */
    public static Long canWithdrawAmount(UserBankDto userBank,AccountEntity accountEntity){
    	//确定可提现金额
        Long canWithdrawAmount = 0L;
        if(accountEntity.getWalletBalance()>0){
        	/**
        	 * 如果余额不等于零，计算该卡的提现额度
        	 * 可提现额度 = 银行卡总充值-总提现+账号的已收利息+账号优惠券收益
        	 */
        	canWithdrawAmount = userBank.getTotalRecharge()-userBank.getTotalWithdraw()+accountEntity.getReceivedInterest()+accountEntity.getCouponBonusIncome();
        	if(canWithdrawAmount>accountEntity.getWalletBalance()){
        		canWithdrawAmount = accountEntity.getWalletBalance();
        	}
        }
        return canWithdrawAmount;
    }
    
    /**
     * 银行卡可提现额度
     * @param userBank
     * @param accountDto
     * @return
     */
    public static Long canWithdrawAmount(UserBankEntity userBank,AccountDto accountDto){
    	//确定可提现金额
        Long canWithdrawAmount = 0L;
        if(accountDto.getWalletBalance()!=0){
        	/**
        	 * 如果余额不等于零，计算该卡的提现额度
        	 * 可提现额度 = 银行卡总充值-总提现+账号的已收利息+账号优惠券收益
        	 */
        	canWithdrawAmount = userBank.getTotalRecharge()-userBank.getTotalWithdraw()+accountDto.getReceivedInterest()+accountDto.getCouponBonusIncome();
        	if(canWithdrawAmount>accountDto.getWalletBalance()){
        		canWithdrawAmount = accountDto.getWalletBalance();
        	}
        }
        return canWithdrawAmount;
    }
    
    
    /**
     * 计算利息
     * @param interest 总利率
     * @param timeLimit 期限
     * @param amount 金额
     * @return
     */
    public static Long calcInterest(BigDecimal interest,Integer timeLimit,Long amount){
    	BigDecimal bAmount = new BigDecimal(amount);
    	BigDecimal bTimeLimit = new BigDecimal(timeLimit);
    	BigDecimal yearInterest = interest.multiply(bAmount).multiply(bTimeLimit);
    	return yearInterest.divide(new BigDecimal("365"),0,BigDecimal.ROUND_HALF_DOWN).longValue();
    }
}
