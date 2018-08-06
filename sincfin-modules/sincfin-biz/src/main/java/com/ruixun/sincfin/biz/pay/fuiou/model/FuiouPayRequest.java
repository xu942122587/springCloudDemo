package com.ruixun.sincfin.biz.pay.fuiou.model;

/**
 * Created by tiantiea on 2018/4/9.
 */
public class FuiouPayRequest {

    // 银行卡验证接口需要 商户流水号
    private String oSsn;

    // 用户 ip
    private String userIp;
    // 商户订单号
    private String mchntOrderId;
    // 用户编号
    private String userId;

    // 交易金额，单位：分
    private String amt;
    // 银行卡号
    private String bankCard;
    // 姓名
    private String name;
    // 证件号
    private String idNo;

    // 银行预存手机号
    private String mobile;

    // 短信验证码
    private String verCd;

    // 富友订单号
    private String orderId;

    // 下单验证码 接口返回的 SIGNPAY 字段
    private String signPay;

    // 协议号
    private String protocolNo;

    public String getOSsn() {
        return oSsn;
    }

    public void setOSsn(String oSsn) {
        this.oSsn = oSsn;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getMchntOrderId() {
        return mchntOrderId;
    }

    public void setMchntOrderId(String mchntOrderId) {
        this.mchntOrderId = mchntOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerCd() {
        return verCd;
    }

    public void setVerCd(String verCd) {
        this.verCd = verCd;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSignPay() {
        return signPay;
    }

    public void setSignPay(String signPay) {
        this.signPay = signPay;
    }

    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }
}
