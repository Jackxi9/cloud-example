package com.xiaoxi.cloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xiaoxi.cloud.service.HystrixFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallBackMethod") //全局降级处理策略，不用一对一的写降级方法
public class OderFeignController {

    @Autowired
    private HystrixFeignService hystrixFeignService;

    @Value("${server.port}")
    private String port;




    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = hystrixFeignService.paymentInfo_OK(id);
        return result;
    }

    /**
     * 模拟异常的达到服务降级的目的
     * @param id
     * @return
     */
    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_Handler",//fallbackMethod =出事了/降级了指定-->paymentInfo_Handler方法兜底
//            //这个表示下面这个方法的线程超过了1.5秒降级处理
//            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")})
    @HystrixCommand //没加fallback属性就找全局的兜底方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = hystrixFeignService.paymentInfo_TimeOut(id);
        return result;
    }
    public String paymentInfo_Handler (@PathVariable("id") Integer id){
        return port+"端口 服务器忙，请稍后再试，id:"+id;
    }

    //下面是全局fallback方法
    public String payment_Global_FallBackMethod (){
        return "Global异常信息，请稍后再试";
    }
}
