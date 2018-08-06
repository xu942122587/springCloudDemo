package com.ruixun.sincfin.domain.enums;

public enum UserRistTestEnum {

    CONSERVATIVE(1, "保守型 "),
	ROBUSTNESS(2, "稳健型 "),
	BALANCE(3, "平衡型"),
	POSITIVE(4, "积极型"),
    RADICAL(5, "激进型"),
    ;

	UserRistTestEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    private int code;
    private String text;

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }

    public static UserRistTestEnum fromCode(Integer code) {
    	if(code == null){
    		code = 0 ;
    	}
        for (UserRistTestEnum statusEnum : UserRistTestEnum.values()) {
            if (code==statusEnum.getCode()) {
                return statusEnum;
            }
        }
        return null;
    }

}
