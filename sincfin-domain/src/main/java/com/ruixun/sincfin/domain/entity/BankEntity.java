package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BankEntity implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deletedFlag;

    private String name;
    
    private String alias;

    private String code;
    
    private String icon;

    private Long limitSingle;

    private Long limitDay;

    private Long limitMonth;

    private String status;

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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
        this.status = status == null ? null : status.trim();
    }

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@JsonIgnore
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}