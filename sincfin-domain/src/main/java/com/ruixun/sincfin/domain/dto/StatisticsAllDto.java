package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;

/**
 * Created by tiea on 2018/6/2.
 */
public class StatisticsAllDto implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
     * 平台总存量
     */
    private Long sumPlatformStock;

    /**
     * 平台总投资
     */
    private Long sumPlatformInvest;

    /**
     * 平台账户余额
     */
    private Long sumPlatformAccountBalance;

    /**
     * 当月投资总量
     */
    private Long monthPlatformInvest;

    /**
     * 当月新增存量
     */
    private Long monthPlatformStock;

    /**
     * 单笔投资均额
     */
    private Long oneAverageInvest;

    /**
     * 人均投资金额
     */
    private Long peopleInvest;

    /**
     * 人均累计忠诚时间
     */
    private double peopleAddLoyalTime;

    /**
     * 人均复投间隔时间
     */
    private double peopleIntervalTime;

    /**
     * 人均投资次数
     */
    private double peopleInvestCount;

    /**
     * 人均在投金额
     */
    private double peopleInvestMoney;
    
    /**
     * 总用户注册数
     */
    private int sumPeopleRegist;
    
    /**
     * 总投资比数
     */
    private int sumPeopleInvest;
    /**
     * 当前平台投资用户数
     */
    private int sumPlatformPeopleInvest;
    /**
     * 体验标投资用户数
     */
    private int sumExperiencePeopleCount;
    /**
     * 非体验标投资用户数
     */
    private int sumRegularPeopleCount;
    /**
     * 当前投资1次用户数
     */
    private int oneInvestPeople;
    /**
     * 累计投资1次用户
     */
    private int sumOneInvestPeople;
    /**
     * 当前投资2次用户数
     */
    private int twoInvestPeople;
    /**
     * 累计投资2次用户
     */
    private int sumTwoInvestPeople;
    /**
     * 当前投资3次用户数
     */
    private int threeInvestPeople;
    /**
     * 累计投资3次用户
     */
    private int sumThreeInvestPeople;
    /**
     * 当前投资3次以上用户数
     */
    private int moreInvestPeople;
    /**
     * 累计投资3次以上用户
     */
    private int sumMoreInvestPeople;
    
    
    
	public Long getSumPlatformStock() {
		return sumPlatformStock;
	}
	public void setSumPlatformStock(Long sumPlatformStock) {
		this.sumPlatformStock = sumPlatformStock;
	}
	public Long getSumPlatformInvest() {
		return sumPlatformInvest;
	}
	public void setSumPlatformInvest(Long sumPlatformInvest) {
		this.sumPlatformInvest = sumPlatformInvest;
	}
	public Long getSumPlatformAccountBalance() {
		return sumPlatformAccountBalance;
	}
	public void setSumPlatformAccountBalance(Long sumPlatformAccountBalance) {
		this.sumPlatformAccountBalance = sumPlatformAccountBalance;
	}
	public Long getMonthPlatformInvest() {
		return monthPlatformInvest;
	}
	public void setMonthPlatformInvest(Long monthPlatformInvest) {
		this.monthPlatformInvest = monthPlatformInvest;
	}
	public Long getMonthPlatformStock() {
		return monthPlatformStock;
	}
	public void setMonthPlatformStock(Long monthPlatformStock) {
		this.monthPlatformStock = monthPlatformStock;
	}
	public Long getOneAverageInvest() {
		return oneAverageInvest;
	}
	public void setOneAverageInvest(Long oneAverageInvest) {
		this.oneAverageInvest = oneAverageInvest;
	}
	public Long getPeopleInvest() {
		return peopleInvest;
	}
	public void setPeopleInvest(Long peopleInvest) {
		this.peopleInvest = peopleInvest;
	}
	public double getPeopleAddLoyalTime() {
		return peopleAddLoyalTime;
	}
	public void setPeopleAddLoyalTime(double peopleAddLoyalTime) {
		this.peopleAddLoyalTime = peopleAddLoyalTime;
	}
	public double getPeopleIntervalTime() {
		return peopleIntervalTime;
	}
	public void setPeopleIntervalTime(double peopleIntervalTime) {
		this.peopleIntervalTime = peopleIntervalTime;
	}
	public double getPeopleInvestCount() {
		return peopleInvestCount;
	}
	public void setPeopleInvestCount(double peopleInvestCount) {
		this.peopleInvestCount = peopleInvestCount;
	}
	public double getPeopleInvestMoney() {
		return peopleInvestMoney;
	}
	public void setPeopleInvestMoney(double peopleInvestMoney) {
		this.peopleInvestMoney = peopleInvestMoney;
	}
	public int getSumPeopleRegist() {
		return sumPeopleRegist;
	}
	public void setSumPeopleRegist(int sumPeopleRegist) {
		this.sumPeopleRegist = sumPeopleRegist;
	}
	public int getSumPeopleInvest() {
		return sumPeopleInvest;
	}
	public void setSumPeopleInvest(int sumPeopleInvest) {
		this.sumPeopleInvest = sumPeopleInvest;
	}
	public int getSumPlatformPeopleInvest() {
		return sumPlatformPeopleInvest;
	}
	public void setSumPlatformPeopleInvest(int sumPlatformPeopleInvest) {
		this.sumPlatformPeopleInvest = sumPlatformPeopleInvest;
	}
	public int getSumExperiencePeopleCount() {
		return sumExperiencePeopleCount;
	}
	public void setSumExperiencePeopleCount(int sumExperiencePeopleCount) {
		this.sumExperiencePeopleCount = sumExperiencePeopleCount;
	}
	public int getSumRegularPeopleCount() {
		return sumRegularPeopleCount;
	}
	public void setSumRegularPeopleCount(int sumRegularPeopleCount) {
		this.sumRegularPeopleCount = sumRegularPeopleCount;
	}
	public int getOneInvestPeople() {
		return oneInvestPeople;
	}
	public void setOneInvestPeople(int oneInvestPeople) {
		this.oneInvestPeople = oneInvestPeople;
	}
	public int getSumOneInvestPeople() {
		return sumOneInvestPeople;
	}
	public void setSumOneInvestPeople(int sumOneInvestPeople) {
		this.sumOneInvestPeople = sumOneInvestPeople;
	}
	public int getTwoInvestPeople() {
		return twoInvestPeople;
	}
	public void setTwoInvestPeople(int twoInvestPeople) {
		this.twoInvestPeople = twoInvestPeople;
	}
	public int getSumTwoInvestPeople() {
		return sumTwoInvestPeople;
	}
	public void setSumTwoInvestPeople(int sumTwoInvestPeople) {
		this.sumTwoInvestPeople = sumTwoInvestPeople;
	}
	public int getThreeInvestPeople() {
		return threeInvestPeople;
	}
	public void setThreeInvestPeople(int threeInvestPeople) {
		this.threeInvestPeople = threeInvestPeople;
	}
	public int getSumThreeInvestPeople() {
		return sumThreeInvestPeople;
	}
	public void setSumThreeInvestPeople(int sumThreeInvestPeople) {
		this.sumThreeInvestPeople = sumThreeInvestPeople;
	}
	public int getMoreInvestPeople() {
		return moreInvestPeople;
	}
	public void setMoreInvestPeople(int moreInvestPeople) {
		this.moreInvestPeople = moreInvestPeople;
	}
	public int getSumMoreInvestPeople() {
		return sumMoreInvestPeople;
	}
	public void setSumMoreInvestPeople(int sumMoreInvestPeople) {
		this.sumMoreInvestPeople = sumMoreInvestPeople;
	}

}
