package com.z2xinyu.io.tobe.demo04.Properties;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class Demo01Properties {
    public static void main(String[] args) throws IOException {
        // test01Properties();
        // test02Properties();
        test03Properties();
    }

    private static void test03Properties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("properties.txt"));
        // properties.load(new FileInputStream("properties.txt")); // 乱码
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String s : propertyNames) {
            System.out.println(s + "=" + properties.getProperty(s));
        }
    }

    /** 测试store方法 */
    public static void test02Properties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("刘亦菲", "花木兰");
        properties.setProperty("王祖贤", "聂小倩");
        properties.setProperty("李若彤", "小龙女");
        properties.store(new FileWriter("properties.txt"), "save data");
        // properties.store(new FileOutputStream("properties.txt"), ""); // 字节流写中文会乱码Unicode
    }

    /*
     * setProperty
     * getProperty
     * stringPropertyNames:相当于keySet方法
     */
    public static void test01Properties() {
        Properties properties = new Properties();
        properties.setProperty("刘亦菲", "花木兰");
        properties.setProperty("王祖贤", "聂小倩");
        properties.setProperty("李若彤", "小龙女");

        Set<String> propertyNames = properties.stringPropertyNames();
        for (String key : propertyNames) {
            System.out.println(key + "=" + properties.getProperty(key));
        }
    }
}
