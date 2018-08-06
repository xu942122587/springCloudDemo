package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AccountWithdrawStatusEnum {
	
	
	PENDING("pending", "待审核 "),
    REJECT("reject", "审核拒绝"),
	PASS("pass", "审核通过"),
	SUCCESS("success", "提现成功");
	
	
	AccountWithdrawStatusEnum(String code, String text) {
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

    public static AccountWithdrawStatusEnum fromCode(String code) {
        for (AccountWithdrawStatusEnum statusEnum : AccountWithdrawStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
