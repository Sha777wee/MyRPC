package com.shawee.impl;

import com.shawee.HelloService;

/**
 * @Author Shawee
 * @Date 2023/10/29
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello:" + name;
    }
}
