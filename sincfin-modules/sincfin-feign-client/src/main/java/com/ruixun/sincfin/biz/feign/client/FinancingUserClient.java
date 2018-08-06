package com.ruixun.sincfin.biz.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.query.FinancingUserQuery;

/**
 * @author t
 * @date 2018/5/22 15:16
 */
@FeignClient("${biz.feign.name}")
public interface FinancingUserClient {

	@RequestMapping("/financingUser/listPageByCondition")
	Pagination<FinancingUserDto> listPageByCondition(@RequestBody FinancingUserQuery query);

	@RequestMapping("/financingUser/listByCondition")
	List<FinancingUserDto> listByCondition(@RequestBody FinancingUserQuery query);

	@RequestMapping("/financingUser/getById")
	FinancingUserDto getById(@RequestParam("id") Long id);

	@RequestMapping("/financingUser/insert")
	FinancingUserDto insert(@RequestBody FinancingUserDto financingUserDto);

	@RequestMapping("/financingUser/update")
	FinancingUserDto update(@RequestBody FinancingUserDto financingUserDto);

	@RequestMapping("/financingUser/updateProductSellOut")
	int updateProductSellOut(@RequestBody FinancingUserRemittanceDto financingUserRemittanceDto);

	@RequestMapping("/financingUser/updateProductRepayment")
	int updateProductRepayment(@RequestBody FinancingUserRepaymentDto financingUserRemittanceDto);

	@RequestMapping("/financingUser/updateFinancingRepayment")
	int updateFinancingRepayment(@RequestBody FinancingUserRepaymentDto financingUserRemittanceDto);

	@RequestMapping("/financingUser/updateFinancingRemittance")
	int updateFinancingRemittance(@RequestBody FinancingUserRemittanceDto financingUserRemittanceDto);
}
