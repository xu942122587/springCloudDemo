package com.ruixun.sincfin.biz.module.product.service;

import com.google.common.collect.Maps;
import com.ruixun.sincfin.biz.feign.client.ProductTypeClient;
import com.ruixun.sincfin.biz.module.product.mapper.ProductTypeMapper;
import com.ruixun.sincfin.domain.dto.ProductTypeDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/6/6.
 */
@RestController
@RequestMapping("productType")
public class ProductTypeService implements ProductTypeClient {

    @Resource
    private ProductTypeMapper productTypeMapper;

    @RequestMapping("list")
    public List<ProductTypeDto> list() {
        return productTypeMapper.list();
    }

    @RequestMapping("mapByProductCode")
    @Override
    public Map<String, ProductTypeDto> mapByProductCode() {
        Map<String, ProductTypeDto> map = Maps.newHashMap();
        List<ProductTypeDto> productTypeDtoList =  productTypeMapper.list();

        if (CollectionUtils.isEmpty(productTypeDtoList)) {
            return map;
        }
        return productTypeDtoList.stream().collect(
                Collectors.toMap(ProductTypeDto::getCode, Function.identity()));

    }

}
