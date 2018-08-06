package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiea on 2018/6/4.
 */
public class UserBankChangeQuery extends BaseRequest {

    private String userRealName;
    private String userMobile;

    private Date gmtStartCreate;
    private Date gmtEndCreate;

    private String status;

    private Date gmtStartAudit;
    private Date gmtEndAudit;

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtStartAudit() {
        return gmtStartAudit;
    }

    public void setGmtStartAudit(Date gmtStartAudit) {
        this.gmtStartAudit = gmtStartAudit;
    }

    public Date getGmtEndAudit() {
        return gmtEndAudit;
    }

    public void setGmtEndAudit(Date gmtEndAudit) {
        this.gmtEndAudit = gmtEndAudit;
    }
}
