package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserRealNameAuthEnum {
	
	UNAUTH("unauth", "未认证 "),
    AUTH("auth", "已认证"),
    ;

	UserRealNameAuthEnum(String code, String text) {
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

    public static UserRealNameAuthEnum fromCode(String code) {
        for (UserRealNameAuthEnum statusEnum : UserRealNameAuthEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
