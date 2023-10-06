package com.z2xinyu.keyword._static;

/**
 * 同名变量：就近原则
 *
 * @author zxy
 */
public class StaticCase1 {
    static int a = 20;

    public static void show(int a) {
        // 50
        System.out.println(a);
        //就近原则
        a = 30;
        // 30
        System.out.println(a);
    }

    public static void main(String[] args) {
        show(50);
        // 20
        System.out.println(a);
    }
}
