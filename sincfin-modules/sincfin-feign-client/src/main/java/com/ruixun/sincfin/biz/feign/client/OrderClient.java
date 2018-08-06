package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.query.OrderQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.dto.LoanAgreementDto;
import com.ruixun.sincfin.domain.dto.OrderDto;

@FeignClient("${biz.feign.name}")
public interface OrderClient {

	/**
	 * 管理平台投资订单列表
	 * @param query
	 * @return
	 */
	@RequestMapping("/order/listManagerPage")
	Pagination<OrderDto> listManagerPage(@RequestBody OrderQuery query);

	/**
	 * 管理平台幽灵用户投资
	 * @param orderDto
	 * @param orderDto userId
	 * @param orderDto productId
	 * @param orderDto amount
	 * @return
	 */
	@RequestMapping("/order/insertGhostInvestment")
	OrderDto insertGhostInvestment(@RequestBody OrderDto orderDto);

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/order/getById",method = RequestMethod.GET)
	public OrderDto getById(@RequestParam("id") Long id);

	/**
	 * 获取借款协议
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/order/loan/agreement",method =RequestMethod.GET)
	public LoanAgreementDto loanAgreement(@RequestParam("id")Long id);

}
