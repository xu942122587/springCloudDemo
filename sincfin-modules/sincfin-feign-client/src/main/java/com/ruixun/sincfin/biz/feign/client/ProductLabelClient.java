package com.ruixun.sincfin.biz.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ProductLabelDto;
import com.ruixun.sincfin.domain.query.ProductLabelQuery;

import java.util.List;

/**
 * <p> 项目标签管理 消费接口 </p>
 */
@FeignClient("${biz.feign.name}")
public interface ProductLabelClient {

    @RequestMapping(value="/productLabel/getById")
    ProductLabelDto getById(@RequestParam("id") Long id);

    @RequestMapping("/productLabel/listByCondition")
    List<ProductLabelDto> listByCondition(@RequestBody ProductLabelQuery query);

    @RequestMapping("/productLabel/listPageByCondition")
    Pagination<ProductLabelDto> listPageByCondition(@RequestBody ProductLabelQuery query);

    @RequestMapping("/productLabel/insert")
    ProductLabelDto insert(@RequestBody ProductLabelDto productLabelDto);

    @RequestMapping("/productLabel/update")
    ProductLabelDto update(@RequestBody ProductLabelDto productLabelDto);

    @RequestMapping("/productLabel/delete")
    int delete(@RequestParam("id") Long id);

}
