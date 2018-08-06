package com.ruixun.sincfin.domain.enums;

public enum  ProductCategoryEnum {

	SELECT_AREA(1, "SELECT_AREA","精选专区",""),
	NEW_USER_AREA(2,"NEW_USER_AREA","新手专区",""),
    ACTIVITY_AREA(3, "ACTIVITY_AREA","活动专区",""),
    SELLOUT_AREA(4, "SELLOUT_AREA","售罄区","");

	ProductCategoryEnum(int id, String code,String name,String icon) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.icon = icon;
		
    }

    private int id;
    private String code;
    private String name;
    private String icon;
   

    public int getId() {
		return id;
	}
    public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getIcon() {
		return icon;
	}
	
}
