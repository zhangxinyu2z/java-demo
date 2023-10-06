package com.z2xinyu.annotation.iocimpl.dao;

import com.xinyu.annotation.iocimpl.domain.Student;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 20:02
 */
public class StudentDaoImpl1 implements StudentDao{
    @Override
    public void add(Student student) {
        System.out.println("studentDaoImpl1 add");
    }

    @Override
    public void update(Student student) {
        System.out.println("studentDaoImpl1 update");
    }
}
