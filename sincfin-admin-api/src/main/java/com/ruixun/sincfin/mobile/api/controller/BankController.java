package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.BankClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.BankQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiea on 2018/6/3.
 */
@RestController
@RequestMapping("bank")
public class BankController extends BaseController {

    @Resource
    private BankClient bankClient;

    @RequestMapping("insert")
    public ApiResponse insert(BankDto bankDto) {
        return ApiResponse.successApiResponse(bankClient.insert(bankDto));
    }

    @RequestMapping("update")
    public ApiResponse update(BankDto bankDto) {
        Assert.assertNotNull(bankDto);
        Assert.assertNotNull(bankDto.getId());
        return ApiResponse.successApiResponse(bankClient.update(bankDto));
    }

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        Assert.assertNotNull(id);
        return ApiResponse.successApiResponse(bankClient.getById(id));

    }

    @RequestMapping("listPage")
    public ApiResponse listPage(BankQuery query){
        return ApiResponse.successApiResponse(bankClient.listPageByCondition(query));
    }

}
