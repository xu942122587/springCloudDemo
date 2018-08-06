package com.ruixun.sincfin.mobile.api.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

/**
 * Created by t on 2016/11/30.
 */
public class CommonParamCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (StringUtils.equalsIgnoreCase(request.getMethod(), "OPTIONS")) {
			return true;
		}

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(ApiStatusEnum.COMMON_PARAM_ERROR.getStatus());
		/*
		 * if (StringUtils.isEmpty(appVersion)) { throw new
		 * ParamException("App 版本号不能为空！"); } if (StringUtils.isEmpty(channelId)) { throw
		 * new ParamException("渠道 ID 不能为空！"); } if (StringUtils.isEmpty(deviceCode)) {
		 * throw new ParamException("设备唯一编号不能为空！"); } if
		 * (DeviceTypeEnum.fromCode(deviceType) == null) { throw new
		 * ParamException("设备类型错误！"); }
		 */
		return super.preHandle(request, response, handler);
	}

	/**
	 * 获取请求Body
	 *
	 * @param request
	 *
	 * @return
	 */
	public static String getBodyString(final ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = cloneInputStream(request.getInputStream());
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Description: 复制输入流</br>
	 *
	 * @param inputStream
	 *
	 * @return</br>
	 */
	public static InputStream cloneInputStream(ServletInputStream inputStream) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}

	private Map<String, String> getSincHeaders(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String appVersion = request.getHeader(ConfigConstants.HTTP_HEADER_APP_VERSION);
		String channelId = request.getHeader(ConfigConstants.HTTP_HEADER_CHANNEL_ID);
		String deviceCode = request.getHeader(ConfigConstants.HTTP_HEADER_DEVICE_CODE);
		String deviceType = request.getHeader(ConfigConstants.HTTP_HEADER_DEVICE_TYPE);
		String token = request.getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
		map.put(ConfigConstants.HTTP_HEADER_APP_VERSION, appVersion);
		map.put(ConfigConstants.HTTP_HEADER_CHANNEL_ID, channelId);
		map.put(ConfigConstants.HTTP_HEADER_DEVICE_CODE, deviceCode);
		map.put(ConfigConstants.HTTP_HEADER_DEVICE_TYPE, deviceType);
		map.put(ConfigConstants.HTTP_HEADER_AUTH_TOKEN, token);
		return map;
	}
}
