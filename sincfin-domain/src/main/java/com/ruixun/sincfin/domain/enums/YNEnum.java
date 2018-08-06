package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum YNEnum {

    Y("y", "是"),
    N("n", "否");

    YNEnum(String code, String text) {
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

    public static YNEnum fromCode(String code) {
        for (YNEnum ynEnum : YNEnum.values()) {
            if (StringUtils.equals(code, ynEnum.getCode())) {
                return ynEnum;
            }
        }
        return null;
    }
}
