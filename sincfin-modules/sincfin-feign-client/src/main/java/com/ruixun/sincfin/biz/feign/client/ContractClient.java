package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ContractDto;
import com.ruixun.sincfin.domain.dto.ProductDto;
import com.ruixun.sincfin.domain.query.ContractQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tiea on 2018/5/25.
 */
@FeignClient("${biz.feign.name}")
public interface ContractClient {

    @RequestMapping("/contract/insertGenerateProduct")
    ProductDto insertGenerateProduct(@RequestParam("contractId") Long contractId
            , @RequestParam("amount") Long amount);

    @RequestMapping("/contract/update")
    ContractDto update(@RequestBody ContractDto contractDto);

    @RequestMapping("/contract/insert")
    ContractDto insert(@RequestBody ContractDto contractDto);

    @RequestMapping("/contract/getById")
    ContractDto getById(@RequestParam("contractId") Long contractId);

    @RequestMapping("/contract/listPageByCondition")
    Pagination<ContractDto> listPageByCondition(@RequestBody ContractQuery query);

    @RequestMapping("/contract/delete")
    int delete(@RequestParam("contractId") Long contractId);

}
