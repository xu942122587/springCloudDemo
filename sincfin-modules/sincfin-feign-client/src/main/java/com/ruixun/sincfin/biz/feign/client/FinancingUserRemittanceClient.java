package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.FinancingUserRemittanceDto;
import com.ruixun.sincfin.domain.dto.FinancingUserRepaymentDto;
import com.ruixun.sincfin.domain.query.FinancingUserRemittanceQuery;
import com.ruixun.sincfin.domain.query.FinancingUserRepaymentQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tiantiea on 2018/6/6.
 */
@FeignClient("${biz.feign.name}")
public interface FinancingUserRemittanceClient {

    @RequestMapping("/financingUserRemittance/listPageByCondition")
    Pagination<FinancingUserRemittanceDto> listPageByCondition(@RequestBody FinancingUserRemittanceQuery query);

}
