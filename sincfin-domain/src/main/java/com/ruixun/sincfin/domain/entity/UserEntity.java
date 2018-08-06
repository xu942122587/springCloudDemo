package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private String mobile;

    private String realName;

    private String realNameAuth;

    private String idCardNo;

    private String deviceType;

    private String mobileProvinceCode;

    private String mobileCityCode;

    private String mobileProvinceName;

    private String mobileCityName;

    private String level;

    private Boolean firstInvestmentFlag;

    private String password;

    private String salt;

    private Long channelId;

    private Long inviterId;

    private Integer riskType;

    private String accountType;

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

    public String getRealNameAuth() {
        return realNameAuth;
    }

    public void setRealNameAuth(String realNameAuth) {
        this.realNameAuth = realNameAuth == null ? null : realNameAuth.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getMobileProvinceCode() {
        return mobileProvinceCode;
    }

    public void setMobileProvinceCode(String mobileProvinceCode) {
        this.mobileProvinceCode = mobileProvinceCode == null ? null : mobileProvinceCode.trim();
    }

    public String getMobileCityCode() {
        return mobileCityCode;
    }

    public void setMobileCityCode(String mobileCityCode) {
        this.mobileCityCode = mobileCityCode == null ? null : mobileCityCode.trim();
    }

    public String getMobileProvinceName() {
        return mobileProvinceName;
    }

    public void setMobileProvinceName(String mobileProvinceName) {
        this.mobileProvinceName = mobileProvinceName == null ? null : mobileProvinceName.trim();
    }

    public String getMobileCityName() {
        return mobileCityName;
    }

    public void setMobileCityName(String mobileCityName) {
        this.mobileCityName = mobileCityName == null ? null : mobileCityName.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Boolean getFirstInvestmentFlag() {
        return firstInvestmentFlag;
    }

    public void setFirstInvestmentFlag(Boolean firstInvestmentFlag) {
        this.firstInvestmentFlag = firstInvestmentFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public Integer getRiskType() {
        return riskType;
    }

    public void setRiskType(Integer riskType) {
        this.riskType = riskType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}