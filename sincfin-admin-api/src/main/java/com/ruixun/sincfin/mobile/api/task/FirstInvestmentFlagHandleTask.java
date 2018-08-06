package com.ruixun.sincfin.mobile.api.task;

import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@JobHandler("FirstInvestmentFlagHandleTask")
public class FirstInvestmentFlagHandleTask extends IJobHandler {

    @Resource
    private UserClient userClient;

    @Override
    public ReturnT<String> execute(String s) {
        userClient.firstInvestmentFlagTask();
        return SUCCESS;
    }
}
