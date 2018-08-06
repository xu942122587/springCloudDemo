package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.entity.FeedbackEntity;

public interface FeedbackMapper {
	
    int insert(FeedbackEntity record);

    int insertSelective(FeedbackEntity record);

    FeedbackEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FeedbackEntity record);

    int updateByPrimaryKey(FeedbackEntity record);
}