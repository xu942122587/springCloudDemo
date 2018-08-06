package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class FinancingUserRemittanceEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long financingUserId;

    private Long contractId;

    private Long productId;

    private Date gmtExpectedRemittance;

    private Long expectedAmount;

    private Date gmtRemittance;

    private Long amount;

    private String bankSerialNo;

    private String remark;

    private String status;
    /**
     * 状态，no_remittance：未打款，remittanceCheck:打款审核中 ,remittanceCheckYes:打款审核通过,remittanceCheckNo:打款审核未通过,remittance：已打款
     */
    private String outherStatus;

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

    public Date getGmtExpectedRemittance() {
        return gmtExpectedRemittance;
    }

    public void setGmtExpectedRemittance(Date gmtExpectedRemittance) {
        this.gmtExpectedRemittance = gmtExpectedRemittance;
    }

    public Long getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Long expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Date getGmtRemittance() {
        return gmtRemittance;
    }

    public void setGmtRemittance(Date gmtRemittance) {
        this.gmtRemittance = gmtRemittance;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getOutherStatus() {
		return outherStatus;
	}

	public void setOutherStatus(String outherStatus) {
		this.outherStatus = outherStatus;
	}
    
}