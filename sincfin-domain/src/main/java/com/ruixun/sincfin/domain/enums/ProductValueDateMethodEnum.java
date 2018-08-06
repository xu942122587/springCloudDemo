package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ProductValueDateMethodEnum {


    T1("t1", "T+1"),
    T2("t2", "T+2"),
    ;

	ProductValueDateMethodEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getText() {
        return "募集满"+text;
    }

    public String getCode() {
        return code;
    }

    public static ProductValueDateMethodEnum fromCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ProductValueDateMethodEnum statusEnum : ProductValueDateMethodEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

    public static String getTextFromCode(String code) {
        ProductValueDateMethodEnum tmpEnum = null;
        String text = null;
        if (StringUtils.isEmpty(code)) {
            return text;
        }
        for (ProductValueDateMethodEnum statusEnum : ProductValueDateMethodEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                tmpEnum = statusEnum;
                break;
            }
        }
        if (tmpEnum != null) {
            text = tmpEnum.getText();
        }
        return text;
    }
}
