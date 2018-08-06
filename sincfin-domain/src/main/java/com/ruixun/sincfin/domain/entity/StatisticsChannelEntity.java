package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class StatisticsChannelEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private Long channelId;

    private Integer activateCount;

    private Integer registerCount;

    private Integer firstInvestmentCount;

    private Long firstInvestmentAmount;

    private Long totalInvestmentAmount;

    private Long currentStock;

    private Date statisticsDate;

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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getActivateCount() {
        return activateCount;
    }

    public void setActivateCount(Integer activateCount) {
        this.activateCount = activateCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getFirstInvestmentCount() {
        return firstInvestmentCount;
    }

    public void setFirstInvestmentCount(Integer firstInvestmentCount) {
        this.firstInvestmentCount = firstInvestmentCount;
    }

    public Long getFirstInvestmentAmount() {
        return firstInvestmentAmount;
    }

    public void setFirstInvestmentAmount(Long firstInvestmentAmount) {
        this.firstInvestmentAmount = firstInvestmentAmount;
    }

    public Long getTotalInvestmentAmount() {
        return totalInvestmentAmount;
    }

    public void setTotalInvestmentAmount(Long totalInvestmentAmount) {
        this.totalInvestmentAmount = totalInvestmentAmount;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

    public Date getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Date statisticsDate) {
        this.statisticsDate = statisticsDate;
    }
}