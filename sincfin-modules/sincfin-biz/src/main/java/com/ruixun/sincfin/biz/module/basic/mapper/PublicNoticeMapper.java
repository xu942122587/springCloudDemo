package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.entity.PublicNoticeEntity;

public interface PublicNoticeMapper {
    int insert(PublicNoticeEntity record);

    int insertSelective(PublicNoticeEntity record);

    PublicNoticeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PublicNoticeEntity record);

    int updateByPrimaryKeyWithBLOBs(PublicNoticeEntity record);

    int updateByPrimaryKey(PublicNoticeEntity record);
}