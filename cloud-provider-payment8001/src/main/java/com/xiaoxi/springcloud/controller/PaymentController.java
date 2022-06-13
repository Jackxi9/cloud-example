package com.xiaoxi.springcloud.controller;

import com.xiaoxi.springcloud.entities.CommonResult;
import com.xiaoxi.springcloud.entities.Payment;
import com.xiaoxi.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * discoveryClient：获得该服务的信息
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获得端口号，以辨认是哪一台服务器处理的请求
     */
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功，serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败，serverPort：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***插入结果：" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功serverPort=" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有记录，查询ID：" + id + "serverPort=" + serverPort, null);
        }
    }

    /**
     *
     * @return 返回服务清单列表
     */
    @GetMapping("/payment/discovery")
    public Object discovery(){
        /**
         * discoveryClient.getServices()：获取注册中有哪些服务name（当然一个name下面可能会有多个微服务。’微服务集群‘）
         */
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***element:"+element);
        }

        /**
         * discoveryClient.getInstances()：获取name"CLOUD-PAYMENT-SERVICE"下的所有微服务实例。
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("服务名字"+instance.getServiceId()+"\tip地址"+instance.getHost()+"\t端口号:"+instance.getPort()+"\turi:"+instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * 写一个接口模拟服务端处理请求业务较长的场景，来测试80客户端的请求超时异常，此方法没有用到下两层
     */
    @GetMapping("/payment/timeout")
    public String timeOut(){
        try {
            TimeUnit.SECONDS.sleep(22);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }

}