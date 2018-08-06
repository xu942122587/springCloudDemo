package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

public class CouponUserQuery extends BaseRequest {

    private String userRealName;

    private String userMobile;

    private Long userId;

    private Long couponId;

    private Date gmtStartCreate;

    private Date gmtEndCreate;

    private Date gmtStartUse;

    private Date gmtEndUse;

    private String status;

    private String couponType;

    private static final long serialVersionUID = 1L;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public Date getGmtStartUse() {
        return gmtStartUse;
    }

    public void setGmtStartUse(Date gmtStartUse) {
        this.gmtStartUse = gmtStartUse;
    }

    public Date getGmtEndUse() {
        return gmtEndUse;
    }

    public void setGmtEndUse(Date gmtEndUse) {
        this.gmtEndUse = gmtEndUse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}
