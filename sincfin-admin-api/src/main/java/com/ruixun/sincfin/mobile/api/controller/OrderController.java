package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.OrderClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.OrderQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

    @Resource
    private OrderClient orderClient;


    /**
     * 管理平台投资订单列表
     * @param query
     * @return
     */
    @RequestMapping("listManagerPage")
    public ApiResponse listManagerPage(OrderQuery query) {
        return ApiResponse.successApiResponse(orderClient.listManagerPage(query));
    }

    /**
     * 管理平台用户投资订单列表
     * @param query
     * @return
     */
    @RequestMapping("listManagerPageByUser")
    public ApiResponse listManagerPageByUser(OrderQuery query) {
        Assert.assertNotNull(query.getUserId());
        return ApiResponse.successApiResponse(orderClient.listManagerPage(query));
    }

    /**
     * 管理平台幽灵用户投资
     * @param orderDto
     * @param orderDto userId
     * @param orderDto productId
     * @param orderDto amount
     * @return
     */
    @RequestMapping("ghostInvestment")
    public ApiResponse ghostInvestment(OrderDto orderDto) {
        Assert.assertNotNull(orderDto);
        Assert.assertNotNull(orderDto.getProductId());
        Assert.assertNotNull(orderDto.getUserId());
        Assert.assertNotNull(orderDto.getAmount());
        return ApiResponse.successApiResponse(orderClient.insertGhostInvestment(orderDto));
    }




}
