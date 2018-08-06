package com.ruixun.sincfin.common.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doGet(String url) {
		return executeGet(url);
	}

	public static String doPost(String urls, String body) {
		log.info("请求远程接口地址：{}   \n   请求远程Body:{}\n", urls, body);
		/* https 调https方法 */
		String result = null;
		if (urls.startsWith("https")) {
			result = executeHttpsPost(urls, body);
		} else {
			result = executePost(urls, body);
		}
		log.info("请求远程结果：{}\n", result);
		return result;
	}

	private static String executeHttpsPost(String urls, String body) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext 对象，并且使用我们指定的任务管理初始化
			TrustManager[] tm = { new TrustAnyTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext 对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(urls);

			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			// conn.setDoOutput(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			// 设置请求方法
			conn.setRequestMethod("POST");
			if (!StringUtils.isBlank(body)) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(body.getBytes("UTF-8"));
				outputStream.flush();
				outputStream.close();
			}
			// 从输入流读取数据
			InputStream is = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;

			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			is.close();
			conn.disconnect();
		} catch (Exception e) {
			log.error("error", e);
		}
		return buffer.toString();
	}

	private static String executeGet(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			// The type DefaultHttpClient is deprecated
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpGet);
			String resultContent = new Utf8ResponseHandler().handleResponse(response);
			log.info("result: " + resultContent);
			return resultContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String doPosts(String url, String body) {
		log.info("请求远程接口地址：{}   \n   请求远程Body:{}\n", url, body);
		String result = executePost(url, body);
		log.info("请求远程结果：{}\n", result);
		return result;
	}

	private static String executePost(String url, String body) {
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(body, "UTF-8");
			entity.setContentType("application/json");
			entity.setContentEncoding("UTF-8");
			httpPost.setEntity(entity);
			final HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
			HttpClient httpclient = new DefaultHttpClient(httpParams);
			HttpResponse response = httpclient.execute(httpPost);
			String resultContent = new Utf8ResponseHandler().handleResponse(response);
			return resultContent;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String doGet(String url, Map<String, String> headers) {
		try {
			HttpGet httpGet = new HttpGet(url);
			if (headers != null) {
				Set<String> keys = headers.keySet();
				Iterator<String> i = keys.iterator();
				while (i.hasNext()) {
					String key = i.next();
					httpGet.addHeader(key, headers.get(key));
				}
			}
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpGet);
			String resultContent = new Utf8ResponseHandler().handleResponse(response);
			return resultContent;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static String doPost(String url, Map<String, String> headers, Map<String, String> params) {

		HttpPost httpPost = new HttpPost(url);
		if (headers != null) {
			Set<String> keys = headers.keySet();
			Iterator<String> i = keys.iterator();
			while (i.hasNext()) {
				String key = i.next();
				httpPost.addHeader(key, headers.get(key));
			}
		}
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		if (params != null) {
			Set<String> keys = params.keySet();
			Iterator<String> i = keys.iterator();
			while (i.hasNext()) {
				String key = i.next();
				pairs.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String resultContent = new Utf8ResponseHandler().handleResponse(httpResponse);
			return resultContent;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static String doPost(String url, Map<String, String> headers, String body) {
		return executePost(url, headers, body);
	}

	private static String executePost(String url, Map<String, String> headers, String body) {
		try {
			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				Set<String> keys = headers.keySet();
				Iterator<String> i = keys.iterator();
				while (i.hasNext()) {
					String key = i.next();
					httpPost.addHeader(key, headers.get(key));
				}
			}
			StringEntity entity = new StringEntity(body, "UTF-8");
			entity.setContentType("application/json");
			entity.setContentEncoding("UTF-8");
			httpPost.setEntity(entity);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpPost);
			String resultContent = new Utf8ResponseHandler().handleResponse(response);
			log.info("result: " + resultContent);
			return resultContent;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * utf -8编码
	 */
	static class Utf8ResponseHandler implements ResponseHandler<String> {
		public String handleResponse(final HttpResponse response) throws HttpResponseException, IOException {
			final StatusLine statusLine = response.getStatusLine();
			final HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() >= 300) {
				EntityUtils.consume(entity);
				throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
			}
			return entity == null ? null : EntityUtils.toString(entity, "UTF-8");
		}

	}

	public static String buildUrlParams(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		if (map.size() > 0) {
			for (Object key : map.keySet()) {
				if ("ip".equals(key)) {
					continue;
				}
				sb.append(key + "=");
				if (StringUtils.isEmpty(map.get(key) + "")) {
					sb.append("&");
				} else {
					String value = map.get(key) + "";
					// try {
					// value = URLEncoder.encode(value, "UTF-8");
					// } catch (UnsupportedEncodingException e) {
					// e.printStackTrace();
					// }
					sb.append(value + "&");
				}
			}
		}
		String last = sb.toString();
		if (last.endsWith("&")) {
			last = last.substring(0, last.length() - 1);
		}
		return last;
	}

}
