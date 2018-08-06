package com.ruixun.sincfin.mobile.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ruixun.sincfin.common.util.http.HttpClientUtil;
import com.ruixun.sincfin.mobile.api.config.SwitchConfiguration;

@RestController
@RefreshScope
public class TestController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SwitchConfiguration switchConfig;

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@RefreshScope
	public Object banner() {
		Map<String, Object> map = new HashMap<>();
		logger.info(JSON.toJSONString(switchConfig));
		map.put("swich", switchConfig.isCoexist());
		return map;
	}

	public static void main(String[] args) {
		String to = "https://oapi.dingtalk.com/robot/send?access_token=faa021eb9dfdc355b8ff2396a06eeb3f5d1e18b4115258496ae1041c238a5014";
		Map<String, Object> context = new HashMap<>();
		

		Map<String, Object> messageJson = new HashMap<>();
		messageJson.put("text", "@18610635264喂110吗？票哒哒短信挂了\r\n" + 
				"![markdown](https://tuyue-service-public.oss-cn-hangzhou.aliyuncs.com/images/2.jpg \"markdown\")\r\n" + 
				"手机号：1861063524\r\n" + 
				"------------\r\n" + 
				"FO：");
		messageJson.put("title", "## @18610635264喂110吗？票哒哒短信挂了");
		context.put("markdown", messageJson);
		context.put("msgtype", "markdown");
		Map<String, Object> at = new HashMap<>();
		List<String> atMobiles = new ArrayList<>();
		atMobiles.add("18610635264");
		at.put("atMobiles", atMobiles);
		context.put("at", at);
		String token = HttpClientUtil.doPosts(to, JSON.toJSONString(context));
		System.out.println(token);

	}
}
