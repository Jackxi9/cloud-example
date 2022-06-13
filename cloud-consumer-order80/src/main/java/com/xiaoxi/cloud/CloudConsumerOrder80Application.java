package com.xiaoxi.cloud;

import com.xiaoxi.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//springboot 2020版本 eureka删除了ribbon可以通过@LoadBalancerClient注解来改变LoadBalancer

@SpringBootApplication
@EnableEurekaClient //eureka客户端入住
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MySelfRule.class)
//@LoadBalancerClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MySelfRule.class)
public class CloudConsumerOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrder80Application.class, args);
    }

}
