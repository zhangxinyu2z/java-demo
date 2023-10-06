package com.z2xinyu.annotation.iocimpl.dao;

import com.xinyu.annotation.iocimpl.domain.Student;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 20:00
 */
public interface StudentDao {
    public abstract void add(Student student);

    public abstract void update(Student student);
}
