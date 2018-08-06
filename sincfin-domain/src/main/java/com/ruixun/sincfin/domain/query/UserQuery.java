package com.ruixun.sincfin.domain.query;

import java.util.Date;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

/**
 * @author t
 * @date 2018/5/22 20:36
 */
@SuppressWarnings("serial")
public class UserQuery extends BaseRequest {

	private Long id;
    private String mobile;
    private String realName;
    private String idCardNo;
    private Boolean firstInvestmentFlag;
    private String realNameAuth;
    private String status;
    private String level;
    private Date gmtStartCreate;
    private Date gmtEndCreate;

    private Long channelId;

    private String accountType;


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

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Boolean getFirstInvestmentFlag() {
        return firstInvestmentFlag;
    }

    public void setFirstInvestmentFlag(Boolean firstInvestmentFlag) {
        this.firstInvestmentFlag = firstInvestmentFlag;
    }

    public String getRealNameAuth() {
        return realNameAuth;
    }

    public void setRealNameAuth(String realNameAuth) {
        this.realNameAuth = realNameAuth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getGmtStartCreate() {
        return gmtStartCreate;
    }

    public void setGmtStartCreate(Date gmtStartCreate) {
        this.gmtStartCreate = gmtStartCreate;
    }

    public Date getGmtEndCreate() {
        return gmtEndCreate;
    }

    public void setGmtEndCreate(Date gmtEndCreate) {
        this.gmtEndCreate = gmtEndCreate;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
