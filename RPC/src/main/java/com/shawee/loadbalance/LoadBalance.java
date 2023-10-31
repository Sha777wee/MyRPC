package com.shawee.loadbalance;

import com.shawee.common.URL;

import java.util.List;
import java.util.Random;

/**
 * @Author Shawee
 * @Date 2023/10/30
 */
public class LoadBalance {

    public static URL random(List<URL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
