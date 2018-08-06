package com.ruixun.sincfin.message.listener;

import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.message.service.MessageDispatch;
import com.ruixun.sincfin.message.sink.MsnPushSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * msn 消息推送队列监听
 */
@Component
@EnableBinding(MsnPushSink.class)
public class MsnPushStreamListener {

    @Autowired
    private MessageDispatch messageSender;

    @StreamListener(MsnPushSink.INPUT)
    public void sinkMessage(Message<PushMessage> message) {
        messageSender.send(message.getPayload());
    }
}
