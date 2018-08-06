package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AccountMoneyRecordTypeEnum {
	
	
	WITHDRAW("withdraw", "提现"),
    PRINCIPAL_INTEREST("principal_interest", "归还本息"),
	CASH_BACK("cash_back", "返现"),
	INVESTMENT("investment", "投资"),
	RECHARGE("recharge", "充值"),
	COUPON_RECHARGE("coupon_recharge", "使用优惠券");
	
	
	AccountMoneyRecordTypeEnum(String code, String text) {
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

    public static AccountMoneyRecordTypeEnum fromCode(String code) {
        for (AccountMoneyRecordTypeEnum statusEnum : AccountMoneyRecordTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
