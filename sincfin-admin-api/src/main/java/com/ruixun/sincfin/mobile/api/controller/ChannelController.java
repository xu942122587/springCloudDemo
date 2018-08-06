package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ChannelClient;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("channel")
public class ChannelController {

    @Resource
    private ChannelClient channelClient;

    @RequestMapping("list")
    public ApiResponse list(){
        return ApiResponse.successApiResponse(channelClient.listByCondition());
    }

}
