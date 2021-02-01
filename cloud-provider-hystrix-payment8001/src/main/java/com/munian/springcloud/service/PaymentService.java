package com.munian.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问，ok
     */
    public String paymentInfo_ok(Integer id){
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_ok,id:" + id + "\t" +"hh~~";
    }

    /**
     * 超时访问，演示降级
     * HystrixCommand:一旦调用服务方法失败并抛出了错误信息后,会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法
     * execution.isolation.thread.timeoutInMilliseconds:线程超时时间3秒钟
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeout(Integer id){
        //int timeNumber = 5;
        //int age = 10 / 0;
        try {
            TimeUnit.MILLISECONDS.sleep(3100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + " paymentInfo_timeout,id:" + id + "\t" +"(*^_^*)哈哈~~" +
                "耗时"  +"秒钟";
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池:" + Thread.currentThread().getName() + " 8001系统繁忙或运行错误,请稍后重试,id: " + id + "\t" +"/(ㄒoㄒ)/~~";
    }

    //******服务熔断
    /**
     * 在10秒窗口期中10次请求有6次是请求失败的,断路器将起作用
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期/时间范文
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
            //10秒内，10次失败6次
            }
    )
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();//等价于UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数,请稍后重试,o(╥﹏╥)o id:" + id;
    }
}
