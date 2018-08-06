package com.ruixun.sincfin.mobile.api.interceptor;

import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.exception.ParamException;
import com.ruixun.sincfin.common.exception.TokenExpireException;
import com.ruixun.sincfin.mobile.api.annotation.Auth;
import com.ruixun.sincfin.mobile.api.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by t on 2016/11/30.
 */
@Component
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthCheckInterceptor.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Auth auth = handlerMethod.getMethod().getAnnotation(Auth.class);
            if (auth != null) {
                String token = RequestContext.getRequest().getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
                if (StringUtils.isNotEmpty(token)) {
                    String json = redisTemplate.opsForValue().get(RedisConstants.getUserTokenKey(token));
                    if (StringUtils.isEmpty(json)) {
                        throw new TokenExpireException("token expire!");
                    }
                } else {
                    throw new TokenExpireException("token can not be null!");
                }

            }
        }

        return super.preHandle(request, response, handler);
    }

}
