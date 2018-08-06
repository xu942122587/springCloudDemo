package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.entity.NewsEntity;

public interface NewsMapper {
    int insert(NewsEntity record);

    int insertSelective(NewsEntity record);

    NewsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsEntity record);

    int updateByPrimaryKeyWithBLOBs(NewsEntity record);

    int updateByPrimaryKey(NewsEntity record);
}