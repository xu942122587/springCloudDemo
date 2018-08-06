package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.entity.ManagerRolePermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerRolePermissionMapper {
    int insert(ManagerRolePermissionEntity record);

    int insertSelective(ManagerRolePermissionEntity record);

    ManagerRolePermissionEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerRolePermissionEntity record);

    int updateByPrimaryKey(ManagerRolePermissionEntity record);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int insertBatch(List<ManagerRolePermissionEntity> list);
}