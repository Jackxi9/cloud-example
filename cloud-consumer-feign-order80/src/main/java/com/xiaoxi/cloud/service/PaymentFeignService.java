package com.xiaoxi.cloud.service;

import com.xiaoxi.springcloud.entities.CommonResult;
import com.xiaoxi.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")//找哪个微服务
public interface PaymentFeignService {
    /**
     * 此处调用的是8001服务的查询方法。方法要和8001service层接口方法一样。
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String timeOut();
}
