package com.xiaoxi.cloud;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Application9001 {
    public static void main(String[] args) {
        SpringApplication.run(Application9001.class,args);
    }
}
