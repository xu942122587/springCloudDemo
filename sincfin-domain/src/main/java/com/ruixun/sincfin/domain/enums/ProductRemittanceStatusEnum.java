package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ProductRemittanceStatusEnum {

    NO_REMITTANCE("no_remittance", "未还款"),
    REMITTANCE("remittance", "已还款"),
    ;

	ProductRemittanceStatusEnum(String code, String text) {
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

    public static ProductRemittanceStatusEnum fromCode(String code) {
        for (ProductRemittanceStatusEnum statusEnum : ProductRemittanceStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
