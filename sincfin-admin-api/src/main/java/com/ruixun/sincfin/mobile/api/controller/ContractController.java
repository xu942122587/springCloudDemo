package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ContractClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.ContractDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ContractQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/29.
 */
@RestController
@RequestMapping("contract")
public class ContractController extends BaseController {

    @Resource
    private ContractClient contractClient;

    @RequestMapping("listPage")
    public ApiResponse getById(ContractQuery query) {
        Assert.assertNotNull(query);
        return ApiResponse.successApiResponse(contractClient.listPageByCondition(query));
    }

    @RequestMapping("getById")
    public ApiResponse getById(Long contractId) {
        return ApiResponse.successApiResponse(contractClient.getById(contractId));

    }

    @RequestMapping("insert")
    public ApiResponse insert(ContractDto contractDto) {
        Assert.assertNotNull(contractDto);
        Assert.assertNotNull(contractDto.getFinancingUserId());
        Assert.assertNotNull(contractDto.getContractTemplateId());
        Assert.assertNotNull(contractDto.getRepaymentType());
        Assert.assertNotNull(contractDto.getGmtStart());
        Assert.assertNotNull(contractDto.getGmtEnd());
        Assert.assertNotNull(contractDto.getAmount());

        contractDto.setUsableBalance(contractDto.getAmount());
        contractDto.setBalance(contractDto.getAmount());

        return ApiResponse.successApiResponse(contractClient.insert(contractDto));
    }

    @RequestMapping("update")
    public ApiResponse update(ContractDto contractDto) {
        Assert.assertNotNull(contractDto);
        Assert.assertNotNull(contractDto.getId());
        Assert.assertNotNull(contractDto.getContractTemplateId());
        // 不允许更新
        contractDto.setAmount(null);
        return ApiResponse.successApiResponse(contractClient.update(contractDto));
    }

    @RequestMapping("generateProduct")
    public ApiResponse generateProduct(Long contractId, Long amount) {

        return ApiResponse.successApiResponse(
                contractClient.insertGenerateProduct(contractId, amount));
    }

    @RequestMapping("delete")
    public ApiResponse delete(Long contractId) {

        return ApiResponse.successApiResponse(contractClient.delete(contractId));

    }


}
