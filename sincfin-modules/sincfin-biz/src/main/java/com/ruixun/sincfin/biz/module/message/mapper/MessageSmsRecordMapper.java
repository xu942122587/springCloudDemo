package com.ruixun.sincfin.biz.module.message.mapper;

import com.ruixun.sincfin.domain.entity.MessageSmsRecordEntity;

public interface MessageSmsRecordMapper {
    int insert(MessageSmsRecordEntity record);

    int insertSelective(MessageSmsRecordEntity record);

    MessageSmsRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageSmsRecordEntity record);

    int updateByPrimaryKey(MessageSmsRecordEntity record);
}