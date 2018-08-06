package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AdvertisementPositionEnum {
	
	ADVERTISEMENT("advertisement", "广告 "),
	BANNER("banner", "banner 图"),
	DIALOG("dialog", "弹出框"),
	NAVIGATION("navigation", "首页导航"),
	START_PAGE("startPage", "启动页");
	
	
	AdvertisementPositionEnum(String code, String text) {
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

    public static AdvertisementPositionEnum fromCode(String code) {
        for (AdvertisementPositionEnum statusEnum : AdvertisementPositionEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
