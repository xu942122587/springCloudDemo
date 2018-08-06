package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class UserBankChangeDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Long userId;

    private String userRealName;

    private String userMobile;

    private String userIdCardNo;

    private Long beforeUserBankId;

    private String beforeBankCardNo;

    private String beforeBankName;

    private Long afterUserBankId;

    private String afterBankCardNo;

    private String afterBankName;

    private String idCardFaceImage;

    private String idCardFaceImageUrl;

    private String idCardBackImage;

    private String idCardBackImageUrl;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getUserIdCardNo() {
        return userIdCardNo;
    }

    public void setUserIdCardNo(String userIdCardNo) {
        this.userIdCardNo = userIdCardNo;
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

    public Long getBeforeUserBankId() {
        return beforeUserBankId;
    }

    public void setBeforeUserBankId(Long beforeUserBankId) {
        this.beforeUserBankId = beforeUserBankId;
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

    public String getIdCardFaceImageUrl() {
        return idCardFaceImageUrl;
    }

    public void setIdCardFaceImageUrl(String idCardFaceImageUrl) {
        this.idCardFaceImageUrl = idCardFaceImageUrl;
    }

    public String getIdCardBackImageUrl() {
        return idCardBackImageUrl;
    }

    public void setIdCardBackImageUrl(String idCardBackImageUrl) {
        this.idCardBackImageUrl = idCardBackImageUrl;
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