package com.ruixun.sincfin.mobile.api.vo;

import java.io.Serializable;
import java.util.Date;

import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.enums.ProductTypeEnum;

public class CouponUserVo  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户优惠券id
	 */
	private Long id;
	
	private Date gmtCreate;
	
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
	
	
	private Date gmtValidityStart;
	
	private Date gmtValidityEnd;
	
	private Date gmtUse;
	
	private String status;
	
	/**
     * 是否可用
     */
    private boolean enable;
	/**
	 * 描述
	 */
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getProductTypeList() {
		return productTypeList;
	}
	public void setProductTypeList(String productTypeList) {
		this.productTypeList = productTypeList;
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
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	/**
	 * 是否即将过期
	 * @return
	 */
	public boolean isSoonExpire(){
		if(this.getGmtValidityEnd()!=null && (this.getGmtValidityEnd().getTime()-new Date().getTime())<1000*60*60*24*3){
			return true;
		}
		return false;
	}


	public static String parseType(String productTypeList){
		if(StringUtils.isEmpty(productTypeList)){
			return null;
		}
		String [] typeCodes = productTypeList.split(",");
		StringBuffer types = new StringBuffer();
		ProductTypeEnum typeEnum = null;
		for(String code : typeCodes){
			typeEnum = ProductTypeEnum.fromCode(code);
			if(typeEnum!=null){
				types.append(",").append(typeEnum.getText());
			}
		}
		String strType = types.toString();
		if(StringUtils.isNotEmpty(strType)){
			strType = strType.substring(1,strType.length());
		}
		return strType;
	}
	

}
