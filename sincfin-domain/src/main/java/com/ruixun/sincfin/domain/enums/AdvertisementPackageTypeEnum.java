package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AdvertisementPackageTypeEnum {

    BASIC("basic", "基础版"),
    OFFICIAL("official", "正式版"),
    ANDROID("android", "Android版"),
    ;


	AdvertisementPackageTypeEnum(String code, String text) {
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

    public static AdvertisementPackageTypeEnum fromCode(String code) {
        for (AdvertisementPackageTypeEnum statusEnum : AdvertisementPackageTypeEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
