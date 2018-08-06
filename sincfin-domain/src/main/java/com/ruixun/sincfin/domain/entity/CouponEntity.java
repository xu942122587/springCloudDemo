package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class CouponEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private String type;

    private String bonusType;

    private String name;

    private String triggerCondition;

    private String validityPeriodType;

    private Date gmtValidityStart;

    private Date gmtValidityEnd;

    private Integer fixedDays;

    private Long amount;

    private String productTypeList;

    private Long limitPrice;

    private Integer limitDays;

    private String remark;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType == null ? null : bonusType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTriggerCondition() {
        return triggerCondition;
    }

    public void setTriggerCondition(String triggerCondition) {
        this.triggerCondition = triggerCondition == null ? null : triggerCondition.trim();
    }

    public String getValidityPeriodType() {
        return validityPeriodType;
    }

    public void setValidityPeriodType(String validityPeriodType) {
        this.validityPeriodType = validityPeriodType == null ? null : validityPeriodType.trim();
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

    public Integer getFixedDays() {
        return fixedDays;
    }

    public void setFixedDays(Integer fixedDays) {
        this.fixedDays = fixedDays;
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
        this.productTypeList = productTypeList == null ? null : productTypeList.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}