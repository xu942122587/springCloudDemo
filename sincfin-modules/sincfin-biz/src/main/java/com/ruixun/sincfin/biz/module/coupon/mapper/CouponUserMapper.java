package com.ruixun.sincfin.biz.module.coupon.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruixun.sincfin.domain.dto.CouponUserDto;
import com.ruixun.sincfin.domain.entity.CouponUserEntity;
import com.ruixun.sincfin.domain.query.CouponUserQuery;

public interface CouponUserMapper {

	int insert(CouponUserEntity record);

	int insertSelective(CouponUserEntity record);

	CouponUserEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CouponUserEntity record);

	int updateByPrimaryKey(CouponUserEntity record);

	List<CouponUserEntity> selectByUserId(Long userId);

	/**
	 * 管理平台用户优惠券列表
	 * 
	 * @param query
	 * @return
	 */
	List<CouponUserDto> listManagerByCondition(CouponUserQuery query);

	List<CouponUserDto> listByUserId(CouponUserQuery query);

	/**
	 * 获取用户优惠券的完整信息
	 * 
	 * @param couponUserId
	 * @return
	 */
	CouponUserDto getCouponById(Long couponUserId);

	/**
	 * 判断是否有可使用的优惠券
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	Boolean canUseCoupon(@Param("userId") Long userId, @Param("productId") Long productId);

	/**
	 * 获取金额最大的优惠券
	 * 
	 * @param userId
	 * @return
	 */
	CouponUserDto getMaxAmountByUserId(Long userId);

	/**
	 * 将过期优惠券状态变为 已失效
	 * 
	 * @return
	 */
	int updateStatusExpired();
	/**
	 * 累计优惠券总额
	 * @param userId
	 * @return
	 */
	BigDecimal getTotalAmountByUserId(Long userId);

}