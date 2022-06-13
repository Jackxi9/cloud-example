package com.xiaoxi.cloud.cotroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持Nacos的动态刷新功能
public class ConfigController {
    /**
     * 注意大坑，新版SpringBoot不支持bootstrap.yml文件，需要添加pom
     */
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("/configInfo")
    public boolean getConfigInfo(){
        return useLocalCache;
    }
}
