package com.z2xinyu.io.tobe.demo05.ObjectStream;

import java.io.Serializable;

/**
 * 序列化的对象要实现Serialiable标记接口
 * 
 * @author zhang xinyu
 * @date 2021-04-14_22:18:33
 * @version v1.0
 */
public class Student implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    public transient int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Student() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}
