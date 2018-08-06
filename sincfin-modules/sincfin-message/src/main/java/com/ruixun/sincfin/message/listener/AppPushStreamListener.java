package com.ruixun.sincfin.message.listener;

import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.message.service.MessageDispatch;
import com.ruixun.sincfin.message.sink.AppPushSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * app 消息推送队列监听
 */
@Component
@EnableBinding(AppPushSink.class)
public class AppPushStreamListener {

    @Autowired
    private MessageDispatch dispatch;

    @StreamListener(AppPushSink.INPUT)
    public void sinkMessage(Message<PushMessage> message) {
        dispatch.send(message.getPayload());
    }
}
