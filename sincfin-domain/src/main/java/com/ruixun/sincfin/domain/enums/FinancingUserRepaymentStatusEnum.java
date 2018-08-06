package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinancingUserRepaymentStatusEnum {

    NO_REPAYMENT("no_repayment", "未还款"),
    REPAYMENT("repayment", "已还款"),
    ;

	FinancingUserRepaymentStatusEnum(String code, String text) {
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

    public static FinancingUserRepaymentStatusEnum fromCode(String code) {
        for (FinancingUserRepaymentStatusEnum statusEnum : FinancingUserRepaymentStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
