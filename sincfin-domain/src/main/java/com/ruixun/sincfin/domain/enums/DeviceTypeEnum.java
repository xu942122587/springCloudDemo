package com.ruixun.sincfin.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum DeviceTypeEnum {

    ANDROID("android", "Android"),
    IOS("ios", "iOS"),
    H5("h5", "H5"),
    PC("pc", "pc"),
    ;

    DeviceTypeEnum(String code, String text) {
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

    public static DeviceTypeEnum fromCode(String code) {
        for (DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()) {
            if (StringUtils.equals(code, deviceTypeEnum.getCode())) {
                return deviceTypeEnum;
            }
        }
        return null;
    }
}
