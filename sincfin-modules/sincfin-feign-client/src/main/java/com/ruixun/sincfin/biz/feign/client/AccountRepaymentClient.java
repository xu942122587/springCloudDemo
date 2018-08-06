package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountRechargeDto;
import com.ruixun.sincfin.domain.dto.AccountRepaymentDto;
import com.ruixun.sincfin.domain.query.AccountRechargeQuery;
import com.ruixun.sincfin.domain.query.AccountRepaymentQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tiantiea on 2018/5/24.
 */
@FeignClient("${biz.feign.name}")
public interface AccountRepaymentClient {

    @RequestMapping("/accountRepayment/listPageByCondition")
    Pagination<AccountRepaymentDto> listPageByCondition(AccountRepaymentQuery query);

    /**
     * 还款短信通知任务
     * @return
     */
    @RequestMapping("/accountRepayment/repaymentMSNTask")
    void repaymentMSNTask();

}
