package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ManagerRoleStatusEnum {

    ENABLED("enabled", "启用"),
    DISABLED("disabled", "停用"),
    ;

	ManagerRoleStatusEnum(String code, String text) {
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

    public static ManagerRoleStatusEnum fromCode(String code) {
        for (ManagerRoleStatusEnum statusEnum : ManagerRoleStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
