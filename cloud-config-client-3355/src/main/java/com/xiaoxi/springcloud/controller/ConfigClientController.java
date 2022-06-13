package com.xiaoxi.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //实现刷新功能
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /**
     * 读远端github上的yml
     * @return
     */
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
