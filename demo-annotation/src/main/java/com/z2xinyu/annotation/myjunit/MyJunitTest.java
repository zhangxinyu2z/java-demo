package com.z2xinyu.annotation.myjunit;

/**
 * @author dhjy
 * @version v1.0
 * @date created in 2021-05-27 22:24
 */
public class MyJunitTest {
    @MyBefore
    public void init() {
        System.out.println("初始化");
    }

    @MyTest
    public void fun1(){
        System.out.println("运行中");
    }

    @MyAfter
    public void destory() {
        System.out.println("destory");
    }
}
