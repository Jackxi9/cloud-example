package com.xiaoxi.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//springboot 2020版本 eureka删除了ribbon可以通过@LoadBalancerClient注解来改变LoadBalancer

@SpringBootApplication
@EnableEurekaClient //eureka客户端入住
@EnableFeignClients
public class FeignOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrder80Application.class, args);
    }

}
