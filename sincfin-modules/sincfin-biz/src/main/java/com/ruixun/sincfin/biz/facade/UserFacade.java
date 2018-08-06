package com.ruixun.sincfin.biz.facade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ruixun.sincfin.biz.module.coupon.mapper.CouponMapper;
import com.ruixun.sincfin.biz.module.coupon.mapper.CouponUserMapper;
import com.ruixun.sincfin.common.util.DateUtils;
import com.ruixun.sincfin.domain.dto.CouponDto;
import com.ruixun.sincfin.domain.entity.CouponEntity;
import com.ruixun.sincfin.domain.entity.CouponUserEntity;
import com.ruixun.sincfin.domain.enums.CouponUserStatusEnum;
import com.ruixun.sincfin.domain.enums.CouponValidityPeriodTypeEnum;

/**
 * @author t
 * @date 2018/5/18 17:45
 */
@Component
public class UserFacade {

	private Logger LOGGER = LoggerFactory.getLogger(UserFacade.class);

	@Resource
	private CouponMapper couponMapper;
	@Resource
	private CouponUserMapper couponUserMapper;

	/* 发送触发红包 */
	public void sendCoupon(Long userId, String type) {
		CouponEntity query = new CouponEntity();
		query.setTriggerCondition(type);
		List<CouponDto> lists = couponMapper.selectByQuery(query);
		if (lists != null && lists.size() > 0) {
			for (CouponDto dto : lists) {
				CouponUserEntity en = new CouponUserEntity();
				en.setCouponId(dto.getId());
				en.setDeletedFlag(false);
				en.setGmtCreate(new Date());
				en.setStatus(CouponUserStatusEnum.UNUSED.getCode());
				en.setUserId(userId);
				if (CouponValidityPeriodTypeEnum.FIXED.getCode().equals(dto.getValidityPeriodType())) {
					en.setGmtValidityStart(new Date());
					en.setGmtValidityEnd(DateUtils.addDay(new Date(), dto.getFixedDays()));
				}
				if (CouponValidityPeriodTypeEnum.RANGE.getCode().equals(dto.getValidityPeriodType())) {
					en.setGmtValidityStart(dto.getGmtValidityStart());
					en.setGmtValidityEnd(dto.getGmtValidityEnd());
				}
				couponUserMapper.insertSelective(en);
			}
		}
	}
}
