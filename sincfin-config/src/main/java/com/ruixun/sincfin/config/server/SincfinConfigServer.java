package com.ruixun.sincfin.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SincfinConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(SincfinConfigServer.class, args);
    }
}
