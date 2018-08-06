package com.ruixun.sincfin.mobile.api.controller;

import com.ruixun.sincfin.common.ConfigConstants;
import com.ruixun.sincfin.common.RedisConstants;
import com.ruixun.sincfin.common.exception.BizException;
import com.ruixun.sincfin.common.util.JacksonUtil;
import com.ruixun.sincfin.domain.dto.UserDto;
import com.ruixun.sincfin.domain.protocol.ApiStatusEnum;
import com.ruixun.sincfin.mobile.api.context.RequestContext;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tiantiea on 2018/5/20.
 */


public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected StringRedisTemplate redisTemplate;
    
    
    protected HttpServletRequest getRequest() {
        return  RequestContext.getRequest();
    }

    protected String getToken() {
        String token = getRequest().getHeader(ConfigConstants.HTTP_HEADER_AUTH_TOKEN);
        return token;
    }
    
    /**
     * 获取当前用户id
     * @return
     */
    protected Long getCurrentUserId(){
    	String token = getToken();
    	Long userId = null;
    	if (StringUtils.isNotEmpty(token)) {
            String uid = redisTemplate.opsForValue().get(RedisConstants.getUserTokenKey(token));
            if (StringUtils.isNotEmpty(uid)) {
            	userId = Long.valueOf(uid);
            } else {
                return null;
            }
        }
    	return userId;
    }
    
    
    /**
     * 清除当前用户，推出登陆
     * @return
     */
    protected void clearCurrentUser(){
    	String token = getToken();
    	redisTemplate.delete(RedisConstants.getUserTokenKey(token));
    }
    
    /**
     * 是否登陆
     * @return
     */
    protected boolean isLogin() {
        return getCurrentUserId() != null;
    }
    
    
    /**
     * 断言是否登陆
     * @param userDto
     */
    protected void assertLogin(){
    	if(!isLogin()){
    		throw new BizException(ApiStatusEnum.COMMON_NOT_LOGIN);
    	}
    }
    
}
