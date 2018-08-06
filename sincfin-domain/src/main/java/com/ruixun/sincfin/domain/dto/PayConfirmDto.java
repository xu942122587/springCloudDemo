package com.ruixun.sincfin.domain.dto;

public class PayConfirmDto {
	
	private long productId;
	
	private long couponUserId;
	
	private long investmentAmount;
	
	private long payAmount;
	
	private long couponPayAmount;

	private long walletPayAmount;
	
	private long bankCodePayAmount;
	
	private boolean needBankCode;
	
	private boolean realNameAuth;
	
	private String realName;
	
	private String idCardNo;
	
	private String rechargeNum;
	
	private UserBankDto userBank;

	public long getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public long getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}

	public long getCouponPayAmount() {
		return couponPayAmount;
	}

	public void setCouponPayAmount(long couponPayAmount) {
		this.couponPayAmount = couponPayAmount;
	}

	public long getWalletPayAmount() {
		return walletPayAmount;
	}

	public void setWalletPayAmount(long walletPayAmount) {
		this.walletPayAmount = walletPayAmount;
	}

	public long getBankCodePayAmount() {
		return bankCodePayAmount;
	}

	public void setBankCodePayAmount(long bankCodePayAmount) {
		this.bankCodePayAmount = bankCodePayAmount;
	}

	public boolean isNeedBankCode() {
		return needBankCode;
	}

	public void setNeedBankCode(boolean needBankCode) {
		this.needBankCode = needBankCode;
	}

	public boolean isRealNameAuth() {
		return realNameAuth;
	}

	public void setRealNameAuth(boolean realNameAuth) {
		this.realNameAuth = realNameAuth;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public UserBankDto getUserBank() {
		return userBank;
	}

	public void setUserBank(UserBankDto userBank) {
		this.userBank = userBank;
	}

	public String getRechargeNum() {
		return rechargeNum;
	}

	public void setRechargeNum(String rechargeNum) {
		this.rechargeNum = rechargeNum;
	}

	public long getCouponUserId() {
		return couponUserId;
	}

	public void setCouponUserId(long couponUserId) {
		this.couponUserId = couponUserId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	
}
