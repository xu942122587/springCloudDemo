package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author t
 * @date 2018/5/21 15:24
 */
public class UserDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

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

    private String channelName;

    private Long inviterId;

    private Integer riskType;

    private String accountType;

    private String status;

    private Long walletBalance;

    private Long currentInvestmentAmount;

    private Date gmtFirstInvestment;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameAuth() {
        return realNameAuth;
    }

    public void setRealNameAuth(String realNameAuth) {
        this.realNameAuth = realNameAuth;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMobileProvinceCode() {
        return mobileProvinceCode;
    }

    public void setMobileProvinceCode(String mobileProvinceCode) {
        this.mobileProvinceCode = mobileProvinceCode;
    }

    public String getMobileCityCode() {
        return mobileCityCode;
    }

    public void setMobileCityCode(String mobileCityCode) {
        this.mobileCityCode = mobileCityCode;
    }

    public String getMobileProvinceName() {
        return mobileProvinceName;
    }

    public void setMobileProvinceName(String mobileProvinceName) {
        this.mobileProvinceName = mobileProvinceName;
    }

    public String getMobileCityName() {
        return mobileCityName;
    }

    public void setMobileCityName(String mobileCityName) {
        this.mobileCityName = mobileCityName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Long walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Long getCurrentInvestmentAmount() {
        return currentInvestmentAmount;
    }

    public void setCurrentInvestmentAmount(Long currentInvestmentAmount) {
        this.currentInvestmentAmount = currentInvestmentAmount;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Date getGmtFirstInvestment() {
        return gmtFirstInvestment;
    }

    public void setGmtFirstInvestment(Date gmtFirstInvestment) {
        this.gmtFirstInvestment = gmtFirstInvestment;
    }
}
