package com.munian.springcloud.service;

import com.munian.springcloud.entities.CommonResult;
import com.munian.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//找这个微服务下面的
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")//这个地址
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/fegin/timeout")
    public String paymentFeginTimeout();
}
