package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.dto.ChannelDto;
import com.ruixun.sincfin.domain.entity.ChannelEntity;

import java.util.List;

public interface ChannelMapper {
    int insert(ChannelEntity record);

    int insertSelective(ChannelEntity record);

    ChannelEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelEntity record);

    int updateByPrimaryKey(ChannelEntity record);

    List<ChannelDto> listByCondition();

}