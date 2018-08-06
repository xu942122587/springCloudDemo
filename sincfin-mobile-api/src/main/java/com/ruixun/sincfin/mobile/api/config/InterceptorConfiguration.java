package com.ruixun.sincfin.mobile.api.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruixun.sincfin.common.springmvc.RequestLogMdcInterceptor;
import com.ruixun.sincfin.mobile.api.interceptor.AuthCheckInterceptor;
import com.ruixun.sincfin.mobile.api.interceptor.CommonParamCheckInterceptor;
import com.ruixun.sincfin.mobile.api.interceptor.RequestContextInterceptor;

/**
 * Created by tiantiea on 2018/5/20.
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	@Resource
	private AuthCheckInterceptor authCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new RequestLogMdcInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new CommonParamCheckInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(authCheckInterceptor).addPathPatterns("/**");

	}

	// @Bean
	// public FilterRegistrationBean myFilter() {
	// FilterRegistrationBean registration = new FilterRegistrationBean();
	// registration.setFilter(new PdFilter());
	// registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	// registration.addUrlPatterns("/*");
	// registration.setName("pdFilter");
	// registration.setOrder(Integer.MAX_VALUE - 1);
	// return registration;
	// }
}
