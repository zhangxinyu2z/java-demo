package com.z2xinyu.annotation.iocimpl.domain;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 19:57
 */
public class Student {
    private String number;
    private String name;
    private int age;
    private String sex;
    private Teacher teacher;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student(String number, String name, int age, String sex, Teacher teacher) {
        this.number = number;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.teacher = teacher;
    }
}
