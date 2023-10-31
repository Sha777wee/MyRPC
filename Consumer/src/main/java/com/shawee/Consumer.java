package com.shawee;

import com.shawee.proxy.ProxyFactory;

/**
 * @Author Shawee
 * @Date 2023/10/29
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("Shawee");
        System.out.println(result);
    }
}
