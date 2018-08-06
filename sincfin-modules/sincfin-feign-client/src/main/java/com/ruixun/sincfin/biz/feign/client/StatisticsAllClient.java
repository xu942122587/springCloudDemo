package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruixun.sincfin.domain.dto.StatisticsAllDto;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface StatisticsAllClient {

    @RequestMapping("/viewapi/statisticsAll/listPageStatiscs")
    StatisticsAllDto listPageStatiscs();


}


