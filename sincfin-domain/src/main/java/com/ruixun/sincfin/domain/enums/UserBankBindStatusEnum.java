package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserBankBindStatusEnum {

    BIND("bind", "绑定"),
    UNBINDING("unbinding", "解绑中"),
    UNBIND("unbind", "解绑"),
    ;

    UserBankBindStatusEnum(String code, String text) {
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

    public static UserBankBindStatusEnum fromCode(String code) {
        for (UserBankBindStatusEnum statusEnum : UserBankBindStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }
}
