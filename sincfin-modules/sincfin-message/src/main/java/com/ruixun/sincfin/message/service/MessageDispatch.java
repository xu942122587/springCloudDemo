package com.ruixun.sincfin.message.service;

import com.alibaba.fastjson.JSON;
import com.ruixun.sincfin.common.util.DateUtils;
import com.ruixun.sincfin.common.util.StringUtils;
import com.ruixun.sincfin.domain.entity.MessageRecordEntity;
import com.ruixun.sincfin.client.message.PushMessage;
import com.ruixun.sincfin.message.interfaces.MessageRecordService;
import com.ruixun.sincfin.message.interfaces.MessageService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 消息转发中心
 */
@Service
public class MessageDispatch {

    private static final Logger LOG = LoggerFactory.getLogger(MessageDispatch.class);

    @Autowired
    private YmMsnService ymMsnService;

    @Autowired
    private JPushAppService jPushAppService;

    @Autowired
    private MessageRecordService messageRecordService;

    public void send(PushMessage message) {
        MessageRecordEntity record = generateMessageRecordEntity(message);
        messageRecordService.insertSelective(record);

        try {
            MessageService service = null;

            if (PushMessage.MsgTypeEnum.APP.eq(message.getType())) {
                if (PushMessage.MsgChannelEnum.DEFAULT.eq(message.getChannel()) ||
                        PushMessage.MsgChannelEnum.ADVERTISEMENT.eq(message.getChannel())) {
                    service = jPushAppService;
                }
            } else if (PushMessage.MsgTypeEnum.MSN.eq(message.getType())) {
                if (PushMessage.MsgChannelEnum.DEFAULT.eq(message.getChannel()) ||
                        PushMessage.MsgChannelEnum.ADVERTISEMENT.eq(message.getChannel())) {
                    service = ymMsnService;
                }
            }

            if (StringUtils.isEmpty(message.getContent())) {
                record.setStatus(2);
                record.setRemark("发送内容为空！");
                messageRecordService.updateByPrimaryKeySelective(record);
                return;
            }

            if (StringUtils.isNotEmpty(message.getSendTime())) {
                if (DateUtils.parseDateTime(message.getSendTime()).compareTo(new Date()) < 0) {
                    record.setStatus(2);
                    record.setRemark("发送时间小于当前时间！");
                    messageRecordService.updateByPrimaryKeySelective(record);
                    return;
                }
            }

            if (null == service) {
                record.setStatus(2);
                record.setRemark("消息类型暂不支持！");
                messageRecordService.updateByPrimaryKeySelective(record);
                return;
            }

            if (message.getUsers().size() == 1) {
                service.send(message, record);
            } else {
                service.sendBatch(message, record);
            }

            messageRecordService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            record.setStatus(2);
            record.setRemark("发送失败！");
            messageRecordService.updateByPrimaryKeySelective(record);
            LOG.error("发送失败", e);
        }
    }

    private MessageRecordEntity generateMessageRecordEntity(PushMessage message) {
        MessageRecordEntity record = new MessageRecordEntity();
        record.setContent(message.getContent());
        record.setChannel(message.getChannel());
        record.setCustom(message.getCustom());
        record.setPlatform(message.getPlatform());
        record.setType(message.getType());
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        record.setStatus(0);

        if (StringUtils.isNotEmpty(message.getSendTime())) {
            record.setSendTime(DateUtils.parseDateTime(message.getSendTime()));
        }

        if (CollectionUtils.isNotEmpty(message.getUsers())) {
            record.setUsers(JSON.toJSONString(message.getUsers()));
        }

        return record;
    }
}
