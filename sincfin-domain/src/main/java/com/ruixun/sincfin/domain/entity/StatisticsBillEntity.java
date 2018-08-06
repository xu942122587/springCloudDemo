package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class StatisticsBillEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long requestWithdrawAmount;

    private Long withdrawAmount;

    private Long rechargeAmount;

    private Long investmentAmount;

    private Long repaymentAmount;

    private Long couponAmount;

    private Long cashBackAmount;

    private Long sellOutAmount;

    private Long balance;

    private Long balanceIncrement;

    private Long currentStock;

    private Date statisticsDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public Long getRequestWithdrawAmount() {
        return requestWithdrawAmount;
    }

    public void setRequestWithdrawAmount(Long requestWithdrawAmount) {
        this.requestWithdrawAmount = requestWithdrawAmount;
    }

    public Long getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(Long withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Long getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Long rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Long getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(Long investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Long getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Long repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Long getCashBackAmount() {
        return cashBackAmount;
    }

    public void setCashBackAmount(Long cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }

    public Long getSellOutAmount() {
        return sellOutAmount;
    }

    public void setSellOutAmount(Long sellOutAmount) {
        this.sellOutAmount = sellOutAmount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBalanceIncrement() {
        return balanceIncrement;
    }

    public void setBalanceIncrement(Long balanceIncrement) {
        this.balanceIncrement = balanceIncrement;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }
}