package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/6/6.
 */
public class FinancingUserRemittanceQuery extends BaseRequest {

    private Long financingUserId;

    private Date gmtStartCreate;
    private Date gmtEndCreate;

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
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
}
