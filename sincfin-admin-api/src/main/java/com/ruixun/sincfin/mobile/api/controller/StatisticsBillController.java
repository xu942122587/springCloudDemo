package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.StatisticsBillClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.StatisticsBillQuery;
import com.ruixun.sincfin.mobile.api.service.ExportExcelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("statisticsBill")
public class StatisticsBillController extends BaseController {

    @Resource
    private StatisticsBillClient statisticsBillClient;
    @Resource
    private ExportExcelService exportExcelService;

    @RequestMapping("listPage")
    public ApiResponse listPage(StatisticsBillQuery query){
        return ApiResponse.successApiResponse(statisticsBillClient.listPageByCondition(query));
    }

    @RequestMapping("getToday")
    public ApiResponse getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date gmtStart = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date gmtEnd = calendar.getTime();

        StatisticsBillQuery query = new StatisticsBillQuery();
        query.setGmtStart(gmtStart);
        query.setGmtEnd(gmtEnd);
        return ApiResponse.successApiResponse(statisticsBillClient.getComputeData(query));
    }

    @RequestMapping("listAccountWithdraw")
    public ApiResponse listAccountWithdraw(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listAccountWithdraw(query));
    }

    @RequestMapping("listAccountRecharge")
    public ApiResponse listAccountRecharge(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listAccountRecharge(query));
    }

    @RequestMapping("listInvestment")
    public ApiResponse listInvestment(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listInvestment(query));
    }

    @RequestMapping("listRepayment")
    public ApiResponse listRepayment(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listRepayment(query));
    }
    @RequestMapping("listCoupon")
    public ApiResponse listCoupon(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listCoupon(query));
    }

    @RequestMapping("listCashBack")
    public ApiResponse listCashBack(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listCashBack(query));
    }

    @RequestMapping("listSellOut")
    public ApiResponse listSellOut(StatisticsBillQuery query){
        buildExportQuery(query);
        return ApiResponse.successApiResponse(
                statisticsBillClient.listSellOut(query));
    }

    @RequestMapping("exportAccountWithdraw")
    public void exportAccountWithdraw(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsAccountWithdraw(
                statisticsBillClient.listAccountWithdraw(query));
    }

    @RequestMapping("exportAccountRecharge")
    public void exportAccountRecharge(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsAccountRecharge(
                statisticsBillClient.listAccountRecharge(query));
    }

    @RequestMapping("exportInvestment")
    public void exportInvestment(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsInvestment(
                statisticsBillClient.listInvestment(query));
    }

    @RequestMapping("exportRepayment")
    public void exportRepayment(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsRepayment(
                statisticsBillClient.listRepayment(query));
    }
    @RequestMapping("exportCoupon")
    public void exportCoupon(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsCoupon(
                statisticsBillClient.listCoupon(query));
    }

    @RequestMapping("exportCashBack")
    public void exportCashBack(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsCashBack(
                statisticsBillClient.listCashBack(query));
    }

    @RequestMapping("exportSellOut")
    public void exportSellOut(StatisticsBillQuery query) throws IOException {
        buildExportQuery(query);
        exportExcelService.exportStatisticsSellOut(
                statisticsBillClient.listSellOut(query));
    }


    private void buildExportQuery(StatisticsBillQuery query) {
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getDate());
        query.setGmtStart(query.getDate());
        query.setGmtEnd(DateUtils.addDays(query.getDate(), 1));
        if (StringUtils.isNotEmpty(query.getIds())) {
            query.setIdList(Arrays.asList(query.getIds().split(",")).stream()
                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
        }
    }

}
