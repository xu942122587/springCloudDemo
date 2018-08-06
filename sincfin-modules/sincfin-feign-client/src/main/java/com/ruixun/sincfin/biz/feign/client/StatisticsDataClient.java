package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.dto.StatisticsDataDto;

/**  
     * @Description 各统计查询  
     * @ClassName   DataStatisticsClient  
     * @Date        2018年7月17日 下午3:07:22  
     * @Author      HJJ  
     * Copyright (c) All Rights Reserved, 2018.  
     */  
@FeignClient("${biz.feign.name}")   
public interface StatisticsDataClient {

	
	  
	    /**  
	     * @Description 根据日期查询各项统计 
	     * @Author      HJJ  
	     * @Date        2018年7月17日 下午3:27:36  
	     * @param @param date
	     * @param @return 参数  
	     * @return DataStatisticsDto 返回类型   
	     * @throws  
	     */  
	    
	@RequestMapping(value = "/viewapi/statisticData/getByDate", method = RequestMethod.GET)
	StatisticsDataDto getByDate(@RequestParam("date") String date);

	
}
