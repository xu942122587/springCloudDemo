package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderStatusEnum {

    NON_PAYMENT("non_payment", "未支付"),
    PAYMENT_SUCCESS("payment_success", "已支付"),
    REPAYMENT("repayment", "已还款"),
    ;

    OrderStatusEnum(String code, String text) {
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

    public static OrderStatusEnum fromCode(String code) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
