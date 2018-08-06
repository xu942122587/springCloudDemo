package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.FinancingUserRemittanceClient;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("financingUserRemittance")
public class FinancingUserRemittanceController extends BaseController {

    @Resource
    private FinancingUserRemittanceClient financingUserRemittanceClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(FinancingUserRemittanceQuery query) {
        return ApiResponse.successApiResponse(financingUserRemittanceClient.listPageByCondition(query));
    }
}
