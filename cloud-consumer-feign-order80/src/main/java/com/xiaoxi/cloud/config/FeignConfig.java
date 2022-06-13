package com.xiaoxi.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     *Logger.Level.FULL;表示开启详细日志。
     */
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
