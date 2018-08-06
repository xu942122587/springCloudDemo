package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AccountRechargeStatusEnum {
	
	UNPAID("unpaid", "未支付"),
	IN_PAYMENT("in_payment", "支付中"),
	SUCCESS("success", "成功"),
    FAILURE("failure", "失败"),
    UNKNOWN("unknown","未知"),
    UNCONFIRMED("unconfirmed","未确认"),
    EXCEPTION("exception","异常");
	
	
	AccountRechargeStatusEnum(String code, String text) {
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

    public static AccountRechargeStatusEnum fromCode(String code) {
        for (AccountRechargeStatusEnum statusEnum : AccountRechargeStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
