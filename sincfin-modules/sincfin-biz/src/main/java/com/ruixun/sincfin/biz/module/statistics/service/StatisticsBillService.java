package com.ruixun.sincfin.biz.module.statistics.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruixun.sincfin.biz.feign.client.StatisticsBillClient;
import com.ruixun.sincfin.biz.module.statistics.mapper.StatisticsBillMapper;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountMoneyRecordDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.dto.StatisticsAccountMoneyDto;
import com.ruixun.sincfin.domain.dto.StatisticsBillDto;
import com.ruixun.sincfin.domain.entity.StatisticsBillEntity;
import com.ruixun.sincfin.domain.enums.AccountMoneyRecordTypeEnum;
import com.ruixun.sincfin.domain.query.StatisticsBillQuery;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tiea on 2018/6/2.
 */
@RestController
@RequestMapping("/statisticsBill")
public class StatisticsBillService implements StatisticsBillClient {

    @Resource
    private StatisticsBillMapper statisticsBillMapper;

    @RequestMapping("/listPageByCondition")
    public Pagination<StatisticsBillDto> listPageByCondition(@RequestBody StatisticsBillQuery query) {

        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<StatisticsBillDto> statisticsBillDtoList = statisticsBillMapper.list(query);
        PageInfo pageInfo = new PageInfo(statisticsBillDtoList);

        return new Pagination(query, statisticsBillDtoList, (int) pageInfo.getTotal());

    }

    /**
     * 获取时间段计算出的数据
     * @param query gmtStart
     * @param query gmtEnd
     * @return
     */
    @RequestMapping("/getComputeData")
    public StatisticsBillDto getComputeData(@RequestBody StatisticsBillQuery query) {

        StatisticsBillDto statisticsBillDto = StatisticsBillDto.getZeroObject();
        statisticsBillDto.setRequestWithdrawAmount(
                statisticsBillMapper.getRequestWithdrawAmount(query));
        statisticsBillDto.setSellOutAmount(
                statisticsBillMapper.getSellOutAmount(query));

        List<AccountMoneyRecordDto> accountMoneyRecordDtoList
                = statisticsBillMapper.getAccountMoneyStatistics(query);
        accountMoneyRecordDtoList.forEach( dto -> {
            if (AccountMoneyRecordTypeEnum.WITHDRAW.getCode().equals(dto.getType())) {
                statisticsBillDto.setWithdrawAmount(dto.getAmount());
            } else if (AccountMoneyRecordTypeEnum.PRINCIPAL_INTEREST.getCode().equals(dto.getType())) {
                statisticsBillDto.setRepaymentAmount(dto.getAmount());
            } else if (AccountMoneyRecordTypeEnum.CASH_BACK.getCode().equals(dto.getType())) {
                statisticsBillDto.setCashBackAmount(dto.getAmount());
            } else if (AccountMoneyRecordTypeEnum.INVESTMENT.getCode().equals(dto.getType())) {
                statisticsBillDto.setInvestmentAmount(dto.getAmount());
            } else if (AccountMoneyRecordTypeEnum.RECHARGE.getCode().equals(dto.getType())) {
                statisticsBillDto.setRechargeAmount(dto.getAmount());
            } else if (AccountMoneyRecordTypeEnum.COUPON_RECHARGE.getCode().equals(dto.getType())) {
                statisticsBillDto.setCouponAmount(dto.getAmount());
            }
        });

        return statisticsBillDto;
    }
    @RequestMapping("/listAccountWithdraw")
    public List<StatisticsAccountMoneyDto> listAccountWithdraw(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listAccountWithdraw(query);
    }

    @RequestMapping("/listAccountRecharge")
    public List<StatisticsAccountMoneyDto> listAccountRecharge(@RequestBody StatisticsBillQuery query) {

        return statisticsBillMapper.listAccountRecharge(query);
    }

    @RequestMapping("listInvestment")
    public List<StatisticsAccountMoneyDto> listInvestment(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listInvestment(query);
    }

