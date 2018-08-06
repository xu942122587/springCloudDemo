package com.ruixun.sincfin.biz.module.order.mapper;

import com.ruixun.sincfin.domain.entity.OrderProductEntity;

public interface OrderProductMapper {
    int insert(OrderProductEntity record);

    int insertSelective(OrderProductEntity record);

    OrderProductEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderProductEntity record);

    int updateByPrimaryKey(OrderProductEntity record);
}