package com.ruixun.sincfin.mobile.api.task;

import com.ruixun.sincfin.biz.feign.client.StatisticsBillClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@JobHandler("StatisticsBillHandleTask")
public class StatisticsBillHandleTask extends IJobHandler {

    @Resource
    private StatisticsBillClient statisticsBillClient;

    @Override
    public ReturnT<String> execute(String s) {
        statisticsBillClient.yesterdayTask();
        return SUCCESS;
    }
}
