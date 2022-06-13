package com.xiaoxi.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {
    /**
     * 正常访问，肯定OK的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK( Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id :"+id+"\t"+"哈哈";
    }

    /**
     * 出现异常，服务降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_Handler",//fallbackMethod =出事了/降级了指定-->paymentInfo_Handler方法兜底
            //这个表示下面这个方法的线程超过了5秒降级处理
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")})//
    public String paymentInfo_TimeOut( Integer id){
//        int age = 2/0;//模拟异常
        try {
            Thread.sleep(3000);//模拟延迟5秒，让它服务降级
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id :"+id+"\t"+"嗨嗨";
    }

    public String paymentInfo_Handler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" 服务器忙请等待...,id :"+id+"\t"+"呜呜";
    }

    //=============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期10秒不能通过，10后的请求尝试通过一下，没有出错就恢复。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//糊涂工具包生成的中间不带-号的UUID

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
