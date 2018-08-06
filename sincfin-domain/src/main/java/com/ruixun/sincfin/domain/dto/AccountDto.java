package com.ruixun.sincfin.domain.dto;

import java.util.Date;

public class AccountDto {
	
	private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long userId;

    private Long walletBalance;

    private Long withdrawAmount;

    private Long currentInvestmentAmount;

    private Long waitInterest;
    
    private Long receivedInterest;
    
    private Long couponBonusIncome;

    private Long totalInvestmentAmount;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Long walletBalance) {
		this.walletBalance = walletBalance;
	}

	public Long getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(Long withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public Long getCurrentInvestmentAmount() {
		return currentInvestmentAmount;
	}

	public void setCurrentInvestmentAmount(Long currentInvestmentAmount) {
		this.currentInvestmentAmount = currentInvestmentAmount;
	}

	public Long getWaitInterest() {
		return waitInterest;
	}

	public void setWaitInterest(Long waitInterest) {
		this.waitInterest = waitInterest;
	}

	public Long getTotalInvestmentAmount() {
		return totalInvestmentAmount;
	}

	public void setTotalInvestmentAmount(Long totalInvestmentAmount) {
		this.totalInvestmentAmount = totalInvestmentAmount;
	}

	public Long getReceivedInterest() {
		return receivedInterest;
	}

	public void setReceivedInterest(Long receivedInterest) {
		this.receivedInterest = receivedInterest;
	}
	
	public Long getCouponBonusIncome() {
		return couponBonusIncome;
	}

	public void setCouponBonusIncome(Long couponBonusIncome) {
		this.couponBonusIncome = couponBonusIncome;
	}
    
}
