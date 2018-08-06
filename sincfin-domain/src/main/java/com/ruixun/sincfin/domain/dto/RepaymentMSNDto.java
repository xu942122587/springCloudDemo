package com.ruixun.sincfin.domain.dto;

/**
 * Created by tiantiea on 2018/6/28.
 */
public class RepaymentMSNDto {

    private Integer capitalCount;
    private Long capitalAmount;
    private Integer interestCount;
    private Long interestAmount;

    public RepaymentMSNDto() {
        capitalCount = 0;
        capitalAmount = 0L;
        interestCount = 0;
        interestAmount = 0L;
    }

    public Integer getCapitalCount() {
        return capitalCount;
    }

    public void setCapitalCount(Integer capitalCount) {
        this.capitalCount = capitalCount;
    }

    public Long getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(Long capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public Integer getInterestCount() {
        return interestCount;
    }

    public void setInterestCount(Integer interestCount) {
        this.interestCount = interestCount;
    }

    public Long getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Long interestAmount) {
        this.interestAmount = interestAmount;
    }

}
