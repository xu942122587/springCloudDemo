package com.ruixun.sincfin.biz.module.message.mapper;

import com.ruixun.sincfin.domain.entity.MessagePushRecordEntity;

public interface MessagePushRecordMapper {
    int insert(MessagePushRecordEntity record);

    int insertSelective(MessagePushRecordEntity record);

    MessagePushRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePushRecordEntity record);

    int updateByPrimaryKey(MessagePushRecordEntity record);
}