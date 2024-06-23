package com.z2xinyu.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangxinyu
 * @date 2024/5/31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String identityId;

    private String name;
    private double score;

    private int age;

    private String city;

    private String hobbies;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, String hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public Student(String identityId, String name, String city) {
        this.identityId = identityId;
        this.name = name;
        this.city = city;
    }
}
