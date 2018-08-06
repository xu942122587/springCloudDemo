package com.ruixun.sincfin.message.mapper;

import com.ruixun.sincfin.domain.entity.MessageRecordEntity;

public interface MessageRecordMapper {

    int insertSelective(MessageRecordEntity record);

    MessageRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageRecordEntity record);

}
