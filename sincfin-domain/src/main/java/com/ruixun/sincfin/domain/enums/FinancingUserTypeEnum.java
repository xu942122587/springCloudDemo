package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinancingUserTypeEnum {

    ENTERPRISE("enterprise", "企业"),
    PERSONAL("personal", "个人"),
    ;

    FinancingUserTypeEnum(String code, String text) {
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

    public static FinancingUserTypeEnum fromCode(String code) {
        for (FinancingUserTypeEnum typeEnum : FinancingUserTypeEnum.values()) {
            if (StringUtils.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}
