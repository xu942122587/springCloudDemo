package com.ruixun.sincfin.biz.module.statistics.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.StatisticsAllClient;
import com.ruixun.sincfin.biz.module.statistics.mapper.StatisticsAllMapper;
import com.ruixun.sincfin.domain.dto.StatisticsAllDto;

/**
 * Created by tiea on 2018/6/2.
 */
@RestController
@RequestMapping("/viewapi/statisticsAll")
public class StatisticsAllService implements StatisticsAllClient {

    @Resource
    private StatisticsAllMapper statisticsAllMapper;
 
    @RequestMapping("/listPageStatiscs")
    public StatisticsAllDto listPageStatiscs() {

    	StatisticsAllDto statisticsResult = new StatisticsAllDto();
    	
    	Map<String, Object> accountStatistics = statisticsAllMapper.accountStatistics();
    	statisticsResult.setSumPlatformStock(((BigDecimal)accountStatistics.get("sumPlatformStock")).longValue());
    	statisticsResult.setSumPlatformAccountBalance(((BigDecimal)accountStatistics.get("sumPlatformAccountBalance")).longValue());
    	
    	Map<String, Object> platformInvestStatistics = statisticsAllMapper.platformInvestStatistics();
    	statisticsResult.setSumPlatformInvest(((BigDecimal)platformInvestStatistics.get("sumPlatformInvest")).longValue());

    	Map<String, Object> monthPlatformInvestStatistics = statisticsAllMapper.monthPlatformInvestStatistics();
    	statisticsResult.setMonthPlatformInvest(((BigDecimal)monthPlatformInvestStatistics.get("monthPlatformInvest")).longValue());
    	
    	Map<String, Object> monthPlatformStockStatistics = statisticsAllMapper.monthPlatformStockStatistics();
    	statisticsResult.setMonthPlatformStock(((BigDecimal)monthPlatformStockStatistics.get("monthPlatformStock")).longValue());
    	
    	Map<String, Object> oneAverageInvestStatistics = statisticsAllMapper.oneAverageInvestStatistics();
    	statisticsResult.setOneAverageInvest(((BigDecimal)oneAverageInvestStatistics.get("oneAverageInvest")).longValue());
    	
    	Map<String, Object> peopleInvestAndCountStatistics = statisticsAllMapper.peopleInvestAndCountStatistics();
    	statisticsResult.setPeopleInvest(((BigDecimal)peopleInvestAndCountStatistics.get("peopleInvest")).longValue());
    	statisticsResult.setPeopleInvestCount(((BigDecimal)peopleInvestAndCountStatistics.get("peopleInvestCount")).longValue());
    	
    	Map<String, Object> peopleAddLoyalTimeStatistics = statisticsAllMapper.peopleAddLoyalTimeStatistics();
    	statisticsResult.setPeopleAddLoyalTime(((BigDecimal)peopleAddLoyalTimeStatistics.get("peopleAddLoyalTime")).doubleValue());
    	
    	Map<String, Object> peopleIntervalTimeStatistics = statisticsAllMapper.peopleIntervalTimeStatistics();
    	statisticsResult.setPeopleIntervalTime(((BigDecimal)peopleIntervalTimeStatistics.get("peopleIntervalTime")).doubleValue());
    	
    	Map<String, Object> peopleInvestCountStatistics = statisticsAllMapper.peopleInvestCountStatistics();
    	statisticsResult.setPeopleInvestCount(((BigDecimal)peopleInvestCountStatistics.get("peopleInvestCount")).doubleValue());
    	
    	Map<String, Object> peopleInvestMoneyStatistics = statisticsAllMapper.peopleInvestMoneyStatistics();
    	statisticsResult.setPeopleInvestMoney(((BigDecimal)peopleInvestMoneyStatistics.get("peopleInvestMoney")).doubleValue());
    	
    	Map<String, Object> sumPeopleRegistStatistics = statisticsAllMapper.sumPeopleRegistStatistics();
    	statisticsResult.setSumPeopleRegist(((Long)sumPeopleRegistStatistics.get("sumPeopleRegist")).intValue());
    	
    	Map<String, Object> sumPeopleInvestStatistics = statisticsAllMapper.sumPeopleInvestStatistics();
    	statisticsResult.setSumPeopleInvest(((Long)sumPeopleInvestStatistics.get("sumPeopleInvest")).intValue());
    	
    	Map<String, Object> sumPlatformPeopleInvestStatistics = statisticsAllMapper.sumPlatformPeopleInvestStatistics();
    	statisticsResult.setSumPlatformPeopleInvest(((Long)sumPlatformPeopleInvestStatistics.get("sumPlatformPeopleInvest")).intValue());
    	
        return statisticsResult;

    }


}
