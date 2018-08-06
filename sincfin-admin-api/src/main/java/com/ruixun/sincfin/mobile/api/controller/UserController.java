package com.ruixun.sincfin.mobile.api.controller;


import com.ruixun.sincfin.biz.feign.client.UserClient;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.enums.UserAccountTypeEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;

import com.ruixun.sincfin.domain.query.UserQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author t
 * @date 2018/4/28 16:09
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserClient userClient;

    @RequestMapping("getById")
    public ApiResponse getById(Long id) {
        Assert.assertNotNull(id);
        return ApiResponse.successApiResponse(userClient.getById(id));
    }

    @RequestMapping("listManagerPage")
    public ApiResponse listManagerPage(UserQuery query) {
        query.setAccountType(UserAccountTypeEnum.NORMAL.getCode());
        return ApiResponse.successApiResponse(userClient.listManagerPage(query));
    }

    @RequestMapping("listManagerGhostPage")
    public ApiResponse listManagerGhostPage(UserQuery query) {
        query.setAccountType(UserAccountTypeEnum.GHOST.getCode());
        return ApiResponse.successApiResponse(userClient.listManagerPage(query));
    }

    @RequestMapping("listGhost")
    public ApiResponse listGhost() {
        UserQuery query = new UserQuery();
        query.setPageSize(Integer.MAX_VALUE);
        query.setAccountType(UserAccountTypeEnum.GHOST.getCode());
        return ApiResponse.successApiResponse(userClient.listManagerPage(query).getData());
    }

    @RequestMapping("insertGhost")
    public ApiResponse insertGhost(UserDto userDto) {
        return ApiResponse.successApiResponse(userClient.insertGhost(userDto));
    }


    @RequestMapping("updateMobile")
    public ApiResponse updateMobile(Long userId, String mobile) {
        return ApiResponse.successApiResponse(userClient.updateMobile(userId, mobile));
    }

    @RequestMapping("updateStatus")
    public ApiResponse updateStatus(Long userId, String status) {

        return ApiResponse.successApiResponse(userClient.updateStatus(userId, status));
    }

}