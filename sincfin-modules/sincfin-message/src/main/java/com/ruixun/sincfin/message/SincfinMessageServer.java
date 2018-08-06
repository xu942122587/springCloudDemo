package com.ruixun.sincfin.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.ruixun.sincfin.message.mapper")
public class SincfinMessageServer {

    public static void main(String[] args) {
        SpringApplication.run(SincfinMessageServer.class, args);
    }
}
