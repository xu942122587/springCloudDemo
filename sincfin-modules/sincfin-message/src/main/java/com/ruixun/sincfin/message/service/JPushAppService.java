package com.ruixun.sincfin.message.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.entity.MessageRecordEntity;
import com.ruixun.sincfin.message.interfaces.MessageService;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;

/**
 * 极光app推送
 */
@Service
@RefreshScope
public class JPushAppService extends AbMessageService implements MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(JPushAppService.class);

	@Value("${message.app.jpush.secrets}")
	private String masterSecret;

	@Value("${message.app.jpush.app-key}")
	private String appKey;

	@Value("${message.app.jpush.apns}")
	private Boolean apns;

	@Override
	public void send(PushMessage message, MessageRecordEntity record) {
		sendBatch(message, record);
	}

	/**
	 * 按用户，所有平台
	 *
	 * @param alias
	 * @param alert
	 * @return
	 */
	private PushPayload buildAliasPayload(List<String> alias, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).build())
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert).build()).build())
				.setOptions(Options.newBuilder().setTimeToLive(86400).setApnsProduction(apns).build()).build();
	}

	/**
	 * 按平台，所有用户
	 *
	 * @param alert
	 * @param platform
	 * @return
	 */
	private PushPayload buildPlatformPayload(String alert, int platform) {
		Platform form = Platform.android_ios();

		if (platform == 1) {
			form = Platform.android();
		} else if (platform == 2) {
			form = Platform.ios();
		}

		return PushPayload.newBuilder().setPlatform(form).setAudience(Audience.all())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).build())
						.addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert).build()).build())
				.setOptions(Options.newBuilder().setTimeToLive(86400).setApnsProduction(apns.equals("1")).build())
				.build();

	}

	@Override
	public void sendBatch(PushMessage message, MessageRecordEntity record) {
		try {
			PushPayload payload;
			JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

			if (message.getPlatform() == 0) {
				payload = buildAliasPayload(message.getUsers(), message.getContent());
			} else {
				payload = buildPlatformPayload(message.getContent(), message.getPlatform());
			}

			if (StringUtils.isBlank(message.getSendTime())) {
				PushResult result = jpushClient.sendPush(payload);
				LOG.info("Got result - " + result);

				record.setStatus(1);
			} else {
				ScheduleResult result = jpushClient.createSingleSchedule("push", message.getSendTime(), payload);
				LOG.info("Got result - " + result);

				record.setStatus(1);
			}
		} catch (Exception e) {
			record.setStatus(2);
			record.setRemark("极光推送失败!");
			noticeTxtMsgAdmins("极光推送失败："+e.getMessage());
			LOG.error("极光推送失败！", e);
		}
	}
}
