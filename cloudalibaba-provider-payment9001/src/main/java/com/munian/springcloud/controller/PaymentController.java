package com.munian.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String srverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPatment(@PathVariable("id") Integer id){
        return "nacos registry, serverport: "+srverPort +"\t id:"+id;
    }
}
