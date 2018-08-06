package com.ruixun.sincfin.biz.module.statistics.mapper;

import java.util.Map;

public interface StatisticsAllMapper {

    /**平台总存量sumPlatformStock  平台账户余额sumPlatformAccountBalance
     * @return
     */
    Map<String, Object> accountStatistics();
    
    /**平台总投资 sumPlatformInvest
     * @return
     */
    Map<String, Object> platformInvestStatistics();
    
    /**当月投资存量 monthPlatformInvest
     * @return
     */
    Map<String, Object> monthPlatformInvestStatistics();
    
    /**当月新增总量monthPlatformStock
     * @return
     */
    Map<String, Object> monthPlatformStockStatistics();
    
    /**单笔投资均额 oneAverageInvest
     * @return
     */
    Map<String, Object> oneAverageInvestStatistics();
    
    /**人均投资金额 peopleInvest 人均投资次数 peopleInvestCount
     * @return
     */
    Map<String, Object> peopleInvestAndCountStatistics();
    
    /**人均累计忠诚时间 peopleAddLoyalTime
     * @return
     */
    Map<String, Object> peopleAddLoyalTimeStatistics();
    
    /**人均复投间隔时间 peopleIntervalTime
     * @return
     */
    Map<String, Object> peopleIntervalTimeStatistics();

    /**人均投资次数 peopleInvestCount
     * @return
     */
    Map<String, Object> peopleInvestCountStatistics();
    
    /**人均在投金额 peopleInvestMoney
     * @return
     */
    Map<String, Object> peopleInvestMoneyStatistics();
    
    /**总用户注册数 sumPeopleRegist
     * @return
     */
    Map<String, Object> sumPeopleRegistStatistics();
    
    /**总投资比数 sumPeopleInvest
     * @return
     */
    Map<String, Object> sumPeopleInvestStatistics();
    
    /**当前平台投资用户数 sumPlatformPeopleInvest
     * @return
     */
    Map<String, Object> sumPlatformPeopleInvestStatistics();
    
}