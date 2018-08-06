package com.ruixun.sincfin.biz.module.message.mapper;

import com.ruixun.sincfin.domain.entity.MessagePushEntity;

public interface MessagePushMapper {
    int insert(MessagePushEntity record);

    int insertSelective(MessagePushEntity record);

    MessagePushEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessagePushEntity record);

    int updateByPrimaryKey(MessagePushEntity record);
}