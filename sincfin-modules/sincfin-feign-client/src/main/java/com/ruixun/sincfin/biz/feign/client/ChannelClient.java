package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.dto.ChannelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface ChannelClient {

    @RequestMapping("/channel/list")
    List<ChannelDto> listByCondition();
    
    
    @RequestMapping("/channel/getById")
    ChannelDto getById(Long id);

}
