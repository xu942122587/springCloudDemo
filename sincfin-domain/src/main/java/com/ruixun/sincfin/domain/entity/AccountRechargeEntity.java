package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class AccountRechargeEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;
    
    private String rechargeNum;

    private Long userId;

    private Long accountId;

    private Long amount;

    private Long userBankId;

    private String bankName;

    private String bankCardNo;

    private String type;

    private String status;
    
    private String fuiouOrderId;
    
    private String fuiouSignPay;
    
    private String fuiouCode;
    
    private String fuiouMsg;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(Long userBankId) {
        this.userBankId = userBankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
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

	public String getRechargeNum() {
		return rechargeNum;
	}

	public void setRechargeNum(String rechargeNum) {
		this.rechargeNum = rechargeNum;
	}

	public String getFuiouOrderId() {
		return fuiouOrderId;
	}

	public void setFuiouOrderId(String fuiouOrderId) {
		this.fuiouOrderId = fuiouOrderId;
	}

	public String getFuiouSignPay() {
		return fuiouSignPay;
	}

	public void setFuiouSignPay(String fuiouSignPay) {
		this.fuiouSignPay = fuiouSignPay;
	}

	public String getFuiouCode() {
		return fuiouCode;
	}

	public void setFuiouCode(String fuiouCode) {
		this.fuiouCode = fuiouCode;
	}

	public String getFuiouMsg() {
		return fuiouMsg;
	}

	public void setFuiouMsg(String fuiouMsg) {
		this.fuiouMsg = fuiouMsg;
	}
}