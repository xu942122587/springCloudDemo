package com.ruixun.sincfin.vdata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.StatisticsAllClient;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

@RestController
@RequestMapping("viewapi/statisticsAll")
public class StatisticsAllController {
	
	@Resource
    private StatisticsAllClient statisticsAllClient;
	
	@RequestMapping("listPageStatiscs")
    public ApiResponse listPageStatiscs(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
        return ApiResponse.successApiResponse(statisticsAllClient.listPageStatiscs());
    }
}
