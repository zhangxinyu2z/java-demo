package com.z2xinyu.reflection.ReflectionAccessField;

/**
 * @author zhang xinyu
 * @date 2021-04-16 20:57:10
 * @version v1.0
 */
public class Student {
    public double money;
    int high;
    protected String salary;
    static int color;

    private String name;
    private int age;

    Student() {
        super();
    }

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
        return "Student [money=" + money + ", high=" + high + ", salary=" + salary + ", name=" + name + ", age=" + age
            + "]";
    }
}
