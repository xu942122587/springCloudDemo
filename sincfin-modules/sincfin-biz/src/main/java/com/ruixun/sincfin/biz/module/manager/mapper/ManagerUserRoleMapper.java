package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.entity.ManagerUserRoleEntity;

public interface ManagerUserRoleMapper {
    int insert(ManagerUserRoleEntity record);

    int insertSelective(ManagerUserRoleEntity record);

    ManagerUserRoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerUserRoleEntity record);

    int updateByPrimaryKey(ManagerUserRoleEntity record);
}