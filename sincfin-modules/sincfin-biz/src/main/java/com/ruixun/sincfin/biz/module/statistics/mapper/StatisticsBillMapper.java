package com.ruixun.sincfin.biz.module.statistics.mapper;

import com.ruixun.sincfin.domain.dto.AccountMoneyRecordDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.StatisticsAccountMoneyDto;
import com.ruixun.sincfin.domain.dto.StatisticsBillDto;
import com.ruixun.sincfin.domain.entity.StatisticsBillEntity;
import com.ruixun.sincfin.domain.query.StatisticsBillQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

  
    /**  
     * @Description TODO  
     * @ClassName   StatisticsBillMapper  
     * @Date        2018年7月17日 下午5:10:20  
     * @Author      张三  
     * Copyright (c) All Rights Reserved, 2018.  
     */  
    
public interface StatisticsBillMapper {
    int insert(StatisticsBillEntity record);

    int insertSelective(StatisticsBillEntity record);

    StatisticsBillEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StatisticsBillEntity record);

    int updateByPrimaryKey(StatisticsBillEntity record);

    List<StatisticsBillDto> list(StatisticsBillQuery query);

    /**
     * 获取申请提现金额
     * @param query
     * @return
     */
    long getRequestWithdrawAmount(StatisticsBillQuery query);

    /**
     * 获取申请提现金额
     * @param query
     * @return
     */
    long getSellOutAmount(StatisticsBillQuery query);

    /**
     * 获取 查询日期 数据
     * @param statisticsDate
     * @return
     */
    StatisticsBillDto getByStatisticsDate(@Param("statisticsDate") Date statisticsDate);

    /**
     * 获取实时余额
     * @return
     */
    long getRealTimeBalance();

    /**
     * 获取实时存量
     * @return
     */
    long getRealTimeCurrentStock();

    /**
     * 获取账单数据汇总
     * @param query
     * @return
     */
    List<AccountMoneyRecordDto> getAccountMoneyStatistics(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listAccountWithdraw(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listAccountRecharge(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listInvestment(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listRepayment(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listCoupon(StatisticsBillQuery query);

    List<StatisticsAccountMoneyDto> listCashBack(StatisticsBillQuery query);

    List<ProductDto> listSellOut(StatisticsBillQuery query);

    /**
     * 根据日期查询
     * @param date
     * @return
     */
    StatisticsBillEntity getByDate(String date);

}