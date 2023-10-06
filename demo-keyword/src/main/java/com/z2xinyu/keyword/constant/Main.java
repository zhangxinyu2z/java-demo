package com.z2xinyu.keyword.constant;

/**
 * 常量池(constant pool)指的是在编译期被确定，并被保存在已编译的.class文件中的一些数据。
 * 它包括了关于类、方法、接口等中的常量，也包括字符串常量。
 *
 * 发现了一个现象：访问一个类的静态常量，静态代码块并没有执行。
 *
 * @author zxy
 */
public class Main {
    public static void main(String[] args) {
        /**output
         * Test static block
         * 23
         */
//        System.out.println(Data.age);
        /**output
         * shanxi
         */
//        System.out.println(Data.name);
        /*output
         * Test static block
         * 85
         */
        System.out.println(Data.score);
    }
}

class Data {
    public static int age = 23;
    public static final String name = "shanxi";
    public static final Integer score = 85;

    static {
        System.out.println("Test static block");
    }
}

