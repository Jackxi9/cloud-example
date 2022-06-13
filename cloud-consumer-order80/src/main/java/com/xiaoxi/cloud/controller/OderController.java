package com.xiaoxi.cloud.controller;


import com.xiaoxi.springcloud.entities.CommonResult;
import com.xiaoxi.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OderController {

    //调用支付服务的地址
//    public static final String PAYMNET_URL = "http://localhost:8001";不能把地址写固定了。因为有N台服务器在提供此服务。
    /**
     * 功能一样的集群写注册在服务中心的name都注册一样
     * 然后直接写注册中心的name就行。但是一定要记得开启RestTemplate负载均衡功能，不然不知道调用哪个服务器
     * spring.application.name:cloud-payment-service。
     * 相当于对外暴露的就不是微服务的地址了，而是微服务的名称。
     */
    public static final String PAYMNET_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 写入请求
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMNET_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMNET_URL+"/payment/get/"+id,CommonResult.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject(PAYMNET_URL+"/payment/zipkin/", String.class);
        return result;
    }
}
