package com.ruixun.sincfin.mobile.api.task;

import javax.annotation.Resource;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import com.ruixun.sincfin.biz.feign.client.AccountClient;

@Component
@JobHandler("rechargeHandleTask")
public class RechargeHandleTask extends IJobHandler {

    @Resource
    private AccountClient accountClient;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        accountClient.rechargeHandle();
        return SUCCESS;
    }
}
