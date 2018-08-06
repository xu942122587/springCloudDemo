package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ProductStatusEnum {

    WAIT_EDIT("wait_edit", "待编辑"),
    WAIT_SELL("wait_sell", "待销售"),
    ON_SALE("on_sale", "销售中"),
    INTEREST("interest", "计息中"),
    REPAYMENT("repayment", "还款中"),
    END("end", "已结束"),
    ;

	ProductStatusEnum(String code, String text) {
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

    public static ProductStatusEnum fromCode(String code) {
        for (ProductStatusEnum statusEnum : ProductStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
