package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum CouponBonusTypeEnum {
	
	ACTIVITY("activity", "活动红包"), 
	CONDITION("condition", "条件红包"),;

	CouponBonusTypeEnum(String code, String text) {
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

	public static CouponBonusTypeEnum fromCode(String code) {
		for (CouponBonusTypeEnum statusEnum : CouponBonusTypeEnum.values()) {
			if (StringUtils.equals(code, statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

}
