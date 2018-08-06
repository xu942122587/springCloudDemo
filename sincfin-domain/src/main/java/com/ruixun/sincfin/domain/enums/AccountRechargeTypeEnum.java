package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AccountRechargeTypeEnum {
	
	PERSON("person", "个人充值"),
	PLATFORM("platform", "平台返现"),
	INVESTMENT("investment", "充值投资");
	
	
	AccountRechargeTypeEnum(String code, String text) {
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

    public static AccountRechargeStatusEnum fromCode(String code) {
        for (AccountRechargeStatusEnum statusEnum : AccountRechargeStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
