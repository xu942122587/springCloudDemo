package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ManagerUserStatusEnum {

    ENABLED("enabled", "启用"),
    DISABLED("disabled", "停用"),
    ;

	ManagerUserStatusEnum(String code, String text) {
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

    public static ManagerUserStatusEnum fromCode(String code) {
        for (ManagerUserStatusEnum statusEnum : ManagerUserStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
