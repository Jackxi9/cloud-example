package com.xiaoxi.cloud.controller;


import com.xiaoxi.cloud.service.PaymentFeignService;
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
public class OderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;



    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String timeOut(){
        //客户端默认等待一秒钟,但是经过测试，新版不是默认等待1秒。
        return paymentFeignService.timeOut();
    }
}
