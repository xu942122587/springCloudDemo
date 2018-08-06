package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ManagerPermissionTypeEnum {

    MENU ("menu", "菜单"),
    ACTION ("action", "功能"),
    ;

	ManagerPermissionTypeEnum(String code, String text) {
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

    public static ManagerPermissionTypeEnum fromCode(String code) {
        for (ManagerPermissionTypeEnum statusEnum : ManagerPermissionTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