    @RequestMapping("listRepayment")
    public List<StatisticsAccountMoneyDto> listRepayment(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listRepayment(query);
    }

    @RequestMapping("listCoupon")
    public List<StatisticsAccountMoneyDto> listCoupon(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listCoupon(query);
    }

    @RequestMapping("listCashBack")
    public List<StatisticsAccountMoneyDto> listCashBack(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listCashBack(query);
    }

    @Override
    public List<ProductDto> listSellOut(@RequestBody StatisticsBillQuery query) {
        return statisticsBillMapper.listSellOut(query);
    }


    @Override
    @RequestMapping("yesterdayTask")
    public void yesterdayTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date gmtEnd = calendar.getTime();

        calendar.add(Calendar.DATE, - 1);
        Date gmtStart = calendar.getTime();

        StatisticsBillQuery query = new StatisticsBillQuery();
        query.setGmtStart(gmtStart);
        query.setGmtEnd(gmtEnd);
        // 获取前一天数据
        StatisticsBillDto statisticsBillDto = getComputeData(query);

        StatisticsBillDto billDto = statisticsBillMapper.getByStatisticsDate(gmtStart);

        StatisticsBillEntity statisticsBillEntity = new StatisticsBillEntity();
        statisticsBillEntity.setStatisticsDate(gmtStart);
        statisticsBillEntity.setRequestWithdrawAmount(statisticsBillDto.getRequestWithdrawAmount());
        statisticsBillEntity.setWithdrawAmount(statisticsBillDto.getWithdrawAmount());
        statisticsBillEntity.setRechargeAmount(statisticsBillDto.getRechargeAmount());
        statisticsBillEntity.setInvestmentAmount(statisticsBillDto.getInvestmentAmount());
        statisticsBillEntity.setRepaymentAmount(statisticsBillDto.getRepaymentAmount());
        statisticsBillEntity.setCouponAmount(statisticsBillDto.getCouponAmount());
        statisticsBillEntity.setCashBackAmount(statisticsBillDto.getCashBackAmount());
        statisticsBillEntity.setSellOutAmount(statisticsBillDto.getSellOutAmount());
        if (billDto != null) {
            statisticsBillEntity.setId(billDto.getId());
            statisticsBillMapper.updateByPrimaryKeySelective(statisticsBillEntity);
        } else {
            statisticsBillMapper.insertSelective(statisticsBillEntity);
        }

    }

    @Override
    @RequestMapping("realTimeBalanceTask")
    public void realTimeBalanceTask() {
        long balance = statisticsBillMapper.getRealTimeBalance();
        long currentStock = statisticsBillMapper.getRealTimeCurrentStock();

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // 时间误差太大，走其他统计逻辑
        if (hour != 23 && hour != 0) {
            return;
        }
        // 消除时间误差 比如 23:59:59 或 00:00:00
        if (hour == 0) {
            calendar.add(Calendar.DATE, -1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date statisticsDate = calendar.getTime();

        StatisticsBillDto billDto = statisticsBillMapper.getByStatisticsDate(statisticsDate);
        calendar.add(Calendar.DATE, - 1);
        Date beforeDay = calendar.getTime();

        StatisticsBillDto beforeDayBillDto = statisticsBillMapper.getByStatisticsDate(beforeDay);

        StatisticsBillEntity statisticsBillEntity = new StatisticsBillEntity();
        statisticsBillEntity.setStatisticsDate(statisticsDate);
        statisticsBillEntity.setBalance(balance);
        statisticsBillEntity.setBalanceIncrement(balance);
        statisticsBillEntity.setCurrentStock(currentStock);
        if (beforeDayBillDto != null) {
            statisticsBillEntity.setBalanceIncrement(balance - beforeDayBillDto.getBalance());
        }
        if (billDto != null) {
            statisticsBillEntity.setId(billDto.getId());
            statisticsBillMapper.updateByPrimaryKeySelective(statisticsBillEntity);
        } else {
            statisticsBillMapper.insertSelective(statisticsBillEntity);
        }

    }


}
