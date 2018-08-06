package com.ruixun.sincfin.biz.pay.fuiou.model;

/**
 * Created by tiantiea on 2018/4/9.
 */
public class FuiouPayResponse {

    private String responseCode;
    private String responseMsg;
    /**
     * 是否是首次支付
     */
    private boolean firstPay;

    /**
     *  富有订单 号
     */
    private String orderId;
    
    /*
     *  商户订单号
     */
    private String mchntOrderId;
    
    /**
     * 用户id
     */
    private String userId;
    /**
     * 交易金额
     */
    private String amt;
    /**
     * 协议号
     */
    private String protocolno;
    
    private String bankName;

    private String signPay;
    

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProtocolno() {
		return protocolno;
	}

	public void setProtocolno(String protocolno) {
		this.protocolno = protocolno;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getMchntOrderId() {
		return mchntOrderId;
	}

	public void setMchntOrderId(String mchntOrderId) {
		this.mchntOrderId = mchntOrderId;
	}

	public boolean isFirstPay() {
		return firstPay;
	}

	public void setFirstPay(boolean firstPay) {
		this.firstPay = firstPay;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
