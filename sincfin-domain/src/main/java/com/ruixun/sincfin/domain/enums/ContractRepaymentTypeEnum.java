package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ContractRepaymentTypeEnum {

	MONTHLY_INTEREST("monthly_interest", "按月付息 "),
	PRINCIPAL_INTEREST("principal_interest", "一次性还本付息"),
	AVERAGE_CAPITAL_PLUS_INTEREST("average_capital_plus_interest", "等额本息"),
    ;

	ContractRepaymentTypeEnum(String code, String text) {
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

    public static ContractRepaymentTypeEnum fromCode(String code) {
        for (ContractRepaymentTypeEnum statusEnum : ContractRepaymentTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
