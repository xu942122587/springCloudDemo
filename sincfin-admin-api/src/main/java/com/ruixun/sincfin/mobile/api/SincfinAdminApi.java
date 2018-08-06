package com.ruixun.sincfin.mobile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author t
 * @date 2018/4/28 15:18
 */

@SpringBootApplication(scanBasePackages = {"com.ruixun.sincfin"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.ruixun.sincfin.biz.feign"})
@EnableWebMvc
public class SincfinAdminApi {

    public static void main(String[] args) {

        SpringApplication.run(SincfinAdminApi.class, args);
    }

}
