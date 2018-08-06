package com.ruixun.sincfin.mobile.api.task;

import com.ruixun.sincfin.biz.feign.client.ProductClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@JobHandler("ProductInterestExpireHandleTask")
public class ProductInterestExpireHandleTask extends IJobHandler {

    @Resource
    private ProductClient productClient;

    @Override
    public ReturnT<String> execute(String s) {
        productClient.interestExpire();
        return SUCCESS;
    }
}
