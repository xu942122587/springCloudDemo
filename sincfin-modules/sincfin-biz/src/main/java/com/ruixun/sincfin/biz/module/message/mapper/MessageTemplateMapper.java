package com.ruixun.sincfin.biz.module.message.mapper;

import com.ruixun.sincfin.domain.entity.MessageTemplateEntity;

public interface MessageTemplateMapper {
    int insert(MessageTemplateEntity record);

    int insertSelective(MessageTemplateEntity record);

    MessageTemplateEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageTemplateEntity record);

    int updateByPrimaryKey(MessageTemplateEntity record);
}