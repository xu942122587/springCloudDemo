package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/6/6.
 */
public class FinancingUserRepaymentQuery extends BaseRequest {

    private Long financingUserId;

    private Long contractId;

    private Date gmtStartCreate;
    private Date gmtEndCreate;

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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
