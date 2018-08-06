package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.AccountRepaymentClient;
import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import com.ruixun.sincfin.domain.query.AccountRepaymentQuery;
import com.ruixun.sincfin.domain.query.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/28.
 */

@RestController
@RequestMapping("accountRepayment")
public class AccountRepaymentController extends BaseController {

    @Resource
    private AccountRepaymentClient accountRepaymentClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(AccountRepaymentQuery query) {

        return ApiResponse.successApiResponse(accountRepaymentClient.listPageByCondition(query));
    }

    @RequestMapping("listPageByOrder")
    public ApiResponse listPageByOrder(AccountRepaymentQuery query) {

        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getOrderNum());

        return ApiResponse.successApiResponse(accountRepaymentClient.listPageByCondition(query));
    }

    @RequestMapping("listPageByProduct")
    public ApiResponse listPageByProduct(AccountRepaymentQuery query) {

        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getProductId());

        return ApiResponse.successApiResponse(accountRepaymentClient.listPageByCondition(query));
    }


}
