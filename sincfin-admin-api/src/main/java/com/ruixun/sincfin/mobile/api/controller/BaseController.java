package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.domain.dto.ManagerPermissionDto;
import com.ruixun.sincfin.domain.dto.ManagerUserContextDto;
import com.ruixun.sincfin.domain.dto.ManagerUserDto;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.mobile.api.context.RequestContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by tiantiea on 2018/5/20.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected StringRedisTemplate redisTemplate;

    protected ManagerUserDto getContextManagerUser() {
        String token = RequestContext.getRequest().getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
        ManagerUserContextDto managerUserContextDto = null;
        if (StringUtils.isNotEmpty(token)) {
            String json = redisTemplate.opsForValue().get(RedisConstants.getUserManagerTokenKey(token));
            if (StringUtils.isNotEmpty(json)) {
                managerUserContextDto = JacksonUtil.readValue(json, ManagerUserContextDto.class);
            } else {
                return null;
            }
        }
        return managerUserContextDto.getManagerUser();
    }

    protected void refreshManagerPermission(Long roleId, List<ManagerPermissionDto> managerPermissionDtoList) {

        if (CollectionUtils.isEmpty(managerPermissionDtoList)) {
            return;
        }
        Set<String> permissionUrlSet = managerPermissionDtoList.stream().map(
                dto -> dto.getUrl()).collect(Collectors.toSet());

        redisTemplate.opsForValue().set(RedisConstants.getManagerUserPermissionKey(roleId),
                JacksonUtil.toJson(permissionUrlSet),
                RedisConstants.MANAGER_USER_PERMISSION_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    protected Long getContextManagerUserId() {
        ManagerUserDto managerUserDto = getContextManagerUser();
        if (managerUserDto == null) {
            return null;
        }
        return managerUserDto.getId();
    }


    protected void clearAuthToken() {
        String token = RequestContext.getRequest().getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            redisTemplate.delete(RedisConstants.getUserManagerTokenKey(token));
        }
    }


}
