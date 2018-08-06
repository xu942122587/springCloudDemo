package com.ruixun.sincfin.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * msn 消息推送队列读取通道
 */
public interface MsnPushSink {

    String INPUT = "msn-push-input";

    @Input(INPUT)
    SubscribableChannel input();

}
