package com.shawee;

import com.shawee.common.URL;
import com.shawee.impl.HelloServiceImpl;
import com.shawee.protocol.HttpServer;
import com.shawee.register.LocalRegister;
import com.shawee.register.MapRemoteRegister;

/**
 * @Author Shawee
 * @Date 2023/10/29
 */
public class Provider {
    public static void main(String[] args) {

        // 注册接口名和实现类
        LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImpl.class);

        // 注册中心注册（服务注册）
        URL url = new URL("localhost", 8080);
        MapRemoteRegister.regist(HelloService.class.getName(), url);

        // Netty、Tomcat、Socket、Jetty...
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
