package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.AccountWithdrawDto;
import com.ruixun.sincfin.domain.query.AccountWithdrawQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tiea on 2018/5/25.
 */
@FeignClient("${biz.feign.name}")
public interface AccountWithdrawClient {

    @RequestMapping("/accountWithdraw/listPageByCondition")
    Pagination<AccountWithdrawDto> listPageByCondition(@RequestBody AccountWithdrawQuery query);

    @RequestMapping("/accountWithdraw/listManagerPage")
    Pagination<AccountWithdrawDto> listManagerPage(AccountWithdrawQuery query);

    @RequestMapping("/accountWithdraw/auditPass")
    int updateAuditPass(@RequestParam("idList") List<Long> idList, @RequestParam("auditorId") Long auditorId);

    @RequestMapping("/accountWithdraw/auditReject")
    int updateAuditReject(@RequestParam("idList") List<Long> idList, @RequestParam("auditorId") Long auditorId);

    @RequestMapping("/accountWithdraw/auditSuccess")
    int updateAuditSuccess(@RequestParam("idList") List<Long> idList, @RequestParam("auditorId") Long auditorId);

}
