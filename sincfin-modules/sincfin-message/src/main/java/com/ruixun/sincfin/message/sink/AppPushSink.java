package com.ruixun.sincfin.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * app 消息推送队列读取通道
 */
public interface AppPushSink {

    String INPUT = "app-push-input";

    @Input(INPUT)
    SubscribableChannel input();

}
