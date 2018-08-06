package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by tiantiea on 2018/5/24.
 */
public class AccountRepaymentQuery extends BaseRequest {

    private Long userId;

    private Long productId;

    private String orderNum;

    private String userRealName;
    private String userMobile;

    private String status;

    private Date gmtStartRepayment;
    private Date gmtEndRepayment;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
}
