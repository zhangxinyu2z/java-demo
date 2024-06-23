package com.z2xinyu.reflection.util;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public class ProxyClassByteCodeGenerator {

    /**
     * 将代理类的字节码文件保存到磁盘上
     *
     * @param path
     * @param interfaces 委托类（被代理类）的接口Class[]
     * @return
     */
    public static byte[] saveGenerateProxyClass(String path, Class<?>[] interfaces) {
        // 用来获取代理类的字节码
        String DEFAULT_CLASS_NAME = "$Proxy";
        byte[] classFile = ProxyGenerator.generateProxyClass(DEFAULT_CLASS_NAME, interfaces);

        String filePath = path + "/" + DEFAULT_CLASS_NAME + ".class";
        try (FileOutputStream out = new FileOutputStream(filePath);) {
            out.write(classFile);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return classFile;
    }

    public static void main(String[] args) {
        // Class<?> interfaces[] = {UserDao.class};
        // saveGenerateProxyClass("D:/tmp", interfaces);
    }

}