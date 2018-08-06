package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ProductRepaymentStatusEnum {

    NO_REPAYMENT("no_repayment", "未还款"),
    REPAYMENT("repayment", "已还款"),
    ;

	ProductRepaymentStatusEnum(String code, String text) {
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

    public static ProductRepaymentStatusEnum fromCode(String code) {
        for (ProductRepaymentStatusEnum statusEnum : ProductRepaymentStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
