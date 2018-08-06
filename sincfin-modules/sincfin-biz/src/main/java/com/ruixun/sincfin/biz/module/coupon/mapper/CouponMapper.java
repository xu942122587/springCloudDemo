package com.ruixun.sincfin.biz.module.coupon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruixun.sincfin.domain.dto.CouponDto;
import com.ruixun.sincfin.domain.dto.StatisticsCouponDto;
import com.ruixun.sincfin.domain.entity.CouponEntity;
import com.ruixun.sincfin.domain.query.CouponQuery;

public interface CouponMapper {
	int insert(CouponEntity record);

	int insertSelective(CouponEntity record);

	CouponEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CouponEntity record);

	int updateByPrimaryKey(CouponEntity record);

	List<CouponDto> listByCondition(CouponQuery query);

	StatisticsCouponDto getStatisticsCoupon();

	StatisticsCouponDto getStatisticsCouponById(@Param("couponId") Long couponId);

	List<CouponDto> selectByQuery(CouponEntity query);

}