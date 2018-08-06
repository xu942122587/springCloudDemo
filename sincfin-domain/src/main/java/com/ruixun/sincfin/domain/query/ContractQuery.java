package com.ruixun.sincfin.domain.query;

import com.ruixun.sincfin.domain.protocol.request.BaseRequest;

import java.util.Date;

/**
 * Created by tiantiea on 2018/5/24.
 */
public class ContractQuery extends BaseRequest {

    private String title;

    private String financingUserRealName;

    private Long financingUserId;
    private String repaymentType;
    // 查询起始 合同开始时间
    private Date gmtStartStart;
    // 查询结束 合同开始时间
    private Date gmtEndStart;

    // 查询起始 合同结束时间
    private Date gmtStartEnd;
    // 查询结束 合同结束时间
    private Date gmtEndEnd;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinancingUserRealName() {
        return financingUserRealName;
    }

    public void setFinancingUserRealName(String financingUserRealName) {
        this.financingUserRealName = financingUserRealName;
    }

    public Long getFinancingUserId() {
        return financingUserId;
    }

    public void setFinancingUserId(Long financingUserId) {
        this.financingUserId = financingUserId;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Date getGmtStartStart() {
        return gmtStartStart;
    }

    public void setGmtStartStart(Date gmtStartStart) {
        this.gmtStartStart = gmtStartStart;
    }

    public Date getGmtEndStart() {
        return gmtEndStart;
    }

    public void setGmtEndStart(Date gmtEndStart) {
        this.gmtEndStart = gmtEndStart;
    }

    public Date getGmtStartEnd() {
        return gmtStartEnd;
    }

    public void setGmtStartEnd(Date gmtStartEnd) {
        this.gmtStartEnd = gmtStartEnd;
    }

    public Date getGmtEndEnd() {
        return gmtEndEnd;
    }

    public void setGmtEndEnd(Date gmtEndEnd) {
        this.gmtEndEnd = gmtEndEnd;
    }
}
