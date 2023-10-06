package com.z2xinyu.introspector.po;

import java.util.Date;

/**
 * JavaBean是用于传递数据的一种规范，使用setter来封装数据
 *
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 15:13
 */
public class Student {
    private String username;
    private int age;
    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
