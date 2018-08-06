package com.ruixun.sincfin.client.message.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * msn 推送消息源
 */
@Component
public interface MsnPushSource {

    String OUTPUT = "msn-push-output";

    @Output(OUTPUT)
    SubscribableChannel output();

}
