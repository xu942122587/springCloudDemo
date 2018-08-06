package com.ruixun.sincfin.biz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class SincfinConfig {
	
	// API 版本号
    public static final String PAY_FUIOU_API_VERSION = "3.0";

    // API 版本号
    public static final String PAY_FUIOU_API_CHECK_CARD_VERSION = "1.30";

    // API 类型
    public static final String PAY_FUIOU_API_TYPE = "03";

    // API 签名类型
    public static final String PAY_FUIOU_API_SIGN_TP = "MD5";

    public static final String PAY_FUIOU_API_ID_TYPE = "0";

    public static final String PAY_FUIOU_API_CHECK_CARD_URL = "/checkCard/checkCard01.pay";

    // 商户首次协议下单、验证码获取接口
    public static final String PAY_FUIOU_API_ORDER_ACTION_URL = "/apinewpay/orderAction.pay";

    // 首次协议支付接口
    public static final String PAY_FUIOU_API_PAY_ACTION_URL = "/apinewpay/payAction.pay";

    // 商户首次协议下单重发验证码接口
    public static final String PAY_FUIOU_API_MESSAGE_ACTION_URL = "/apinewpay/messageAction.pay";

    // 协议下单、验证码获取接口
    public static final String PAY_FUIOU_API_PRO_PAY_ORDER_ACTION_URL = "/apipropay/orderAction.pay";

    // 协议下单重发验证码接口
    public static final String PAY_FUIOU_API_PRO_PAY_MESSAGE_ACTION_URL = "/apipropay/messageAction.pay";

    // 协议支付接口
    public static final String PAY_FUIOU_API_PRO_PAY_PAY_ACTION_URL = "/apipropay/payAction.pay";

    // 协议解绑接口
    public static final String PAY_FUIOU_API_UNBIND_ACTION_URL = "/cardPro/unbindAction.pay";
    
    // 订单结果查询接口（商户订单号） 
    public static final String PAY_FUIOU_API_QUERY_ACTION_URL = "/checkInfo/checkResult.pay";

    @Value("${sincfin.pay.fuiou.mchnt.cd}")
    private String payFuiouMchntCd ;

    @Value("${sincfin.pay.fuiou.mchnt.key}")
    private String payFuiouMchntKey ;

    @Value("${sincfin.pay.fuiou.api.host}")
    private String payFuiouApiHost;

    @Value("${sincfin.pay.fuiou.back.url}")
    private String payFuiouBackUrl;

	public String getPayFuiouMchntCd() {
		return payFuiouMchntCd;
	}

	public String getPayFuiouMchntKey() {
		return payFuiouMchntKey;
	}

	public String getPayFuiouApiHost() {
		return payFuiouApiHost;
	}

	public String getPayFuiouBackUrl() {
		return payFuiouBackUrl;
	}
}
