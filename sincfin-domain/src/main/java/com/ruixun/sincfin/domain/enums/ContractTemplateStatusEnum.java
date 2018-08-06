package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ContractTemplateStatusEnum {

	NORMAL("normal", "正常 "),
    FROZEN("frozen", "冻结"),
    ;

	ContractTemplateStatusEnum(String code, String text) {
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

    public static ContractTemplateStatusEnum fromCode(String code) {
        for (ContractTemplateStatusEnum statusEnum : ContractTemplateStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
