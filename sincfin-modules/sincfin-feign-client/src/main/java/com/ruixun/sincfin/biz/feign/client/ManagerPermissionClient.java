package com.ruixun.sincfin.biz.feign.client;

import com.ruixun.sincfin.domain.Pagination;
import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tiantiea on 2018/5/20.
 */
@FeignClient("${biz.feign.name}")
public interface ManagerPermissionClient {

    @RequestMapping("/managerPermission/updateRolePermission")
    int updateRolePermission(@RequestParam("roleId") Long roleId,
                             @RequestParam("permissionIdList") List<Long> permissionIdList);

    /**
     * 获取管理员用户功能权限
     * @param roleId
     * @return
     */
    @RequestMapping("/managerPermission/listActionByRoleId")
    List<ManagerPermissionDto> listActionByRoleId(@RequestParam("roleId") Long roleId);

    /**
     * 获取管理员用户菜单树
     * @param managerUserId
     * @return
     */
    @RequestMapping("/managerPermission/menuTree")
    List<ManagerPermissionDto> menuTree(@RequestParam("managerUserId") Long managerUserId);

    /**
     * 获取角色树
     * @param
     * @return
     */
    @RequestMapping("/managerPermission/menuTreeByRoleId")
    List<ManagerPermissionDto> menuTreeByRoleId(@RequestParam("roleId") Long roleId);

}
