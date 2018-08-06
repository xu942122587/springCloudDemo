package com.ruixun.sincfin.common.springmvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.common.util.WebHelper;

public class RequestLogMdcInterceptor extends HandlerInterceptorAdapter {
	private final static Logger logger = LoggerFactory.getLogger("postLog");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String unqId = RandomStringUtils.randomNumeric(16);
		MDC.put("req.id", unqId);
		MDC.put("req.uri", request.getRequestURI());
		MDC.put("req.beginTime", System.currentTimeMillis() + "");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("C-INFO	{}	地址：{}	耗时：{}", request.getMethod(), request.getRequestURL(),
				System.currentTimeMillis() - Long.parseLong(MDC.get("req.beginTime")) + "ms");
		logger.info("C-Header	{}", JacksonUtil.toJson(getSincHeaders(request)));
		logger.info("C-Body	{}\n", JacksonUtil.toJson(WebHelper.getRequestParam(request)));
		MDC.remove("req.id");
		MDC.remove("req.uri");
		MDC.remove("req.beginTime");
		super.afterCompletion(request, response, handler, ex);
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
