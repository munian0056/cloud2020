package com.munian.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//全参
@NoArgsConstructor//空参
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T       data;

    //只有两个参数的构造方法
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
