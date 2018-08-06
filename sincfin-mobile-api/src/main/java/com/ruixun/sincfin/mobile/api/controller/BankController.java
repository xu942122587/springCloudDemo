package com.ruixun.sincfin.mobile.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.BankClient;
import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.enums.BankStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.BankQuery;

@RestController
@RequestMapping("bank")
public class BankController {
	

	@Resource
	public BankClient bankClient;
	
	/**
     * 广告list
     *
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResponse list(BankQuery query) {
    	Pagination<BankDto> pageBank = bankClient.listPageByCondition(query);
        return ApiResponse.successApiResponse(pageBank);
    }

}
