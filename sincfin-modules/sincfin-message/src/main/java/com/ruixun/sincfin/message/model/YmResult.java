package com.ruixun.sincfin.message.model;

/**
 * 亿美短信返回格式
 */
public class YmResult {
    private String mobile;
    private String smsId;
    private String customSmsId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getCustomSmsId() {
        return customSmsId;
    }

    public void setCustomSmsId(String customSmsId) {
        this.customSmsId = customSmsId;
    }
}
