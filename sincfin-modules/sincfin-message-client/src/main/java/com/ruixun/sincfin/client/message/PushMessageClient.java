package com.ruixun.sincfin.client.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

import com.alibaba.druid.support.json.JSONUtils;
import com.ruixun.sincfin.client.message.source.AppPushSource;
import com.ruixun.sincfin.client.message.source.MsnPushSource;

/**
 * 消息推送客户端
 */
@EnableBinding(value = { AppPushSource.class, MsnPushSource.class })
public class PushMessageClient {
	private Logger LOGGER = LoggerFactory.getLogger(PushMessageClient.class);

	@Autowired
	private AppPushSource appPushSource;

	@Autowired
	private MsnPushSource msnPushSource;

	public void send(PushMessage message) {
		LOGGER.info("消息发送：{}",message==null?"":"type "+message.getType()+" content " +message.getContent());
		if (PushMessage.MsgTypeEnum.APP.eq(message.getType())) {
			appPushSource.output().send(MessageBuilder.withPayload(message).build());
		} else if (PushMessage.MsgTypeEnum.MSN.eq(message.getType())) {
			msnPushSource.output().send(MessageBuilder.withPayload(message).build());
		}
	}
}
