
package com.ruixun.sincfin.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDto implements java.io.Serializable {

    private static final long serialVersionUID = 8460678572521747651L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long contractId;

    private String contractTitle;

    private Long financingUserId;

    private String title;

    private Long productTypeId;
    
    private String productTypeCode;
    
    private String productTypeName;

    private Long productLabelId;
    
    private String productLabelName;

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

    // 实际售卖金额
    private Long sellOutAmount;

    private String status;

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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
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
        this.title = title;
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
        this.valueDateMethod = valueDateMethod;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getProductLabelName() {
		return productLabelName;
	}

	public void setProductLabelName(String productLabelName) {
		this.productLabelName = productLabelName;
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

	public String getProductTypeCode() {
		return productTypeCode;
	}

	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
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

	public BigDecimal getOriginalInterestRate() {
		return originalInterestRate;
	}

	public void setOriginalInterestRate(BigDecimal originalInterestRate) {
		this.originalInterestRate = originalInterestRate;
	}

    public Date getGmtSellOut() {
        return gmtSellOut;
    }

    public void setGmtSellOut(Date gmtSellOut) {
        this.gmtSellOut = gmtSellOut;
    }

    public Long getSellOutAmount() {
        return sellOutAmount;
    }

    public void setSellOutAmount(Long sellOutAmount) {
        this.sellOutAmount = sellOutAmount;
    }
}

