package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class CouponUserEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long userId;

    private Long couponId;

    private Date gmtValidityStart;

    private Date gmtValidityEnd;

    private Date gmtUse;

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

    public Date getGmtValidityStart() {
        return gmtValidityStart;
    }

    public void setGmtValidityStart(Date gmtValidityStart) {
        this.gmtValidityStart = gmtValidityStart;
    }

    public Date getGmtValidityEnd() {
        return gmtValidityEnd;
    }

    public void setGmtValidityEnd(Date gmtValidityEnd) {
        this.gmtValidityEnd = gmtValidityEnd;
    }

    public Date getGmtUse() {
        return gmtUse;
    }

    public void setGmtUse(Date gmtUse) {
        this.gmtUse = gmtUse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}