package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.dto.ProductTypeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by tiantiea on 2018/6/6.
 */
@FeignClient("${biz.feign.name}")
public interface ProductTypeClient {

    @RequestMapping("/productType/list")
    List<ProductTypeDto> list();

    @RequestMapping("/productType/mapByProductCode")
    Map<String, ProductTypeDto> mapByProductCode();

}
