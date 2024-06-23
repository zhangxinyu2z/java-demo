package com.z2xinyu.reflection.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {
    private String name;
    private int age;

    public static int high;

    protected String salary;
    public double money;

    public Student() {
        super();
    }

    @SuppressWarnings("unused")
    private Student(String name) {
        this.name = name;
    }

    protected Student(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public void getInfo(String name, int age) {
        System.out.println("name=" + name + ",age=" + age);
    }

    protected void getSalary() {
        System.out.println("salary=" + salary);
    }

    void getMoney() {
        System.out.println("money=" + money);
    }

    private String show(String something) {
        System.out.println("输出" + something);
        return something + name;
    }

    public void show2(String s,int x) {
        System.out.println();
    }
}
