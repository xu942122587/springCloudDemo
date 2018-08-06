package com.ruixun.sincfin.mobile.api.controller;

import com.google.common.collect.Lists;
import com.ruixun.sincfin.biz.feign.client.ManagerPermissionClient;
import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/6/26.
 */
@RestController
@RequestMapping("managerPermission")
public class ManagerPermissionController extends BaseController {

    @Resource
    private ManagerPermissionClient managerPermissionClient;

    @RequestMapping("updateRolePermission")
    public ApiResponse updateRolePermission(@RequestParam("roleId") Long roleId,
                                            @RequestParam("permissionIds") String permissionIds) {
        List<Long> permissionIdList = Lists.newArrayList();
        if (StringUtils.isNotEmpty(permissionIds)) {
            permissionIdList = Arrays.asList(permissionIds.split(",")).stream()
                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        }
        int result = managerPermissionClient.updateRolePermission(roleId, permissionIdList);

        List<ManagerPermissionDto> managerPermissionDtoList = managerPermissionClient.listActionByRoleId(roleId);
        refreshManagerPermission(roleId, managerPermissionDtoList);

        return ApiResponse.successApiResponse(result);
    }

    @RequestMapping("menuTree")
    public ApiResponse menuTree() {
        List<ManagerPermissionDto> managerPermissionDtoList
                = managerPermissionClient.menuTree(getContextManagerUserId());
        clearNoPermissionMenu(managerPermissionDtoList);
        return ApiResponse.successApiResponse(managerPermissionDtoList);
    }

    private void clearNoPermissionMenu(List<ManagerPermissionDto> dtoList) {
        Iterator<ManagerPermissionDto> dtoIterator = dtoList.iterator();
        while (dtoIterator.hasNext()) {
            ManagerPermissionDto dto = dtoIterator.next();
            if (dto.getChecked()) {
                continue;
            }
            if (CollectionUtils.isEmpty(dto.getChildren())) {
                dtoIterator.remove();
                continue;
            }
            clearNoPermissionMenu(dto.getChildren());
            if (CollectionUtils.isEmpty(dto.getChildren())) {
                dtoIterator.remove();
            }
        }
    }

    @RequestMapping("menuTreeByRoleId")
    public ApiResponse menuTreeByRoleId(Long roleId) {
        return ApiResponse.successApiResponse(managerPermissionClient.menuTreeByRoleId(roleId));
    }

}
