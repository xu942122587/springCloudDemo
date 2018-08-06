package com.ruixun.sincfin.common.util.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * https 接口辅助类
 * 类TrustAnyTrustManager.java的实现描述：TODO 类实现描述 
 * @author lizhihui 2015年9月28日 下午3:04:42
 */
public class TrustAnyTrustManager implements X509TrustManager{

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}