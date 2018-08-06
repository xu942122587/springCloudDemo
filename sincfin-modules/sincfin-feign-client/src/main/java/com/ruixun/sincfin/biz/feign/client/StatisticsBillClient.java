package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.StatisticsAccountMoneyDto;
import com.ruixun.sincfin.domain.dto.StatisticsBillDto;
import com.ruixun.sincfin.domain.query.StatisticsBillQuery;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface StatisticsBillClient {

    @RequestMapping("/statisticsBill/listPageByCondition")
    Pagination<StatisticsBillDto> listPageByCondition(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/getComputeData")
    StatisticsBillDto getComputeData(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listAccountWithdraw")
    List<StatisticsAccountMoneyDto> listAccountWithdraw(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listAccountRecharge")
    List<StatisticsAccountMoneyDto> listAccountRecharge(@RequestBody StatisticsBillQuery query);


    @RequestMapping("/statisticsBill/listInvestment")
    List<StatisticsAccountMoneyDto> listInvestment(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listRepayment")
    List<StatisticsAccountMoneyDto> listRepayment(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listCoupon")
    List<StatisticsAccountMoneyDto> listCoupon(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listCashBack")
    List<StatisticsAccountMoneyDto> listCashBack(@RequestBody StatisticsBillQuery query);

    @RequestMapping("/statisticsBill/listSellOut")
    List<ProductDto> listSellOut(@RequestBody StatisticsBillQuery query);


    /**
     * 统计账单前一天数据任务
     */
    @RequestMapping("/statisticsBill/yesterdayTask")
    void yesterdayTask();

    /**
     * 统计实时余额、存量 任务
     */
    @RequestMapping("/statisticsBill/realTimeBalanceTask")
    void realTimeBalanceTask();


}


