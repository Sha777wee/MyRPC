package com.shawee.proxy;

import com.shawee.common.Invocation;
import com.shawee.common.URL;
import com.shawee.loadbalance.LoadBalance;
import com.shawee.protocol.HttpClient;
import com.shawee.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shawee
 * @Date 2023/10/30
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // mock测试用
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                // 服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());


                // 服务调用
                String result = null;
                int max = 3;
                List<URL> invokedUrls = new ArrayList<>();
                while (max > 0) {
                    // 负载均衡
                    urls.remove(invokedUrls);
                    URL url = LoadBalance.random(urls);
                    invokedUrls.add(url);
                    try {
                        HttpClient httpClient = new HttpClient();
                        result = httpClient.send(url.getHostName(), url.getPort(), invocation);
                    } catch (Exception e) {
                        if (max-- != 0) {
                            continue;
                        }
                        // error-callback
                        // 容错
                        return "报错了";
                    }
                }
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
