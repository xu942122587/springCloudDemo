package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinancingUserRepaymentOtherStatusEnum {

    NO_REPAYMENT("no_repayment", "未回款"),
    REPAYMENT("repayment", "已回款"),
    ;

	FinancingUserRepaymentOtherStatusEnum(String code, String text) {
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

    public static FinancingUserRepaymentOtherStatusEnum fromCode(String code) {
        for (FinancingUserRepaymentOtherStatusEnum statusEnum : FinancingUserRepaymentOtherStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
