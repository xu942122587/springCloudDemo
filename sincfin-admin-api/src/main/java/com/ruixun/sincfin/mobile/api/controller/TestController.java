package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.client.message.PushMessageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

//@RestController
//@RefreshScope
public class TestController {

    @Autowired
    private PushMessageClient messageClient;

    @Value("${sincfin.test}")
    private String test;

    /**
     * 消息推送例子
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        String userId = "";
        String mobile = "";
        String content = "";

        // 正常短信
        PushMessage message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
                Arrays.asList(mobile), content);

        messageClient.send(message);

        // app推送
        message = new PushMessage(PushMessage.MsgTypeEnum.APP.getVal(),
                Arrays.asList(userId), content);

        messageClient.send(message);

        // 营销短信
        message = new PushMessage(PushMessage.MsgTypeEnum.MSN.getVal(),
                Arrays.asList(mobile), content, PushMessage.MsgChannelEnum.ADVERTISEMENT.getVal());

        messageClient.send(message);

        // 定时发送
        message.setSendTime("2020-01-01 00:00:00");
        messageClient.send(message);

        return "success";
    }

    /**
     * 配置中心的例子
     *
     * @return
     */
    @GetMapping("/test1")
    public String test1() {
        return test;
    }
}
