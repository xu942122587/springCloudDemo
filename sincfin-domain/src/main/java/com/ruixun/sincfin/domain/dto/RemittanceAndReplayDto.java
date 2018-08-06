package com.ruixun.sincfin.domain.dto;

import java.util.Date;

/**
* @ClassName: RemittanceAndReplayDto
* @Description: 打款回款详情
* @author hjj
* @date 2018年7月19日
*
*/
public class RemittanceAndReplayDto {

	/**
	* @Fields 资产名称
	*/
	private String productTitle;	
	/**
	* @Fields 产品期限(天)
	*/
	private Integer timeLimit;
	/**
	* @Fields 总利率
	*/
	private double totalInterestRate;
	/**
	* @Fields 总金额
	*/
	private double totalAmount;
	/**
	* @Fields 销售金额
	*/
	private double orderAmount;
	/**
	* @Fields 用户利息
	*/
	private double userInterest;
	/**
	* @Fields 用户本息
	*/
	private double userPrincipalAndInterest;
	/**
	* @Fields 结标时间
	*/
	private Date gmtSellOut;
	/**
	* @Fields 融资方
	*/
	private String financiers;
	/**
	* @Fields 打款日期
	*/
	private Date remittanceTime;
	/**
	* @Fields 回款利率
	*/
	private double replayInterestRate;
	/**
	* @Fields 回款本金
	*/
	private double replayPrincipal;
	/**
	* @Fields 回款利息
	*/
	private double replayInterest;
	/**
	* @Fields 回款总额
	*/
	private double replayAmount;
	/**
	* @Fields 回款时间
	*/
	private Date replayTime;
	/**
	* @Fields 状态
	*/
	private String stateMsg; 
	/**
	* @Fields 操作
	*/
	private String operation;
	/**
	* @Fields 融资用户银行名称
	*/
	private String bankName;
	/**
	* @Fields 融资用户银行卡号
	*/
	private String bankNo;
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public double getTotalInterestRate() {
		return totalInterestRate;
	}
	public void setTotalInterestRate(double totalInterestRate) {
		this.totalInterestRate = totalInterestRate;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getUserInterest() {
		return userInterest;
	}
	public void setUserInterest(double userInterest) {
		this.userInterest = userInterest;
	}
	public double getUserPrincipalAndInterest() {
		return userPrincipalAndInterest;
	}
	public void setUserPrincipalAndInterest(double userPrincipalAndInterest) {
		this.userPrincipalAndInterest = userPrincipalAndInterest;
	}
	public Date getGmtSellOut() {
		return gmtSellOut;
	}
	public void setGmtSellOut(Date gmtSellOut) {
		this.gmtSellOut = gmtSellOut;
	}
	public String getFinanciers() {
		return financiers;
	}
	public void setFinanciers(String financiers) {
		this.financiers = financiers;
	}
	public Date getRemittanceTime() {
		return remittanceTime;
	}
	public void setRemittanceTime(Date remittanceTime) {
		this.remittanceTime = remittanceTime;
	}
	public double getReplayInterestRate() {
		return replayInterestRate;
	}
	public void setReplayInterestRate(double replayInterestRate) {
		this.replayInterestRate = replayInterestRate;
	}
	public double getReplayPrincipal() {
		return replayPrincipal;
	}
	public void setReplayPrincipal(double replayPrincipal) {
		this.replayPrincipal = replayPrincipal;
	}
	public double getReplayInterest() {
		return replayInterest;
	}
	public void setReplayInterest(double replayInterest) {
		this.replayInterest = replayInterest;
	}
	public double getReplayAmount() {
		return replayAmount;
	}
	public void setReplayAmount(double replayAmount) {
		this.replayAmount = replayAmount;
	}
	public Date getReplayTime() {
		return replayTime;
	}
	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}
	public String getStateMsg() {
		return stateMsg;
	}
	public void setStateMsg(String stateMsg) {
		this.stateMsg = stateMsg;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
}
