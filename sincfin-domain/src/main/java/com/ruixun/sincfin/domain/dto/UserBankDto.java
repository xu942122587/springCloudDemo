package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author t
 * @date 2018/5/23 14:49
 */
public class UserBankDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long userId;

    private String bankCardNo;

    private String idCardNo;

    private String mobile;

    private String realName;

    private String bankName;

    private String bankCode;
    
    private Long totalRecharge;
    
    private Long totalWithdraw;

    private Long withdrawLimit;

    private String bindStatus;

    private Long limitSingle;

    private Long limitDay;

    private Long limitMonth;
    
    private String icon;
    /*可绑定新卡或者权限覆盖新卡*/
    private Boolean canUntie;

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

	public Long getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(Long totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public Long getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(Long totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

    public Long getLimitSingle() {
        return limitSingle;
    }

    public void setLimitSingle(Long limitSingle) {
        this.limitSingle = limitSingle;
    }

    public Long getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(Long limitDay) {
        this.limitDay = limitDay;
    }

    public Long getLimitMonth() {
        return limitMonth;
    }

    public void setLimitMonth(Long limitMonth) {
        this.limitMonth = limitMonth;
    }

	public String getIcon() {
		return icon==null ? null : icon.trim();
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getCanUntie() {
		return canUntie;
	}

	public void setCanUntie(Boolean canUntie) {
		this.canUntie = canUntie;
	}
}
