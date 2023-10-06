package com.z2xinyu.reflection.complie_loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyFileClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
        IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        MyFileClassLoader fileClsLoader = new MyFileClassLoader();
        // fileClsLoader.setClassPath("E:\\j2ee_proj\\skythink\\WebContent\\WEB-INF\\classes\\");
        fileClsLoader.setClassPath("D:\\develop\\projects\\eclipse-workspace\\day27_Reflection\\src\\");
        Class<?> cls = fileClsLoader.loadClass("com.dhjy.Test");
        Object obj = cls.newInstance();

        Method[] mthds = cls.getMethods();
        for (Method mthd : mthds) {
            String methodName = mthd.getName();
            System.out.println("mthd.name=" + methodName);
        }
        System.out.println("obj.class=" + obj.getClass().getName());
        System.out.println("obj.class=" + cls.getClassLoader().toString());
        System.out.println("obj.class=" + cls.getClassLoader().getParent().toString());
    }

}
