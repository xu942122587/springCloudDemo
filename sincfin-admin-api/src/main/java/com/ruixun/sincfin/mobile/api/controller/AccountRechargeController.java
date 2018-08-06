package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.AccountRechargeClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/28.
 */

@RestController
@RequestMapping("accountRecharge")
public class AccountRechargeController extends BaseController {

    @Resource
    private AccountRechargeClient accountRechargeClient;

    @RequestMapping("listManagerPageByUser")
    public ApiResponse listManagerPageByUser(AccountRechargeQuery query) {
        Assert.assertNotNull(query.getUserId());
        return ApiResponse.successApiResponse(accountRechargeClient.listManagerPage(query));

    }

    @RequestMapping("listManagerPage")
    public ApiResponse listManagerPage(AccountRechargeQuery query) {

        return ApiResponse.successApiResponse(accountRechargeClient.listManagerPage(query));

    }




}
