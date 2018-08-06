package com.ruixun.sincfin.biz.module.basic.mapper;

import com.ruixun.sincfin.domain.entity.ReturnVisitEntity;

public interface ReturnVisitMapper {
    int insert(ReturnVisitEntity record);

    int insertSelective(ReturnVisitEntity record);

    ReturnVisitEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReturnVisitEntity record);

    int updateByPrimaryKey(ReturnVisitEntity record);
}