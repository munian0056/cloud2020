package com.munian.springcloud.controller;

import com.munian.springcloud.entities.CommonResult;
import com.munian.springcloud.entities.Payment;
import com.munian.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/consumer/payment/fegin/timeout")
    public String paymentFeginTimeout(){
        //openfegin--ribbon,客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeginTimeout();
    }

}
