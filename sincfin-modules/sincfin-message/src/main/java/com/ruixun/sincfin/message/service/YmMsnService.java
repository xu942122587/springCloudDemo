package com.ruixun.sincfin.message.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.common.util.CollectionUtils;
import com.ruixun.sincfin.domain.entity.MessageRecordEntity;
import com.ruixun.sincfin.message.interfaces.MessageService;
import com.ruixun.sincfin.message.model.YmMsnMessage;
import com.ruixun.sincfin.message.util.AES;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 亿美短信
 */
@Service
@RefreshScope
public class YmMsnService extends AbMessageService implements MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(YmMsnService.class);

	@Autowired
	private Environment env;

	@Value("https://oapi.dingtalk.com/robot/send?access_token=4dd22e952b174b99da8d5ccb6d83578c682b5f0c619e4ad0dd0849fefeb3b5ad")
	private String dToken;

	@Value("${message.msn.ym.host}")
	private String host;

	@Value("${message.msn.ym.port}")
	private String port;

	@Value("${message.msn.regex.mobile}")
	private String regex;

	@Value("${message.msn.ym.prefix}")
	private String prefix;

	private String getAppIdByChannel(int channel) {
		return env.getProperty("message.msn.ym.app-id.channel" + channel);
	}

	private String getSecretKeyByChannel(int channel) {
		return env.getProperty("message.msn.ym.secret-key.channel" + channel);
	}

	@Override
	public void send(PushMessage message, MessageRecordEntity record) {
		sendBatch(message, record);
	}

	@Override
	public void sendBatch(PushMessage message, MessageRecordEntity record) {
		// 手机号校验
		List<String> valid = message.getUsers().stream().filter(e -> e.matches(regex)).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(valid)) {
			record.setStatus(2);
			record.setRemark("合法推送用户为空");
			return;
		}

		// 统一加前缀
		message.setContent(prefix + message.getContent());
		message.setUsers(valid);

		request(message, record);
	}

	private void request(PushMessage pushMessage, MessageRecordEntity record) {

		YmMsnMessage message = YmMsnMessage.generate(pushMessage);

		OkHttpClient client = new OkHttpClient();

		String json = JSON.toJSONString(message);

		byte[] data = json.getBytes(Charset.forName("utf-8"));

		data = AES.encrypt(data, getSecretKeyByChannel(pushMessage.getChannel()).getBytes(), AES.ALGORITHM_AEPP);

		Request request = new Request.Builder().url("http://" + host + ":" + port + "/inter/sendBatchOnlySMS")
				.addHeader("appId", getAppIdByChannel(pushMessage.getChannel())).addHeader("encode", "UTF-8")
				.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), data)).build();

		try {
			Response response = client.newCall(request).execute();

			if (response.isSuccessful() && response.header("result").equalsIgnoreCase("success")) {
				record.setStatus(1);

				data = response.body().bytes();
				data = AES.decrypt(data, getSecretKeyByChannel(pushMessage.getChannel()).getBytes(),
						AES.ALGORITHM_AEPP);

				json = new String(data, "UTF-8");
				LOG.info("发送短信返回[" + record.getId() + "]：" + json);
				//
				// List<YmResult> result = JSON.parseArray(json, YmResult.class);

				LOG.info("发送短信成功!");
			} else {
				record.setStatus(2);
				record.setRemark("发送失败！");
				LOG.info("发送短信请求失败！");
				noticeTxtMsgAdmins(response == null ? "" : response.message());
			}
		} catch (Exception e) {
			record.setStatus(2);
			record.setRemark("发送失败，网络异常！");
			noticeTxtMsgAdmins(e.getMessage());
			LOG.info("发送短信请求失败！", e);
		}
	}
}
