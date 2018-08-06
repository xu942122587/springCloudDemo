package com.ruixun.sincfin.vdata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.StatisticsDataClient;
import com.ruixun.sincfin.common.util.DateUtils;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

/**  
     * @Description TODO  
     * @ClassName   DataStatisticsController  
     * @Date        2018年7月17日 下午2:58:51  
     * @Author      hjj
     * Copyright (c) All Rights Reserved, 2018.  
     */  
@RestController
@RequestMapping("viewapi/statisticData")
public class StatisticsDataController {

	@Resource
	private StatisticsDataClient statisticsDataClient;
	
	  
	    /**  
	     * @Description TODO  
	     * @Author      hjj 
	     * @Date        2018年7月17日 下午3:10:58  
	     * @param @param date
	     * @param @return 参数  
	     * @return ApiResponse 返回类型   
	     * @throws  
	     */  
	    
	@RequestMapping("getByDate")
	public ApiResponse getByDate(String date,HttpServletResponse response) {
		if(date==null) {
			date=DateUtils.today();
		}
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		return ApiResponse.successApiResponse(statisticsDataClient.getByDate(date));
	}
}
