package com.ruixun.sincfin.domain.dto;

import java.util.Date;

public class ProductLabelDto implements java.io.Serializable {

    private static final long serialVersionUID = -6323031537808997591L;

    private Long id;

    private Date gmtCreate;

    private String name;

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
}
