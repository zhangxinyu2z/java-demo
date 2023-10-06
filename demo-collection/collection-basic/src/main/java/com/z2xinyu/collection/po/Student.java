package com.z2xinyu.collection.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dell
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Comparable<Student> {
    private String name;
    private int age;

    @Override
    public int compareTo(Student o) { // 排序规则
        // 名字长短
        int num = this.name.length() - o.getName().length();
        // 名字的自然顺序
        num = num == 0 ? this.name.compareTo(o.name) : num;
        // 年龄的大小
        num = num == 0 ? this.age - o.age : num;
        return num;
    }
}