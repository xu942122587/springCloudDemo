package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author t
 * @date 2018/5/22 14:08
 */
public class FinancingUserDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String mobile;

    private String type;

    private String realName;

    private String idCardNo;

    private String idCardFaceImage;

    private String idCardFaceImageUrl;

    private String idCardBackImage;

    private String idCardBackImageUrl;

    private String contactNumber;

    private String businessLicenseNo;

    private String businessLicenseImage;

    private String businessLicenseImageUrl;

    private Long totalFinancingAmount;

    private Long totalUnpaidPrincipal;

    private String status;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getIdCardFaceImage() {
        return idCardFaceImage;
    }

    public void setIdCardFaceImage(String idCardFaceImage) {
        this.idCardFaceImage = idCardFaceImage;
    }

    public String getIdCardBackImage() {
        return idCardBackImage;
    }

    public void setIdCardBackImage(String idCardBackImage) {
        this.idCardBackImage = idCardBackImage;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getBusinessLicenseImage() {
        return businessLicenseImage;
    }

    public void setBusinessLicenseImage(String businessLicenseImage) {
        this.businessLicenseImage = businessLicenseImage;
    }

    public Long getTotalFinancingAmount() {
        return totalFinancingAmount;
    }

    public void setTotalFinancingAmount(Long totalFinancingAmount) {
        this.totalFinancingAmount = totalFinancingAmount;
    }

    public Long getTotalUnpaidPrincipal() {
        return totalUnpaidPrincipal;
    }

    public void setTotalUnpaidPrincipal(Long totalUnpaidPrincipal) {
        this.totalUnpaidPrincipal = totalUnpaidPrincipal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBusinessLicenseImageUrl() {
        return businessLicenseImageUrl;
    }

    public void setBusinessLicenseImageUrl(String businessLicenseImageUrl) {
        this.businessLicenseImageUrl = businessLicenseImageUrl;
    }
}
