package com.ruixun.sincfin.mobile.api.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixun.sincfin.biz.feign.client.OrderClient;
import com.ruixun.sincfin.domain.dto.LoanAgreementDto;
import com.ruixun.sincfin.domain.dto.OrderDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.mobile.api.annotation.Auth;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {
	
	@Resource
    private OrderClient orderClient;
	
	/**
     * 投资详情
     * @return
     */
	@Auth
    @RequestMapping(value="details",method = RequestMethod.GET)
    public ApiResponse details(Long id) {
    	OrderDto order = orderClient.getById(id);
        return ApiResponse.successApiResponse(order);
    }
    
    
    
    /**
     * 借款协议
     * @return
     */
	@Auth
    @RequestMapping(value="loan/agreement",method = RequestMethod.GET)
    public ApiResponse loanAgreement(Long id) {
    	LoanAgreementDto loanAgreementDto = orderClient.loanAgreement(id);
        return ApiResponse.successApiResponse(loanAgreementDto);
    }

}
