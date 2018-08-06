package com.ruixun.sincfin.biz.module.file.config;

public enum FileObjectCategoryEnum {

    FINANCING_USER_ENTERPRISE ("financing_user_enterprise",
            "企业融资客户信息", FileObjectHelper.BUCKET_PUBLIC_TYPE),

    FINANCING_USER_PERSONAL ("financing_user_personal",
            "个人融资客户信息", FileObjectHelper.BUCKET_PUBLIC_TYPE),

    CONTRACT_SERVICE_AGREEMENT ("contract_service_agreement",
            "合同借款人融资服务协议", FileObjectHelper.BUCKET_PUBLIC_TYPE),

    USER_BANK_CHANGE ("user_bank_change",
            "用户更换银行卡", FileObjectHelper.BUCKET_PRIVATE_TYPE),

    ADVERTISEMENT ("advertisement",
            "广告", FileObjectHelper.BUCKET_PUBLIC_TYPE),
    ;

    FileObjectCategoryEnum(String code, String text, String bucketType) {
        this.code = code;
        this.text = text;
        this.bucketType = bucketType;
    }

    private String code;
    private String text;
    private String bucketType;

    public String getText() {
        return text;
    }
    public String getCode() {
        return code;
    }
    public String getBucketType() {
        return bucketType;
    }

    public static FileObjectCategoryEnum fromCode(String code) {
        for (FileObjectCategoryEnum fileObjectCategoryTypeEnum : FileObjectCategoryEnum.values()) {
            if (code.equalsIgnoreCase(fileObjectCategoryTypeEnum.getCode()) ) {
                return fileObjectCategoryTypeEnum;
            }
        }
        return null;
    }

}
