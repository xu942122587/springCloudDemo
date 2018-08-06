package com.ruixun.sincfin.mobile.api.vo;

import java.io.Serializable;

import com.ruixun.sincfin.common.util.StringUtils;

public class UserAccountVO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户手机号
	 */
	private String mobile;
	/**
	 * 总资产
	 */
	private long totalAssets;
	/**
	 * 账户余额
	 */
	private long walletBalance;
	/**
	 * 提现中金额
	 */
	private long withdrawAmount;
	/**
	 * 当前投资中金额
	 */
	private long currentInvestmentAmount;
	
	/**
	 * 待收利息
	 */
	private long waitInterest;
	
	/**
	 * 累计收益金额
	 */
	private long totalInvestmentAmount;
	
	/**
	 * 账户总收益
	 */
	private long totalProfit;
	

	public String getMobile() {
		return StringUtils.mobileEncryption(mobile);
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public long getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(long totalAssets) {
		this.totalAssets = totalAssets;
	}

	public long getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(long walletBalance) {
		this.walletBalance = walletBalance;
	}

	public long getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(long withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public long getCurrentInvestmentAmount() {
		return currentInvestmentAmount;
	}

	public void setCurrentInvestmentAmount(long currentInvestmentAmount) {
		this.currentInvestmentAmount = currentInvestmentAmount;
	}

	public long getWaitInterest() {
		return waitInterest;
	}

	public void setWaitInterest(long waitInterest) {
		this.waitInterest = waitInterest;
	}

	public long getTotalInvestmentAmount() {
		return totalInvestmentAmount;
	}

	public void setTotalInvestmentAmount(long totalInvestmentAmount) {
		this.totalInvestmentAmount = totalInvestmentAmount;
	}

	public long getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(long totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	

}
