package com.ruixun.sincfin.domain.enums;

public enum FileObjectBucketEnum {

    SINCFIN_SERVICE_DEV("10", "sincfin-service-dev"),
    SINCFIN_SERVICE_PUBLIC("11", "sincfin-service-public");

    FileObjectBucketEnum(String code, String text) {
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

    public static FileObjectBucketEnum fromCode(String code) {
        for (FileObjectBucketEnum fileObjectBucketEnum : FileObjectBucketEnum.values()) {
            if (code.equalsIgnoreCase(fileObjectBucketEnum.getCode()) ) {
                return fileObjectBucketEnum;
            }
        }
        return null;
    }
}
