package com.ruixun.sincfin.message.service;

import com.ruixun.sincfin.domain.entity.MessageRecordEntity;
import com.ruixun.sincfin.message.interfaces.MessageRecordService;
import com.ruixun.sincfin.message.mapper.MessageRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Autowired
    private MessageRecordMapper messageRecordMapper;

    @Override
    public int insertSelective(MessageRecordEntity record) {
        return messageRecordMapper.insertSelective(record);
    }

    @Override
    public MessageRecordEntity selectByPrimaryKey(Long id) {
        return messageRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MessageRecordEntity record) {
        return messageRecordMapper.updateByPrimaryKeySelective(record);
    }
}
