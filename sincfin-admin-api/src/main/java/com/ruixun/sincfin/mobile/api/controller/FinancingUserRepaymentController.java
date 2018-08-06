package com.ruixun.sincfin.mobile.api.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.FinancingUserRepaymentClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceAndRepaymentQuery;
import com.ruixun.sincfin.domain.query.FinancingUserRepaymentQuery;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("financingUserRepayment")
public class FinancingUserRepaymentController extends BaseController {

    @Resource
    private FinancingUserRepaymentClient financingUserRepaymentClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(FinancingUserRepaymentQuery query) {
        return ApiResponse.successApiResponse(financingUserRepaymentClient.listPageByCondition(query));
    }

    @RequestMapping("listPageByContract")
    public ApiResponse listPageByContract(FinancingUserRepaymentQuery query) {
        Assert.assertNotNull(query);
        Assert.assertNotNull(query.getContractId());
        return ApiResponse.successApiResponse(financingUserRepaymentClient.listPageByCondition(query));
    }
    
    @RequestMapping("listPageSelect")
    public ApiResponse listPageSelect(FinancingUserRemittanceAndRepaymentQuery query) {
    	return ApiResponse.successApiResponse(financingUserRepaymentClient.listPageSelect(query));
    }
    
    @RequestMapping("ensureRepaymentById")
    public ApiResponse ensureRepaymentById(Long id) {
    	return ApiResponse.successApiResponse(financingUserRepaymentClient.ensureRepaymentById(id));
    }

}
