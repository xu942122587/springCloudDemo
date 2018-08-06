package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.entity.ManagerMenuEntity;

public interface ManagerMenuMapper {
    int insert(ManagerMenuEntity record);

    int insertSelective(ManagerMenuEntity record);

    ManagerMenuEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerMenuEntity record);

    int updateByPrimaryKey(ManagerMenuEntity record);
}