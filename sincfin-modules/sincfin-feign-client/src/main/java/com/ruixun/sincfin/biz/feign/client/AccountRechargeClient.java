package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tiantiea on 2018/5/24.
 */
@FeignClient("${biz.feign.name}")
public interface AccountRechargeClient {

    @RequestMapping("/accountRecharge/listPageByCondition")
    Pagination<AccountRechargeDto> listPageByCondition(AccountRechargeQuery query);

    @RequestMapping("/accountRecharge/listManagerPage")
    Pagination<AccountRechargeDto> listManagerPage(AccountRechargeQuery query);


}
