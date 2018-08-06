package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 优惠券有效期类型
 * @author rx
 *
 */
public enum CouponValidityPeriodTypeEnum {
	
	RANGE("range", "区间"), 
	FIXED("fixed", "固定天数"),;

	CouponValidityPeriodTypeEnum(String code, String text) {
	        this.code = code;
	        this.text = text;
	}

	private String code;
	private String text;

	public String getText() {
		return text;
	}

	public String getCode() {
		return code;
	}

	public static CouponValidityPeriodTypeEnum fromCode(String code) {
		for (CouponValidityPeriodTypeEnum statusEnum : CouponValidityPeriodTypeEnum.values()) {
			if (StringUtils.equals(code, statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

}
