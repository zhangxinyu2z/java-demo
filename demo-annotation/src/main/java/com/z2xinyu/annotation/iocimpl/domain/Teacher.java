package com.z2xinyu.annotation.iocimpl.domain;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 19:57
 */
public class Teacher {
    private String tid;
    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher(String tid, String name) {
        this.tid = tid;
        this.name = name;
    }

    public Teacher() {
    }
}
