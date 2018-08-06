package com.ruixun.sincfin.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.ruixun.sincfin.common.util.http.HttpClientUtil;

public abstract class AbMessageService {

	@Value("${dingtalk.webhook}")
	private String webhook;

	@Value("${dingtalk.enabled:false}")
	private String dToken;

	protected void noticeTxtMsgAdmins(String content) {
		try {
			Map<String, Object> messageJson = buildTxtMsg(content);
			HttpClientUtil.doPosts(webhook, JSON.toJSONString(messageJson));
		} catch (Exception e) {

		}
	}

	/* 构造钉钉txt消息体 */
	private Map<String, Object> buildTxtMsg(String content) {
		Map<String, Object> context = new HashMap<>();
		Map<String, Object> messageJson = new HashMap<>();
		messageJson.put("text", "@18610635264喂110吗？票哒哒短信挂了\r\n" + 
				"![markdown](https://tuyue-service-public.oss-cn-hangzhou.aliyuncs.com/images/2.jpg \"markdown\")\r\n" + 
				""+content);
		messageJson.put("title", "## @18610635264喂110吗？票哒哒短信挂了");
		context.put("markdown", messageJson);
		context.put("msgtype", "markdown");
		Map<String, Object> at = new HashMap<>();
		List<String> atMobiles = new ArrayList<>();
		atMobiles.add("18610635264");
		at.put("atMobiles", atMobiles);
		context.put("at", at);
		return context;
	}

}
