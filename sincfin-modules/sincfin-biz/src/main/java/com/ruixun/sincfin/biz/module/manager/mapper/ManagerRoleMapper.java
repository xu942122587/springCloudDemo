package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.entity.ManagerRoleEntity;
import com.ruixun.sincfin.domain.query.ManagerRoleQuery;

import java.util.List;

public interface ManagerRoleMapper {
    int insert(ManagerRoleEntity record);

    int insertSelective(ManagerRoleEntity record);

    ManagerRoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRoleEntity record);

    int updateByPrimaryKey(ManagerRoleEntity record);

    int deleteById(Long id);

    List<ManagerRoleEntity> listByCondition(ManagerRoleQuery query);


}