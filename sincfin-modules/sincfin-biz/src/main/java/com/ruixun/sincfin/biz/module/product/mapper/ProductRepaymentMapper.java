package com.ruixun.sincfin.biz.module.product.mapper;

import com.ruixun.sincfin.domain.dto.ProductRepaymentDto;
import com.ruixun.sincfin.domain.entity.ProductRepaymentEntity;
import com.ruixun.sincfin.domain.query.ProductRepaymentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRepaymentMapper {

    int insert(ProductRepaymentEntity record);

    int insertSelective(ProductRepaymentEntity record);

    ProductRepaymentEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductRepaymentEntity record);

    int updateByPrimaryKey(ProductRepaymentEntity record);

    List<ProductRepaymentDto> listByCondition(ProductRepaymentQuery query);

    ProductRepaymentEntity getByProductId(@Param("productId") Long productId);

}