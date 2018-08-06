package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum ChannelUserExportTypeEnum {

    ACTIVATE("activate", "激活名单"),
    REGISTER("register", "注册名单"),
    FIRST_INVESTMENT("first_investment", "首投名单"),
    REPEATEDLY_INVESTMENT("repeatedly_investment", "复投名单");


	ChannelUserExportTypeEnum(String code, String text) {
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

    public static ChannelUserExportTypeEnum fromCode(String code) {
        for (ChannelUserExportTypeEnum typeEnum : ChannelUserExportTypeEnum.values()) {
            if (StringUtils.equals(code, typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }

}
