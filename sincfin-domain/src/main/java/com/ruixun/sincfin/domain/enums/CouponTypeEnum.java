package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 优惠券类型
 * @author rx
 *
 */
public enum CouponTypeEnum {

	BONUS("bonus", "红包"), 
	INTEREST("interest", "加息券"),;

	CouponTypeEnum(String code, String text) {
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

	public static CouponTypeEnum fromCode(String code) {
		for (CouponTypeEnum statusEnum : CouponTypeEnum.values()) {
			if (StringUtils.equals(code, statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

}
