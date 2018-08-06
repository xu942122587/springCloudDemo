package com.ruixun.sincfin.mobile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import feign.Request;


/**
 * @author t
 * @date 2018/4/28 15:18
 */

@SpringBootApplication(scanBasePackages = { "com.ruixun.sincfin" })
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.ruixun.sincfin.biz.feign" })
@EnableWebMvc
public class SincfinMobileApi {

	public static void main(String[] args) {

		SpringApplication.run(SincfinMobileApi.class, args);
	}

	@Bean
	Request.Options feignOptions() {
		return new Request.Options(/** connectTimeoutMillis **/
				60 * 1000, /** readTimeoutMillis **/
				60 * 1000);
	}
}
