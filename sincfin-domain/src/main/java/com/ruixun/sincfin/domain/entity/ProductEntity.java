package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductEntity implements Serializable {
	
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long contractId;

    private String repaymentType;

    private Long financingUserId;

    private String title;

    private Long productTypeId;

    private Long productLabelId;

    private BigDecimal totalInterestRate;
    
    private BigDecimal originalInterestRate;

    private BigDecimal subsidyInterestRate;

    private Integer timeLimit;

    private String valueDateMethod;

    private Long amount;

    private Long soldAmount;

    private Long unsoldAmount;
    
    private Long amountMin;
    
    private Long amountMax;

    private Integer securityLevel;

    private Boolean presellFlag;

    private Date gmtRelease;

    private Date gmtValueDate;

    private Date gmtExpirationDate;

    private Date gmtSellOut;

    private String status;

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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
    }

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getProductLabelId() {
        return productLabelId;
    }

    public void setProductLabelId(Long productLabelId) {
        this.productLabelId = productLabelId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(Long soldAmount) {
        this.soldAmount = soldAmount;
    }

    public Long getUnsoldAmount() {
        return unsoldAmount;
    }

    public void setUnsoldAmount(Long unsoldAmount) {
        this.unsoldAmount = unsoldAmount;
    }

    public Integer getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(Integer securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Boolean getPresellFlag() {
        return presellFlag;
    }

    public void setPresellFlag(Boolean presellFlag) {
        this.presellFlag = presellFlag;
    }

    public Date getGmtRelease() {
        return gmtRelease;
    }

    public void setGmtRelease(Date gmtRelease) {
        this.gmtRelease = gmtRelease;
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

    public Date getGmtSellOut() {
        return gmtSellOut;
    }

    public void setGmtSellOut(Date gmtSellOut) {
        this.gmtSellOut = gmtSellOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public Long getAmountMin() {
		return amountMin;
	}

	public void setAmountMin(Long amountMin) {
		this.amountMin = amountMin;
	}

	public Long getAmountMax() {
		return amountMax;
	}

	public void setAmountMax(Long amountMax) {
		this.amountMax = amountMax;
	}

	public BigDecimal getOriginalInterestRate() {
		return originalInterestRate;
	}

	public void setOriginalInterestRate(BigDecimal originalInterestRate) {
		this.originalInterestRate = originalInterestRate;
	}
}