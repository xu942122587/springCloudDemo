package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.entity.ManagerAccessLogEntity;

public interface ManagerAccessLogMapper {
    int insert(ManagerAccessLogEntity record);

    int insertSelective(ManagerAccessLogEntity record);

    ManagerAccessLogEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerAccessLogEntity record);

    int updateByPrimaryKey(ManagerAccessLogEntity record);
}