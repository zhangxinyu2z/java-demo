package com.z2xinyu.reflection.IntroduceReflectionOfConfigProperties;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 如果提供一个这样的配置文件，要求创建一个对象，该怎么实现？ 由此引入反射
 * 
 * @author zhang xinyu
 * @date 2021-04-16 21:42:03
 * @version v1.0
 */
public class ReadConfigFile {
    public static void main(String[] args)
        throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
        NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        // 1 使用io流把classinfo信息读取到Properties中
        Properties p = new Properties();
        p.load(new FileReader("classinfo.txt"));

        // 2 获取类名和方法名
        String className = p.getProperty("ClassName");
        String methodName = p.getProperty("MethodName");

        // 3 通过反射创建该类对象，并调用方法
        Class<?> c = Class.forName(className);

        // 4 创建该类对象
        Object obj = c.newInstance();

        // 5 获取方法对象
        Method method = c.getMethod(methodName);

        // 6 调用方法
        method.invoke(obj);
    }
}
