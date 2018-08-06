package com.ruixun.sincfin.domain.dto;

public class StatisticsDataDto {

	//注册用户数
	private Integer regUserCount;
	//注册并投资用户数
	private Integer regUserAndInvCount;
	//新增投资用户数
	private Integer newInvUserCount;
	//充值总额
	private Long rechargeAmount;
	//投资总额
	private Long investmentAmount;
	//新增存量
	private Long newCurrentStock;
	//新手标投资总额
	private Long investmentAmountNewArea;
	//复投总额
	private Long reInvestmentAmount;
	//体现总额
	private Long withdrawAmount;
	//发标总额
	private Long ReleaseAmount;
	//还款总额
	private Long repaymentAmount;
	
	public Integer getRegUserCount() {
		return regUserCount;
	}
	public void setRegUserCount(Integer regUserCount) {
		this.regUserCount = regUserCount;
	}
	public Integer getRegUserAndInvCount() {
		return regUserAndInvCount;
	}
	public void setRegUserAndInvCount(Integer regUserAndInvCount) {
		this.regUserAndInvCount = regUserAndInvCount;
	}
	public Integer getNewInvUserCount() {
		return newInvUserCount;
	}
	public void setNewInvUserCount(Integer newInvUserCount) {
		this.newInvUserCount = newInvUserCount;
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
	public Long getNewCurrentStock() {
		return newCurrentStock;
	}
	public void setNewCurrentStock(Long newCurrentStock) {
		this.newCurrentStock = newCurrentStock;
	}
	public Long getInvestmentAmountNewArea() {
		return investmentAmountNewArea;
	}
	public void setInvestmentAmountNewArea(Long investmentAmountNewArea) {
		this.investmentAmountNewArea = investmentAmountNewArea;
	}
	public Long getReInvestmentAmount() {
		return reInvestmentAmount;
	}
	public void setReInvestmentAmount(Long reInvestmentAmount) {
		this.reInvestmentAmount = reInvestmentAmount;
	}
	public Long getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Long withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public Long getReleaseAmount() {
		return ReleaseAmount;
	}
	public void setReleaseAmount(Long releaseAmount) {
		ReleaseAmount = releaseAmount;
	}
	public Long getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(Long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	
}
