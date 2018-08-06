package com.ruixun.sincfin.mobile.api.config;

import com.ruixun.sincfin.mobile.api.interceptor.AuthCheckInterceptor;
import com.ruixun.sincfin.mobile.api.interceptor.RequestContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by tiantiea on 2018/5/20.
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authCheckInterceptor).addPathPatterns("/**");

    }

}
