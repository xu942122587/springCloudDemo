package com.ruixun.sincfin.client.message.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * app 推送消息源
 */
@Component
public interface AppPushSource {

    String OUTPUT = "app-push-output";

    @Output(OUTPUT)
    SubscribableChannel output();

}
