package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum BankStatusEnum {


    SHELVE("shelve", "上架"),
    UNSHELVE("unshelve", "下架"),
    MAINTAINING("maintaining", "维护中"),
    ;

	BankStatusEnum(String code, String text) {
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

    public static BankStatusEnum fromCode(String code) {
        for (BankStatusEnum statusEnum : BankStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
