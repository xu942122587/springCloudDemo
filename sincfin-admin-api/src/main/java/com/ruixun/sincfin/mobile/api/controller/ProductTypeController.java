package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ProductTypeClient;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("productType")
public class ProductTypeController extends BaseController {

    @Resource
    private ProductTypeClient productTypeClient;

    @RequestMapping("list")
    public ApiResponse list() {
        return ApiResponse.successApiResponse(productTypeClient.list());
    }

}
