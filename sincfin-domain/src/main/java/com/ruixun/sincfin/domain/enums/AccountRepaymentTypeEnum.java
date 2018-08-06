package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AccountRepaymentTypeEnum {

    PRINCIPAL_INTEREST("principal_interest", "本息"),
    ;

	AccountRepaymentTypeEnum(String code, String text) {
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

    public static AccountRepaymentTypeEnum fromCode(String code) {
        for (AccountRepaymentTypeEnum statusEnum : AccountRepaymentTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
