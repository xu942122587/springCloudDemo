package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ManagerRoleClient;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.ManagerRoleDto;
import com.ruixun.sincfin.domain.enums.ManagerRoleStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ManagerRoleQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/30.
 */
@RestController
@RequestMapping("managerRole")
public class ManagerRoleController extends BaseController {

    @Resource
    private ManagerRoleClient managerRoleClient;

    @RequestMapping("listPage")
    public ApiResponse listPage(ManagerRoleQuery query){
        return ApiResponse.successApiResponse(managerRoleClient.listPageByCondition(query));
    }

    @RequestMapping("list")
    public ApiResponse list(){
        ManagerRoleQuery query = new ManagerRoleQuery();
        query.setStatus(ManagerRoleStatusEnum.ENABLED.getCode());
        return ApiResponse.successApiResponse(managerRoleClient.listByCondition(query));
    }

    @RequestMapping("insert")
    public ApiResponse insert(ManagerRoleDto managerRoleDto) {
        Assert.assertNotNull(managerRoleDto);
        Assert.assertNotNull(managerRoleDto.getName());

        return ApiResponse.successApiResponse(managerRoleClient.insert(managerRoleDto));
    }

    @RequestMapping("updateStatus")
    public ApiResponse updateStatus(ManagerRoleDto managerRoleDto) {

        Assert.assertNotNull(managerRoleDto);
        Assert.assertNotNull(managerRoleDto.getId());
        Assert.assertNotNull(managerRoleDto.getStatus());
        return ApiResponse.successApiResponse(managerRoleClient.updateStatus(managerRoleDto));

    }

    @RequestMapping("update")
    public ApiResponse update(ManagerRoleDto managerRoleDto) {

        Assert.assertNotNull(managerRoleDto);
        Assert.assertNotNull(managerRoleDto.getId());
        Assert.assertNotNull(managerRoleDto.getName());
        return ApiResponse.successApiResponse(managerRoleClient.update(managerRoleDto));

    }

    @RequestMapping("delete")
    public ApiResponse delete(Long id) {
        return ApiResponse.successApiResponse(managerRoleClient.delete(id));
    }

}
