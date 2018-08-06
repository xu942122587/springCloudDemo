package com.ruixun.sincfin.mobile.api.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ruixun.sincfin.biz.feign.client.CouponUserClient;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@Component
@JobHandler("CouponUserExpiredHandleTask")
public class CouponUserExpiredHandleTask extends IJobHandler {
	private static transient Logger logger = LoggerFactory.getLogger(CouponUserExpiredHandleTask.class);
	@Resource
	private CouponUserClient couponUserClient;

	@Override
	public ReturnT<String> execute(String s) {
		logger.info("执行用户优惠券过期任务");
		couponUserClient.couponUserExpiredTask();
		return SUCCESS;
	}
}
