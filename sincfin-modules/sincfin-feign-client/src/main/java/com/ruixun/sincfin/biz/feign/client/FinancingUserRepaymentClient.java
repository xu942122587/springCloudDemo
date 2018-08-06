package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.dto.RemittanceAndReplayDto;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceAndRepaymentQuery;
import com.ruixun.sincfin.domain.query.FinancingUserRepaymentQuery;

/**
 * Created by tiantiea on 2018/6/6.
 */
@FeignClient("${biz.feign.name}")
public interface FinancingUserRepaymentClient {

    @RequestMapping("/financingUserRepayment/listPageByCondition")
    Pagination<FinancingUserRepaymentDto> listPageByCondition(@RequestBody FinancingUserRepaymentQuery query);
    
    @RequestMapping("/financingUserRepayment/listPageSelect")
    Pagination<RemittanceAndReplayDto> listPageSelect(@RequestBody FinancingUserRemittanceAndRepaymentQuery query);
    
    @RequestMapping("/financingUserRepayment/ensureRepaymentById")
    int ensureRepaymentById(@RequestBody Long id);
}
