package com.z2xinyu.introspector.po;

/**
 * @author James
 * @version v1.0
 * @date created in 2021-11-04 22:39
 */
public class Teacher {
    private String username;
    private int age;
    private long height;

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

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
