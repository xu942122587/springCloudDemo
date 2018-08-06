package com.ruixun.sincfin.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author t
 * @date 2018/4/28 14:22
 */

@SpringBootApplication
@EnableEurekaServer
public class SincfinEurekaServer {

    public static void main(String[] args) {

        SpringApplication.run(SincfinEurekaServer.class, args);

    }

}
