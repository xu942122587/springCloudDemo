package com.ruixun.sincfin.mobile.api.interceptor;

import com.google.common.collect.Sets;
import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.exception.PermissionException;
import com.ruixun.sincfin.common.exception.TokenExpireException;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.domain.dto.ManagerUserContextDto;
import com.ruixun.sincfin.mobile.api.context.RequestContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by t on 2016/11/30.
 */
@Component
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 未登录可访问接口
    private static Set<String> excludePaths = Sets.newHashSet();

    // 导出功能 token 可从 request param 带过来
    private static Set<String> exportActionPaths = Sets.newHashSet();

    // 所有登录用户都可访问的接口
    private static Set<String> permissionPaths = Sets.newHashSet();

    static {
        excludePaths.add("/managerUser/login");
        excludePaths.add("/fileObject/aliyunCallback");

        permissionPaths.add("/fileObject/getSecurityToken");
        permissionPaths.add("/channel/list");
        permissionPaths.add("/contractTemplate/list");
        permissionPaths.add("/financingUser/list");
        permissionPaths.add("/managerPermission/menuTree");
        permissionPaths.add("/managerPermission/menuTreeByRoleId");
        permissionPaths.add("/managerUser/logout");
        permissionPaths.add("/managerUser/changePassword");
        permissionPaths.add("/product/listSearch");
        permissionPaths.add("/user/listGhost");
        permissionPaths.add("/productLabel/list");
        permissionPaths.add("/productType/list");
        permissionPaths.add("/managerRole/list");


        exportActionPaths.add("/accountWithdraw/exportExcel");
        exportActionPaths.add("/statisticsBill/exportAccountWithdraw");
        exportActionPaths.add("/statisticsBill/exportAccountRecharge");
        exportActionPaths.add("/statisticsBill/exportInvestment");
        exportActionPaths.add("/statisticsBill/exportRepayment");
        exportActionPaths.add("/statisticsBill/exportCoupon");
        exportActionPaths.add("/statisticsBill/exportCashBack");
        exportActionPaths.add("/statisticsBill/exportSellOut");

        exportActionPaths.add("/statisticsChannel/exportListUser");
        exportActionPaths.add("/statisticsChannel/exportListStatistics");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (StringUtils.equalsIgnoreCase(request.getMethod(), "OPTIONS")) {
            return true;
        }
        if (CollectionUtils.isNotEmpty(excludePaths) && excludePaths.contains(request.getRequestURI())) {
            return super.preHandle(request, response, handler);
        }
        String token = RequestContext.getRequest().getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
        if (StringUtils.isEmpty(token) && exportActionPaths.contains(request.getRequestURI())) {
            token = RequestContext.getRequest().getParameter(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
        }
        ManagerUserContextDto managerUserContextDto = null;
        if (StringUtils.isNotEmpty(token)) {
            String json = redisTemplate.opsForValue().get(RedisConstants.getUserManagerTokenKey(token));
            if (StringUtils.isEmpty(json)) {
                throw new TokenExpireException("token expire!");
            }
            managerUserContextDto = JacksonUtil.readValue(json, ManagerUserContextDto.class);
        } else {
            throw new TokenExpireException("token can not be null!");
        }
        if (managerUserContextDto == null) {
            throw new TokenExpireException("token expire!");
        }

        if (permissionPaths.contains(request.getRequestURI())) {
            return super.preHandle(request, response, handler);
        }
        String permissionJson = redisTemplate.opsForValue().get(
                RedisConstants.getManagerUserPermissionKey(managerUserContextDto.getManagerUser().getRoleId()));
        if (StringUtils.isEmpty(permissionJson)) {
            throw new PermissionException("no permission!");
        }
        Set<String> permissionSet = JacksonUtil.readValue(permissionJson, Set.class);
        if (CollectionUtils.isEmpty(permissionSet) || !permissionSet.contains(request.getRequestURI())) {
            throw new PermissionException("no permission!");
        }

        return super.preHandle(request, response, handler);
    }

}
