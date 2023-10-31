package com.shawee.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Shawee
 * @Date 2023/10/30
 */
public class LocalRegister {

    private static final Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, String version, Class implClass) {
        map.put(interfaceName + version, implClass);
    }

    public static Class get(String interfaceName, String version) {
        return map.get(interfaceName + version);
    }
}
