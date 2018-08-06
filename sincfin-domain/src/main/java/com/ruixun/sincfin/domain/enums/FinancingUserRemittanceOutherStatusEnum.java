package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinancingUserRemittanceOutherStatusEnum {

	NO_REMITTANCE("no_remittance", "未打款"),
    REMITTANCECHECK("remittanceCheck", "打款审核中"),
    REMITTANCECHECK_YES("remittanceCheck_yes", "打款审核通过"),
    REMITTANCECHECK_NO("remittanceCheck_no", "打款审核未通过"),
    REMITTANCE("remittance", "已打款"),
    ;

	FinancingUserRemittanceOutherStatusEnum(String code, String text) {
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

    public static FinancingUserRemittanceOutherStatusEnum fromCode(String code) {
        for (FinancingUserRemittanceOutherStatusEnum statusEnum : FinancingUserRemittanceOutherStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
