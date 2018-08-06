package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 优惠券触发条件
 * @author rx
 *
 */
public enum CouponTriggerConditionEnum {
	
	NEW_REGISTER("new_register", "新注册"), 
	FIRST_INVESTMENT("first_investment", "首次投资"),
	INVITES_500("invite_500", "邀请奖励"),
	FIRST_RECHARGE("first_recharge", "首次充值"),;

	CouponTriggerConditionEnum(String code, String text) {
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

	public static CouponTriggerConditionEnum fromCode(String code) {
		for (CouponTriggerConditionEnum statusEnum : CouponTriggerConditionEnum.values()) {
			if (StringUtils.equals(code, statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

}
