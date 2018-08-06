package com.ruixun.sincfin.mobile.api.task;

import com.ruixun.sincfin.biz.feign.client.AccountRepaymentClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@JobHandler("AccountRepaymentMSNHandleTask")
public class AccountRepaymentMSNHandleTask extends IJobHandler {

    @Resource
    private AccountRepaymentClient accountRepaymentClient;

    @Override
    public ReturnT<String> execute(String s) {
        accountRepaymentClient.repaymentMSNTask();
        return SUCCESS;
    }
}
