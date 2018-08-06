package com.ruixun.sincfin.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tiea on 2018/6/3.
 */
public class BankDto implements Serializable {

    private Long id;

    private Date gmtCreate;

    private String name;

    private String code;

    private Long limitSingle;

    private Long limitDay;

    private Long limitMonth;

    private String status;
    
    private String  icon;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getLimitSingle() {
        return limitSingle;
    }

    public void setLimitSingle(Long limitSingle) {
        this.limitSingle = limitSingle;
    }

    public Long getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(Long limitDay) {
        this.limitDay = limitDay;
    }

    public Long getLimitMonth() {
        return limitMonth;
    }

    public void setLimitMonth(Long limitMonth) {
        this.limitMonth = limitMonth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
