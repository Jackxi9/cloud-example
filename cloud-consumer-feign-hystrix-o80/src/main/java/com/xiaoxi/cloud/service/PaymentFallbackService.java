package com.xiaoxi.cloud.service;

import org.springframework.stereotype.Component;

/**
 * 服务降级类，这样就不用写在controller层了
 */
@Component
public class PaymentFallbackService implements HystrixFeignService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK被服务降级，请稍后再试";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut被服务降级，请稍后再试";
    }
}
