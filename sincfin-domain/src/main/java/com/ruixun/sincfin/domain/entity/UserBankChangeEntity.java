package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class UserBankChangeEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long userId;

    private Long beforeUserBankId;

    private String beforeBankCardNo;

    private String beforeBankName;

    private Long afterUserBankId;

    private String afterBankCardNo;

    private String afterBankName;

    private String idCardFaceImage;

    private String idCardBackImage;

    private Date gmtAudit;

    private Long auditorId;

    private String status;

    private String remark;

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

    public Long getBeforeUserBankId() {
        return beforeUserBankId;
    }

    public void setBeforeUserBankId(Long beforeUserBankId) {
        this.beforeUserBankId = beforeUserBankId;
    }

    public String getBeforeBankCardNo() {
        return beforeBankCardNo;
    }

    public void setBeforeBankCardNo(String beforeBankCardNo) {
        this.beforeBankCardNo = beforeBankCardNo == null ? null : beforeBankCardNo.trim();
    }

    public String getBeforeBankName() {
        return beforeBankName;
    }

    public void setBeforeBankName(String beforeBankName) {
        this.beforeBankName = beforeBankName == null ? null : beforeBankName.trim();
    }

    public Long getAfterUserBankId() {
        return afterUserBankId;
    }

    public void setAfterUserBankId(Long afterUserBankId) {
        this.afterUserBankId = afterUserBankId;
    }

    public String getAfterBankCardNo() {
        return afterBankCardNo;
    }

    public void setAfterBankCardNo(String afterBankCardNo) {
        this.afterBankCardNo = afterBankCardNo == null ? null : afterBankCardNo.trim();
    }

    public String getAfterBankName() {
        return afterBankName;
    }

    public void setAfterBankName(String afterBankName) {
        this.afterBankName = afterBankName == null ? null : afterBankName.trim();
    }

    public String getIdCardFaceImage() {
        return idCardFaceImage;
    }

    public void setIdCardFaceImage(String idCardFaceImage) {
        this.idCardFaceImage = idCardFaceImage == null ? null : idCardFaceImage.trim();
    }

    public String getIdCardBackImage() {
        return idCardBackImage;
    }

    public void setIdCardBackImage(String idCardBackImage) {
        this.idCardBackImage = idCardBackImage == null ? null : idCardBackImage.trim();
    }

    public Date getGmtAudit() {
        return gmtAudit;
    }

    public void setGmtAudit(Date gmtAudit) {
        this.gmtAudit = gmtAudit;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}