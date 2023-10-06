package com.z2xinyu.keyword._final;

/**
 * @author zxy
 * @version v1.0
 * @date created in 2021-12-07 16:24
 */
public final class People {
    private String name;
    private int age;

    public People() {

    }

    People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public final void show() {
        System.out.println("hello world");
    }

}
