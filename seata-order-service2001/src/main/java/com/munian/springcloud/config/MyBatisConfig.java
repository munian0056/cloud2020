package com.munian.springcloud.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.munian.springcloud.dao"})
public class MyBatisConfig {


}
