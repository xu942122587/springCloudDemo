package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.BankDto;
import com.ruixun.sincfin.domain.query.BankQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiea on 2018/6/3.
 */
@FeignClient("${biz.feign.name}")
public interface BankClient {

    @RequestMapping("/bank/insert")
    BankDto insert(@RequestBody BankDto bankDto);

    @RequestMapping("/bank/update")
    BankDto update(@RequestBody BankDto bankDto);

    @RequestMapping("/bank/getById")
    BankDto getById(@RequestParam("id") Long id);

    @RequestMapping("/bank/listPageByCondition")
    Pagination<BankDto> listPageByCondition(@RequestBody BankQuery query);

}
