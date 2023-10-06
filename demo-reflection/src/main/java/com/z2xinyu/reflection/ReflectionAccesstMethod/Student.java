package com.z2xinyu.reflection.ReflectionAccesstMethod;

/**
 * @author zhang xinyu
 * @date 2021-04-16 20:57:10
 * @version v1.0
 */
public class Student {
    public double money;
    int high;
    protected String salary;

    private String name;
    private int age;

    Student() {
        super();
    }

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public void getInfo(String name, int age) {
        System.out.println("name=" + name + ",age=" + age);
    }

    protected void getSalay() {
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

    @Override
    public String toString() {
        return "Student [money=" + money + ", high=" + high + ", salary=" + salary + ", name=" + name + ", age=" + age
            + "]";
    }
}
