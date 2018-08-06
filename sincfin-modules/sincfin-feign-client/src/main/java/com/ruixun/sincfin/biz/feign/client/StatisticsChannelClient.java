package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.StatisticsChannelDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.query.StatisticsChannelQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface StatisticsChannelClient {

    @RequestMapping("/statisticsChannel/listPageStatistics")
    Pagination<StatisticsChannelDto> listPageStatistics(@RequestBody StatisticsChannelQuery query);

    @RequestMapping("/statisticsChannel/listUser")
    List<UserDto> listUser(@RequestBody StatisticsChannelQuery query);
    
}


