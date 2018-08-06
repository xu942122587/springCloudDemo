package com.ruixun.sincfin.message.interfaces;

import com.ruixun.sincfin.domain.entity.MessageRecordEntity;

public interface MessageRecordService {

    int insertSelective(MessageRecordEntity record);

    MessageRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageRecordEntity record);

}
