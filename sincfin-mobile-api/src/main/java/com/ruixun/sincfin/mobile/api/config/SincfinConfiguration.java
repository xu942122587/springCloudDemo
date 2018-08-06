package com.ruixun.sincfin.mobile.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class SincfinConfiguration {
	
	/**
	 * 是否打开短信发送通道
	 */
	@Value("${sincfin.sms.send.open}")
	private Boolean smsSendOpen;
	
	/**
	 * 是否开启验证，开启验证意味着 打开了短信验证发送通道
	 */
	@Value("${sincfin.sms.code.validate}")
	private Boolean smsCodeValidate;
	
	/**
	 * 关闭短信验证码，是以提供默认短信验证码的方式关闭的
	 */
	@Value("${sincfin.sms.code.defaultValue}")
	private String smsCodeDefaultValue;
	
	/**
	 * 风险评测的url
	 */
	@Value("${sincfin.risk.test.url}")
	private String riskTestUrl;

	public Boolean isSmsCodeValidate() {
		return smsCodeValidate;
	}

	public String getSmsCodeDefaultValue() {
		return smsCodeDefaultValue;
	}

	public String getRiskTestUrl(String source) {
		return riskTestUrl+"?source="+source;
	}

	public Boolean isOpenSmsSend() {
		return smsSendOpen;
	}
	
	

}
