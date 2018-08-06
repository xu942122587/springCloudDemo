package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ProductRepaymentClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ProductRepaymentQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiea on 2018/5/31.
 */
@RestController
@RequestMapping("productRepayment")
public class ProductRepaymentController extends BaseController {

    @Resource
    private ProductRepaymentClient productRepaymentClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(ProductRepaymentQuery query) {
        return ApiResponse.successApiResponse(productRepaymentClient.listPageByCondition(query));
    }

    @RequestMapping("listPageByProductId")
    public ApiResponse listPageByProductId(ProductRepaymentQuery query) {
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getProductId());
        return ApiResponse.successApiResponse(productRepaymentClient.listPageByCondition(query));
    }

    @RequestMapping("repayment")
    public ApiResponse repayment(Long productId) {
        Assert.assertNotNull(productId);
        return ApiResponse.successApiResponse(productRepaymentClient.updateRepayment(productId));
    }


}
