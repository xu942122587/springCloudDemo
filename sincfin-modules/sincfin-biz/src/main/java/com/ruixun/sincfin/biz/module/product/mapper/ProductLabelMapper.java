package com.ruixun.sincfin.biz.module.product.mapper;

import com.ruixun.sincfin.domain.entity.ProductLabelEntity;
import com.ruixun.sincfin.domain.query.ProductLabelQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductLabelMapper {
    int insert(ProductLabelEntity record);

    int insertSelective(ProductLabelEntity record);

    ProductLabelEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductLabelEntity record);

    int updateByPrimaryKey(ProductLabelEntity record);

    List<ProductLabelEntity> listByCondition(ProductLabelQuery query);

    int deleteById(@Param("id") Long id);
}