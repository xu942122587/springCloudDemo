package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserAccountTypeEnum {
	
	NORMAL("normal", "正常账户 "),
    GHOST("ghost", "幽灵账户");

	UserAccountTypeEnum(String code, String text) {
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

    public static UserAccountTypeEnum fromCode(String code) {
        for (UserAccountTypeEnum statusEnum : UserAccountTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
