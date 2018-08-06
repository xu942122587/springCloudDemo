package com.ruixun.sincfin.biz.module.manager.mapper;

import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.entity.ManagerPermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerPermissionMapper {
    int insert(ManagerPermissionEntity record);

    int insertSelective(ManagerPermissionEntity record);

    ManagerPermissionEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerPermissionEntity record);

    int updateByPrimaryKey(ManagerPermissionEntity record);

    /**
     * 构建菜单树所需数据
     * @return
     */
    List<ManagerPermissionDto> listMenu();

    /**
     * 获取用户权限信息
     * @return
     */
    List<ManagerPermissionDto> listByManagerUserId(@Param("managerUserId") Long managerUserId,
                                                   @Param("type") String type);
    /**
     * 获取角色权限信息
     * @return
     */
    List<ManagerPermissionDto> listByRoleId(@Param("roleId") Long roleId, @Param("type") String type);

    /**
     * 根据菜单权限获取节点下的功能权限
     * @return
     */
    List<ManagerPermissionDto> listActionByParent(@Param("parentIdList") List<Long> parentIdList);



}