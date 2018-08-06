package com.ruixun.sincfin.biz.module.product.mapper;

import com.ruixun.sincfin.domain.dto.ProductTypeDto;
import com.ruixun.sincfin.domain.entity.ProductTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeMapper {
    int insert(ProductTypeEntity record);

    int insertSelective(ProductTypeEntity record);

    ProductTypeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductTypeEntity record);

    int updateByPrimaryKey(ProductTypeEntity record);

    List<ProductTypeDto> list();

    List<ProductTypeDto> listByCodeList(@Param("codeList") List<String> codeList);

}