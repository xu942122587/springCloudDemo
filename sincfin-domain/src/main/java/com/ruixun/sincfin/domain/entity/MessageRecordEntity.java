package com.ruixun.sincfin.domain.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageRecordEntity implements Serializable {

    private Long id;
    // 类型，1 - app，2 - msn
    private Integer type;
    // 通道，0 - 默认亿美
    private Integer channel = 0;
    // 消息内容
    private String content;
    // 接受用户
    private String users;
    // 发送时间 单位：yyyy-MM-dd HH:mm:ss
    private Date sendTime;

    private String custom;
    // 平台，1 - android, 2 - ios, 3 - all
    private Integer platform = 0;

    private Integer status = 0;

    private String remark;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
