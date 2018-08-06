package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinancingUserStatusEnum {

	NORMAL("normal", "正常 "),
    FROZEN("frozen", "冻结"),
    ;

	FinancingUserStatusEnum(String code, String text) {
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

    public static FinancingUserStatusEnum fromCode(String code) {
        for (FinancingUserStatusEnum statusEnum : FinancingUserStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
