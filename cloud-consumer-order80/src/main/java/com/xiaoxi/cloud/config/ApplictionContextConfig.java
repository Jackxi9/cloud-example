package com.xiaoxi.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Bena注入类
 */

@Configuration
public class ApplictionContextConfig {

    /**
     *
     * @LoadBalanced 负载均衡注解，负载策略：负载轮询，每次请求换一个轮着来。
     * 这个注解是基于ribbon的，本来要在pom文件里单独引入ribbon，但是我们在引入spring-cloud-starter-netflix-eureka-client
     * 依赖的时候里面整合了ribbon。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
