package com.munian.springcloud.controller;

import com.munian.springcloud.entities.CommonResult;
import com.munian.springcloud.entities.Payment;
import com.munian.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        log.info("******插入结果："+result);
        if(result > 0){
            return new CommonResult(200,"插入数据库成功，端口："+serverPort,result);
        }else{
            return new CommonResult(404,"插入数据库失败",null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果："+payment+"hh");
        if(payment != null){
            return new CommonResult(200,"查询成功，端口："+serverPort,payment);
        }else{
            return new CommonResult(404,"查询失败，查询id"+id,null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
