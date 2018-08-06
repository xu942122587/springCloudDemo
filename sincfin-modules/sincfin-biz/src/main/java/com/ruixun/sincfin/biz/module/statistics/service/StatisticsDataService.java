package com.ruixun.sincfin.biz.module.statistics.service;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.StatisticsDataClient;
import com.ruixun.sincfin.biz.module.order.mapper.OrderMapper;
import com.ruixun.sincfin.biz.module.statistics.mapper.StatisticsBillMapper;
import com.ruixun.sincfin.biz.module.user.mapper.UserMapper;
import com.ruixun.sincfin.domain.dto.StatisticsDataDto;
import com.ruixun.sincfin.domain.entity.StatisticsBillEntity;

  
    /**  
     * @Description TODO  
     * @ClassName   DataStatisticsService  
     * @Date        2018年7月17日 下午3:23:55  
     * @Author      HJJ  
     * Copyright (c) All Rights Reserved, 2018.  
     */  
@RestController
//@RequestMapping("statisticData")    
public class StatisticsDataService implements StatisticsDataClient{

	@Resource
	private UserMapper userMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private StatisticsBillMapper statisticsBillMapper;
	
	@RequestMapping(value = "/viewapi/statisticData/getByDate", method = RequestMethod.GET)
	public StatisticsDataDto getByDate(@RequestParam("date") String date) {
		Integer regUserCounts=userMapper.getRegUserCountsByDate(date);
		Integer regUserAndInvestmentCounts = userMapper.getRegUserAndInvestmentCountsByDate(date);
		Integer investUserCounts = orderMapper.getInvestUserCountsByDate(date);
		Long investUserTotalNewUserAreaByDate = orderMapper.getInvestUserTotalNewUserAreaByDate(date);
		Long reInvestUserTotalNewUserAreaByDate = orderMapper.getReInvestUserTotalNewUserAreaByDate(date);
		Long releaseAmountByDate = orderMapper.getReleaseAmountByDate(date);
		StatisticsBillEntity billEntity = statisticsBillMapper.getByDate(date);
		Long rechargeAmount=0L;
		Long withdrawAmount=0L;
		Long investmentAmount=0L;
		Long repaymentAmount=0L;
		if(billEntity!=null) {
			rechargeAmount = billEntity.getRechargeAmount();
			withdrawAmount = billEntity.getWithdrawAmount();
			investmentAmount = billEntity.getInvestmentAmount();
			repaymentAmount = billEntity.getRepaymentAmount();
		}
		//当日新增存量
		Long currentStock=0L;
		if(rechargeAmount!=null||withdrawAmount!=null) {
			currentStock=rechargeAmount-withdrawAmount;
		}
		
		StatisticsDataDto statisticsDataDto=new StatisticsDataDto();
		statisticsDataDto.setInvestmentAmount(investmentAmount);
		statisticsDataDto.setInvestmentAmountNewArea(investUserTotalNewUserAreaByDate);
		statisticsDataDto.setNewCurrentStock(currentStock);
		statisticsDataDto.setRechargeAmount(rechargeAmount);
		statisticsDataDto.setNewInvUserCount(investUserCounts);
		statisticsDataDto.setRegUserAndInvCount(regUserAndInvestmentCounts);
		statisticsDataDto.setReInvestmentAmount(reInvestUserTotalNewUserAreaByDate);
		statisticsDataDto.setReleaseAmount(releaseAmountByDate);
		statisticsDataDto.setRepaymentAmount(repaymentAmount);
		statisticsDataDto.setWithdrawAmount(withdrawAmount);
		statisticsDataDto.setRegUserCount(regUserCounts);
		return statisticsDataDto;
	}

}
