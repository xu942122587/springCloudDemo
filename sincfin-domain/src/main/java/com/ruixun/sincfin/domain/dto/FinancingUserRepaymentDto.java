package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class FinancingUserRepaymentDto implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Long financingUserId;

    private Long contractId;

    private Long productId;

    private Date gmtExpectedRepayment;

    private Long expectedAmount;

    private Date gmtRepayment;

    private Long amount;

    private Long principal;

    private String bankSerialNo;

    private String remark;

    private String type;

    private String status;

    /**
     * 状态，no_repayment：未回款，repayment：已回款
     */
    private String outherStatus;
    
    private String productTitle;

    private String contractTitle;

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

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getGmtExpectedRepayment() {
        return gmtExpectedRepayment;
    }

    public void setGmtExpectedRepayment(Date gmtExpectedRepayment) {
        this.gmtExpectedRepayment = gmtExpectedRepayment;
    }

    public Long getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Long expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Date getGmtRepayment() {
        return gmtRepayment;
    }

    public void setGmtRepayment(Date gmtRepayment) {
        this.gmtRepayment = gmtRepayment;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPrincipal() {
        return principal;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo == null ? null : bankSerialNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle == null ? null : contractTitle.trim();
    }

	public String getOutherStatus() {
		return outherStatus;
	}

	public void setOutherStatus(String outherStatus) {
		this.outherStatus = outherStatus;
	}

}