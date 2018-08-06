package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ContractDto;
import com.ruixun.sincfin.domain.dto.ContractTemplateDto;
import com.ruixun.sincfin.domain.query.ContractQuery;
import com.ruixun.sincfin.domain.query.ContractTemplateQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tiea on 2018/5/25.
 */
@FeignClient("${biz.feign.name}")
public interface ContractTemplateClient {

    @RequestMapping("/contractTemplate/listByCondition")
    List<ContractTemplateDto> listByCondition(@RequestBody ContractTemplateQuery query);

}
