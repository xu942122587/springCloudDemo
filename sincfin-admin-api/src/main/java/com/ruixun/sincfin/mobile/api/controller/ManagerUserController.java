package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.biz.feign.client.ManagerPermissionClient;
import com.ruixun.sincfin.biz.feign.client.ManagerRoleClient;
import com.ruixun.sincfin.biz.feign.client.ManagerUserClient;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.util.Assert;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.dto.ManagerRoleDto;
import com.ruixun.sincfin.domain.dto.ManagerUserContextDto;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.enums.ManagerRoleStatusEnum;
import com.ruixun.sincfin.domain.enums.ManagerUserStatusEnum;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.domain.protocol.response.ApiResponse;
import com.ruixun.sincfin.domain.query.ManagerUserQuery;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/5/20.
 */
@RestController
@RequestMapping("/managerUser")
public class ManagerUserController extends BaseController {

    @Resource
    private ManagerUserClient managerUserClient;
    @Resource
    private ManagerRoleClient managerRoleClient;
    @Resource
    private ManagerPermissionClient managerPermissionClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ApiResponse login(String userName, String password) {

        Assert.assertNotNull(userName);
        Assert.assertNotNull(password);

        ManagerUserDto managerUserDto = managerUserClient.getByUserName(userName);

        Assert.assertNotNull(managerUserDto, ApiStatusEnum.MANAGER_USER_NAME_NOT_EXIST);
        Assert.assertNotEquals(DigestUtils.md5Hex(password), managerUserDto.getPassword(), ApiStatusEnum.MANAGER_PASSWORD_ERROR);
        Assert.assertNotEquals(managerUserDto.getStatus(), ManagerUserStatusEnum.ENABLED.getCode(),
                ApiStatusEnum.MANAGER_USER_STATUS_NOT_ENABLED);

        if (managerUserDto.getRoleId() != null) {
            ManagerRoleDto managerRoleDto = managerRoleClient.getById(managerUserDto.getRoleId());
            Assert.assertNotEquals(managerRoleDto.getStatus(), ManagerRoleStatusEnum.ENABLED.getCode(),
                    ApiStatusEnum.MANAGER_USER_ROLE_STATUS_NOT_ENABLED);
        }
        List<ManagerPermissionDto> managerPermissionDtoList =
                managerPermissionClient.listActionByRoleId(managerUserDto.getRoleId());
        refreshManagerPermission(managerUserDto.getRoleId(), managerPermissionDtoList);

        return ApiResponse.successApiResponse(createManagerUserContext(managerUserDto));

    }

    private ManagerUserContextDto createManagerUserContext(ManagerUserDto managerUserDto) {
        String token = UUID.randomUUID().toString().replace("-", "");
        ManagerUserContextDto managerUserContextDto = new ManagerUserContextDto();

        managerUserDto.setPassword(null);

        managerUserContextDto.setToken(token);
        managerUserContextDto.setManagerUser(managerUserDto);

        redisTemplate.opsForValue().set(RedisConstants.getUserManagerTokenKey(token),
                JacksonUtil.toJson(managerUserContextDto),
                RedisConstants.MANAGER_USER_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);

        return managerUserContextDto;
    }

    @RequestMapping("/logout")
    public ApiResponse logout() {
        clearAuthToken();
        return ApiResponse.successApiResponse();

    }

    @RequestMapping("listPage")
    public ApiResponse listPage(ManagerUserQuery query) {
        return ApiResponse.successApiResponse(managerUserClient.listPageByCondition(query));
    }

    @RequestMapping("insert")
    public ApiResponse insert(ManagerUserDto managerUserDto) {
        Assert.assertNotNull(managerUserDto);
        Assert.assertNotNull(managerUserDto.getUserName());
        Assert.assertNotNull(managerUserDto.getRealName());
        Assert.assertNotNull(managerUserDto.getRoleId());
        Assert.assertNotNull(managerUserDto.getMobile());
        return ApiResponse.successApiResponse(managerUserClient.insert(managerUserDto));
    }

    @RequestMapping("update")
    public ApiResponse update(ManagerUserDto managerUserDto) {
        Assert.assertNotNull(managerUserDto);
        Assert.assertNotNull(managerUserDto.getId());
        Assert.assertNotNull(managerUserDto.getUserName());
        Assert.assertNotNull(managerUserDto.getRoleId());
        Assert.assertNotNull(managerUserDto.getMobile());
        return ApiResponse.successApiResponse(managerUserClient.update(managerUserDto));
    }

    @RequestMapping("updateStatus")
    public ApiResponse updateStatus(ManagerUserDto managerUserDto) {
        Assert.assertNotNull(managerUserDto);
        Assert.assertNotNull(managerUserDto.getId());
        Assert.assertNotNull(managerUserDto.getStatus());
        return ApiResponse.successApiResponse(managerUserClient.updateStatus(managerUserDto));
    }

    @RequestMapping("delete")
    public ApiResponse delete(@RequestParam Long id){
        return ApiResponse.successApiResponse(managerUserClient.delete(id));
    }


    @RequestMapping("initPassword")
    public ApiResponse initPassword(@RequestParam Long id) {
        return ApiResponse.successApiResponse(managerUserClient.initPassword(id));
    }

    @RequestMapping("changePassword")
    public ApiResponse changePassword(@RequestParam Long id,
                                      @RequestParam("newPassword") String newPassword,
                                      @RequestParam("oldPassword") String oldPassword) {

        return ApiResponse.successApiResponse(
                managerUserClient.changePassword(id, newPassword, oldPassword));
    }

}
