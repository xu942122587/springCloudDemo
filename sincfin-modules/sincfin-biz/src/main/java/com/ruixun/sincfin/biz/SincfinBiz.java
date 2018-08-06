package com.ruixun.sincfin.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import feign.Request;

/**
 * @author t
 * @date 2018/4/28 15:18
 */

@SpringBootApplication(scanBasePackages = { "com.ruixun.sincfin" })
@EnableDiscoveryClient
@EnableFeignClients
// @ComponentScan("com.ruixun.sincfin")
@MapperScan(basePackages = "com.ruixun.sincfin.biz.**.mapper")
@EnableTransactionManagement
public class SincfinBiz {

	public static void main(String[] args) {

		SpringApplication.run(SincfinBiz.class, args);
	}

	@Bean
	Request.Options feignOptions() {
		return new Request.Options(/** connectTimeoutMillis **/
				60 * 1000, /** readTimeoutMillis **/
				60 * 1000);
	}
}
