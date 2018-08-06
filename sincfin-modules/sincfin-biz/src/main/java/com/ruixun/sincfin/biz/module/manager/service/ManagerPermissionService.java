package com.ruixun.sincfin.biz.module.manager.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ruixun.sincfin.biz.feign.client.ManagerPermissionClient;
import com.ruixun.sincfin.biz.module.manager.mapper.ManagerPermissionMapper;
import com.ruixun.sincfin.biz.module.manager.mapper.ManagerRolePermissionMapper;
import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.entity.ManagerRolePermissionEntity;
import com.ruixun.sincfin.domain.enums.ManagerPermissionTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/5/20.
 */
@RestController
@RequestMapping("managerPermission")
public class ManagerPermissionService implements ManagerPermissionClient {

    @Resource
    private ManagerPermissionMapper managerPermissionMapper;
    @Resource
    private ManagerRolePermissionMapper managerRolePermissionMapper;

    @Override
    @RequestMapping("updateRolePermission")
    @Transactional
    public int updateRolePermission(@RequestParam("roleId") Long roleId,
                                    @RequestParam("permissionIdList") List<Long> permissionIdList) {
        managerRolePermissionMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(permissionIdList)) {
            return 0;
        }
//        List<ManagerPermissionDto> listAction = managerPermissionMapper.listActionByParent(permissionIdList);

        List<ManagerRolePermissionEntity> entityList = Lists.newArrayList();
        for (Long permissionId : permissionIdList) {
            ManagerRolePermissionEntity entity = new ManagerRolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setPermissionId(permissionId);
            entityList.add(entity);
        }
//        for (ManagerPermissionDto dto : listAction) {
//            ManagerRolePermissionEntity entity = new ManagerRolePermissionEntity();
//            entity.setRoleId(roleId);
//            entity.setPermissionId(dto.getId());
//            entityList.add(entity);
//        }
        return managerRolePermissionMapper.insertBatch(entityList);
    }

    @Override
    @RequestMapping("listActionByRoleId")
    public List<ManagerPermissionDto> listActionByRoleId(@RequestParam("roleId") Long roleId) {
        // 获取角色菜单
        List<ManagerPermissionDto> managerPermissionDtoList
                = managerPermissionMapper.listByRoleId(roleId, ManagerPermissionTypeEnum.MENU.getCode());
        if (CollectionUtils.isEmpty(managerPermissionDtoList)) {
            return Lists.newArrayList();
        }
        // 根据菜单获取菜单下所拥有的功能
        return managerPermissionMapper.listActionByParent(
                managerPermissionDtoList.stream()
                        .map(dto -> dto.getId()).collect(Collectors.toList()));
    }

    @Override
    @RequestMapping("menuTree")
    public List<ManagerPermissionDto> menuTree(@RequestParam("managerUserId") Long managerUserId) {
        List<ManagerPermissionDto> permissionTree = Lists.newArrayList();

        return buildTree(permissionIdSetByManagerUserId(
                managerUserId, ManagerPermissionTypeEnum.MENU.getCode()));
    }

    @Override
    @RequestMapping("menuTreeByRoleId")
    public List<ManagerPermissionDto> menuTreeByRoleId(@RequestParam("roleId") Long roleId) {
        return buildTree(permissionIdSetByRoleId(
                roleId, ManagerPermissionTypeEnum.MENU.getCode()));
    }

    private List<ManagerPermissionDto> buildTree(Set<Long> permissionIdSet) {

        List<ManagerPermissionDto> permissionTree = Lists.newArrayList();
        List<ManagerPermissionDto> permissionList = managerPermissionMapper.listMenu();

        if (CollectionUtils.isEmpty(permissionList)) {
            return permissionTree;
        }

        Map<Long, ManagerPermissionDto> permissionMap = Maps.newHashMap();
        for (ManagerPermissionDto permissionDto : permissionList) {
            Long parentId = permissionDto.getParentId();
            Long id = permissionDto.getId();
            permissionDto.setChecked(permissionIdSet.contains(id));

            if (!permissionMap.containsKey(id)) {
                permissionMap.put(id, permissionDto);
            }
            if (parentId == null || !permissionMap.containsKey(parentId)) {
                permissionTree.add(permissionDto);
                continue;
            }
            ManagerPermissionDto parentPermissionDto = permissionMap.get(parentId);
            List<ManagerPermissionDto> childList = parentPermissionDto.getChildren();
            if (CollectionUtils.isEmpty(childList)) {
                childList = Lists.newArrayList();
                parentPermissionDto.setChildren(childList);
            }
            childList.add(permissionDto);
        }
        return permissionTree;
    }

    public Set<Long> permissionIdSetByManagerUserId(Long managerUserId, String type) {
        Set<Long> permissionIdSet = Sets.newHashSet();
        List<ManagerPermissionDto> managerPermissionDtoList
                = managerPermissionMapper.listByManagerUserId(managerUserId, type);
        if (CollectionUtils.isEmpty(managerPermissionDtoList)) {
            return permissionIdSet;
        }
        return managerPermissionDtoList.stream()
                .map(dto -> dto.getId()).collect(Collectors.toSet());
    }

    public Set<Long> permissionIdSetByRoleId(Long roleId, String type) {
        Set<Long> permissionIdSet = Sets.newHashSet();
        List<ManagerPermissionDto> managerPermissionDtoList
                = managerPermissionMapper.listByRoleId(roleId, type);
        if (CollectionUtils.isEmpty(managerPermissionDtoList)) {
            return permissionIdSet;
        }
        return managerPermissionDtoList.stream()
                .map(dto -> dto.getId()).collect(Collectors.toSet());
    }
}
