package com.munian.springcloud.controller;

import com.munian.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SendMessageController {
    @Resource
    private IMessageProvider mssageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return mssageProvider.send();
    }
}
