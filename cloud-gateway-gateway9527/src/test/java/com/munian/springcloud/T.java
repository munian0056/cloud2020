package com.munian.springcloud;

import java.time.ZonedDateTime;

public class T {
    public static void main(String[] args) {
        // 默认时区
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        //2020-12-23T20:52:19.505+08:00[Asia/Shanghai]
    }
}
