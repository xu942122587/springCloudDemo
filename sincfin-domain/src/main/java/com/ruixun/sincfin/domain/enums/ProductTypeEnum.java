package com.ruixun.sincfin.domain.enums;

public enum  ProductTypeEnum {

	NEW_USER("NEW_USER", "新手标"),
    INCREASE_INTEREST("INCREASE_INTEREST", "加息标"),
    COMMON("COMMON", "普通标");

    ProductTypeEnum(String code, String text) {
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

    public static ProductTypeEnum fromCode(String code) {
        for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
            if (code.equalsIgnoreCase(productTypeEnum.getCode()) ) {
                return productTypeEnum;
            }
        }
        return null;
    }
}
