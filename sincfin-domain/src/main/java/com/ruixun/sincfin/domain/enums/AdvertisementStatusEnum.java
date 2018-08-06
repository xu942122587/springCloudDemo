package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum AdvertisementStatusEnum {

    SHELVE("shelve", "上架"),
    UNSHELVE("unshelve", "下架"),
    END("end", "已结束"),
    ;


	AdvertisementStatusEnum(String code, String text) {
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

    public static AdvertisementStatusEnum fromCode(String code) {
        for (AdvertisementStatusEnum statusEnum : AdvertisementStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

}
