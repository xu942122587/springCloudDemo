package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ContractStatusEnum {

    WAIT_EDIT("wait_edit", "待编辑"),
    EDITED("edited", "编辑完成"),
    ;

	ContractStatusEnum(String code, String text) {
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

    public static ContractStatusEnum fromCode(String code) {
        for (ContractStatusEnum statusEnum : ContractStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
