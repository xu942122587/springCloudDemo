package com.ruixun.sincfin.client.message;

import java.util.List;

/**
 * 推送的消息
 */
public class PushMessage {
    // 类型，1 - app，2 - msn
    private Integer type;
    /**
     * 通道，默认 0
     * 0 - (app: 极光，msn: 亿美)
     * 1 - (app: 极光，msn: 亿美营销)
     */
    private Integer channel = 0;
    // 消息内容
    private String content;
    // 接受用户, app推送对应的userId, 短信推送对应的手机号
    private List<String> users;
    // 发送时间 单位：yyyy-MM-dd HH:mm:ss，不填时立即发送，填写时按填写的时间发送
    private String sendTime;
    // 自定义参数，暂时保留
    private String custom;
    // 推送平台，0 - 按用户推送, 1 - android, 2 - ios, 3 - all
    private Integer platform = 0;

    public PushMessage() {
    }

    public PushMessage(Integer type,
                       List<String> users,
                       String content) {
        this.type = type;
        this.users = users;
        this.content = content;
    }

    public PushMessage(Integer type,
                       List<String> users,
                       String content,
                       Integer channel) {
        this.type = type;
        this.users = users;
        this.content = content;
        this.channel = channel;
    }

    public PushMessage(Integer type,
                       Integer channel,
                       String content,
                       List<String> users,
                       String sendTime,
                       String custom,
                       Integer platform) {
        this.type = type;
        this.channel = channel;
        this.content = content;
        this.users = users;
        this.sendTime = sendTime;
        this.custom = custom;
        this.platform = platform;
    }

    public enum MsgChannelEnum {
        // 默认
        DEFAULT(0),
        // 营销广告
        ADVERTISEMENT(1);

        private int val;

        MsgChannelEnum(int val) {
            this.val = val;
        }

        public boolean eq(int val) {
            return this.val == val;
        }

        public int getVal() {
            return this.val;
        }
    }

    public enum MsgTypeEnum {
        APP(1),
        MSN(2);

        private int val;

        MsgTypeEnum(int val) {
            this.val = val;
        }

        public boolean eq(int val) {
            return this.val == val;
        }

        public int getVal() {
            return this.val;
        }
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

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
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
}
