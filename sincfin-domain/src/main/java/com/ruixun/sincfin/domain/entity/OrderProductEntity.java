package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderProductEntity implements Serializable {
	
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long userId;

    private String orderNum;

    private Long productId;

    private String productTitle;

    private Date gmtValueDate;

    private Date gmtExpirationDate;

    private BigDecimal totalInterestRate;

    private BigDecimal subsidyInterestRate;

    private Integer timeLimit;

    private String valueDateMethod;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Date getGmtValueDate() {
        return gmtValueDate;
    }

    public void setGmtValueDate(Date gmtValueDate) {
        this.gmtValueDate = gmtValueDate;
    }

    public Date getGmtExpirationDate() {
        return gmtExpirationDate;
    }

    public void setGmtExpirationDate(Date gmtExpirationDate) {
        this.gmtExpirationDate = gmtExpirationDate;
    }

    public BigDecimal getTotalInterestRate() {
        return totalInterestRate;
    }

    public void setTotalInterestRate(BigDecimal totalInterestRate) {
        this.totalInterestRate = totalInterestRate;
    }

    public BigDecimal getSubsidyInterestRate() {
        return subsidyInterestRate;
    }

    public void setSubsidyInterestRate(BigDecimal subsidyInterestRate) {
        this.subsidyInterestRate = subsidyInterestRate;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getValueDateMethod() {
        return valueDateMethod;
    }

    public void setValueDateMethod(String valueDateMethod) {
        this.valueDateMethod = valueDateMethod == null ? null : valueDateMethod.trim();
    }
}