package com.ruixun.sincfin.message.interfaces;

import com.ruixun.sincfin.domain.entity.MessageRecordEntity;
import com.ruixun.sincfin.client.message.PushMessage;

/**
 * 消息推送服务接口
 */
public interface MessageService {

    /**
     * 单个推送
     * @param message
     */
    void send(PushMessage message, MessageRecordEntity record);

    /**
     * 批量推送
     * @param message
     */
    void sendBatch(PushMessage message, MessageRecordEntity record);
}
