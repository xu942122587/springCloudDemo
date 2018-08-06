package com.ruixun.sincfin.biz.module.order.mapper;

import com.ruixun.sincfin.domain.entity.OrderCouponEntity;

public interface OrderCouponMapper {
    int insert(OrderCouponEntity record);

    int insertSelective(OrderCouponEntity record);

    OrderCouponEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderCouponEntity record);

    int updateByPrimaryKey(OrderCouponEntity record);
}