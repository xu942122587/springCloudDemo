package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;
/**
 * 用户优惠券状态
 * @author rx
 *
 */
public enum CouponUserStatusEnum {
	
	USED("used", "已使用"), 
	UNUSED("unused", "未使用"),
	EXPIRED("expired", "已过期"),;

	CouponUserStatusEnum(String code, String text) {
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

	public static CouponUserStatusEnum fromCode(String code) {
		for (CouponUserStatusEnum statusEnum : CouponUserStatusEnum.values()) {
			if (StringUtils.equals(code, statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

}
