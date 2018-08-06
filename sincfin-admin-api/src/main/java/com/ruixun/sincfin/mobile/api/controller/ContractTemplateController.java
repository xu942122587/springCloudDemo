package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ContractTemplateClient;
import com.ruixun.sincfin.domain.enums.ContractTemplateStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ContractTemplateQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/29.
 */
@RestController
@RequestMapping("contractTemplate")
public class ContractTemplateController extends BaseController {

    @Resource
    private ContractTemplateClient contractTemplateClient;

    @RequestMapping("list")
    public ApiResponse list() {
        ContractTemplateQuery query = new ContractTemplateQuery();
        query.setStatus(ContractTemplateStatusEnum.NORMAL.getCode());
        return ApiResponse.successApiResponse(contractTemplateClient.listByCondition(query));
    }

}
