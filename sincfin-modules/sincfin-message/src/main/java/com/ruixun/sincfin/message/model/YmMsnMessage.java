package com.ruixun.sincfin.message.model;

import com.ruixun.sincfin.client.message.PushMessage;

import java.util.Date;
import java.util.List;

/**
 * 亿美短信发送格式
 */
public class YmMsnMessage {
    private List<String> mobiles;
    private String content;
    private String timerTime;
    private String customSmsId;
    private long requestTime;
    private int requestValidPeriod = 60;

    public static YmMsnMessage generate(PushMessage message) {
        YmMsnMessage ymMsnMessage = new YmMsnMessage();
        ymMsnMessage.setMobiles(message.getUsers());
        ymMsnMessage.setContent(message.getContent());
        ymMsnMessage.setTimerTime(message.getSendTime());
        ymMsnMessage.setCustomSmsId(message.getCustom());
        ymMsnMessage.setRequestTime(new Date().getTime());
        return ymMsnMessage;
    }

    public List<String> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<String> mobiles) {
        this.mobiles = mobiles;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimerTime() {
        return timerTime;
    }

    public void setTimerTime(String timerTime) {
        this.timerTime = timerTime;
    }

    public String getCustomSmsId() {
        return customSmsId;
    }

    public void setCustomSmsId(String customSmsId) {
        this.customSmsId = customSmsId;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public int getRequestValidPeriod() {
        return requestValidPeriod;
    }

    public void setRequestValidPeriod(int requestValidPeriod) {
        this.requestValidPeriod = requestValidPeriod;
    }
}
