package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class StatisticsChannelDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private Long channelId;

    private String channelName;

    // 激活数
    private Integer activateCount;

    // 注册数
    private Integer registerCount;

    // 首投数
    private Integer firstInvestmentCount;

    // 复投数
    private Integer repeatedlyInvestmentCount;

    // 首投金额
    private Long firstInvestmentAmount;

    // 新增复投金额
    private Long repeatedlyInvestmentAmount;

    // 总投资额
    private Long totalInvestmentAmount;

    // 期间存量
    private Long timeIntervalStock;

    // 当前存量
    private Long currentStock;

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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public Integer getRepeatedlyInvestmentCount() {
        return repeatedlyInvestmentCount;
    }

    public void setRepeatedlyInvestmentCount(Integer repeatedlyInvestmentCount) {
        this.repeatedlyInvestmentCount = repeatedlyInvestmentCount;
    }

    public Long getFirstInvestmentAmount() {
        return firstInvestmentAmount;
    }

    public void setFirstInvestmentAmount(Long firstInvestmentAmount) {
        this.firstInvestmentAmount = firstInvestmentAmount;
    }

    public Long getRepeatedlyInvestmentAmount() {
        return repeatedlyInvestmentAmount;
    }

    public void setRepeatedlyInvestmentAmount(Long repeatedlyInvestmentAmount) {
        this.repeatedlyInvestmentAmount = repeatedlyInvestmentAmount;
    }

    public Long getTotalInvestmentAmount() {
        return totalInvestmentAmount;
    }

    public void setTotalInvestmentAmount(Long totalInvestmentAmount) {
        this.totalInvestmentAmount = totalInvestmentAmount;
    }

    public Long getTimeIntervalStock() {
        return timeIntervalStock;
    }

    public void setTimeIntervalStock(Long timeIntervalStock) {
        this.timeIntervalStock = timeIntervalStock;
    }

    public Long getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Long currentStock) {
        this.currentStock = currentStock;
    }

}