package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductRepaymentDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private String productTitle;

    private Long productId;

    private Date gmtExpectedRepayment;

    private Date gmtRepayment;

    private Long amount;

    private String type;

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

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getGmtExpectedRepayment() {
        return gmtExpectedRepayment;
    }

    public void setGmtExpectedRepayment(Date gmtExpectedRepayment) {
        this.gmtExpectedRepayment = gmtExpectedRepayment;
    }

    public Date getGmtRepayment() {
        return gmtRepayment;
    }

    public void setGmtRepayment(Date gmtRepayment) {
        this.gmtRepayment = gmtRepayment;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}