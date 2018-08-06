package com.ruixun.sincfin.domain.dto;

import java.util.Date;

public class CouponUserDto {
	
	/**
	 * 用户优惠券id
	 */
	private Long id;
	
    private Date gmtCreate;

    private String userRealName;

    private Long userId;
    
    private String userMobile;
    
    /**
	 * 优惠券id
	 */
	private Long couponId;
	/**
	 * 优惠券类型
	 */
	private String type;
	/**
	 * 红包类型
	 */
	private String bonusType;
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 金额
	 */
	private long amount;
	
	/**
	 * 起投门槛
	 */
	private long limitPrice;
	
	/**
	 * 期限门槛
	 */
	private int limitDays;
	/**
	 * 触发条件
	 */
	private String triggerCondition;
	
	/**
	 * 可用产品
	 */
	private String productTypeList;


	private String productTypeListDesc;
	

    private Date gmtValidityStart;

    private Date gmtValidityEnd;

    private Date gmtUse;

    private String status;

    private Long investmentAmount;
    
	

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTriggerCondition() {
		return triggerCondition;
	}

	public void setTriggerCondition(String triggerCondition) {
		this.triggerCondition = triggerCondition;
	}

	public Date getGmtValidityStart() {
		return gmtValidityStart;
	}

	public void setGmtValidityStart(Date gmtValidityStart) {
		this.gmtValidityStart = gmtValidityStart;
	}

	public Date getGmtValidityEnd() {
		return gmtValidityEnd;
	}

	public void setGmtValidityEnd(Date gmtValidityEnd) {
		this.gmtValidityEnd = gmtValidityEnd;
	}

	public Date getGmtUse() {
		return gmtUse;
	}

	public void setGmtUse(Date gmtUse) {
		this.gmtUse = gmtUse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(String productTypeList) {
		this.productTypeList = productTypeList;
	}

	public String getProductTypeListDesc() {
		return productTypeListDesc;
	}

	public void setProductTypeListDesc(String productTypeListDesc) {
		this.productTypeListDesc = productTypeListDesc;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(Long limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Integer getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Integer limitDays) {
		this.limitDays = limitDays;
	}

	public Long getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	
}
