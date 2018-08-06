package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class UserBankEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long userId;

    private String bankCardNo;

    private String idCardNo;

    private String mobile;

    private String realName;

    private String bankName;

    private String bankCode;
    
    private String bindMerch;
    
    private Long totalRecharge;
    
    private Long totalWithdraw;

    private Long withdrawLimit;

    private String bindStatus;
    
    private String fuiouUserId;
    
    private String fuiouProtocolNo;
    
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

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public Long getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(Long withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus == null ? null : bindStatus.trim();
    }

	public String getBindMerch() {
		return bindMerch;
	}

	public void setBindMerch(String bindMerch) {
		this.bindMerch = bindMerch;
	}

	public Long getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(Long totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

	public Long getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(Long totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public String getFuiouUserId() {
		return fuiouUserId;
	}

	public void setFuiouUserId(String fuiouUserId) {
		this.fuiouUserId = fuiouUserId;
	}

	public String getFuiouProtocolNo() {
		return fuiouProtocolNo;
	}

	public void setFuiouProtocolNo(String fuiouProtocolNo) {
		this.fuiouProtocolNo = fuiouProtocolNo;
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