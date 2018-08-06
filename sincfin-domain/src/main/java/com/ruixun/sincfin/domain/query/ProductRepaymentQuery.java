package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/5/31.
 */
public class ProductRepaymentQuery extends BaseRequest {

    private Long productId;

    private String productTitle;

    private Date gmtStartExpectedRepayment;
    private Date gmtEndExpectedRepayment;

    private String status;

    private Date gmtStartRepayment;
    private Date gmtEndRepayment;

    private String type;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Date getGmtStartExpectedRepayment() {
        return gmtStartExpectedRepayment;
    }

    public void setGmtStartExpectedRepayment(Date gmtStartExpectedRepayment) {
        this.gmtStartExpectedRepayment = gmtStartExpectedRepayment;
    }

    public Date getGmtEndExpectedRepayment() {
        return gmtEndExpectedRepayment;
    }

    public void setGmtEndExpectedRepayment(Date gmtEndExpectedRepayment) {
        this.gmtEndExpectedRepayment = gmtEndExpectedRepayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getGmtStartRepayment() {
        return gmtStartRepayment;
    }

    public void setGmtStartRepayment(Date gmtStartRepayment) {
        this.gmtStartRepayment = gmtStartRepayment;
    }

    public Date getGmtEndRepayment() {
        return gmtEndRepayment;
    }

    public void setGmtEndRepayment(Date gmtEndRepayment) {
        this.gmtEndRepayment = gmtEndRepayment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
