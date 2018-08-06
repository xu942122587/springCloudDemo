package com.ruixun.sincfin.biz.mapper;

import com.ruixun.sincfin.domain.entity.ProductExtend;

public interface ProductExtendMapper {
    int insert(ProductExtend record);

    int insertSelective(ProductExtend record);

    ProductExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductExtend record);

    int updateByPrimaryKey(ProductExtend record);
}