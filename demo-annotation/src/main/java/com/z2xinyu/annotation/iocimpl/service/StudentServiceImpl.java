package com.z2xinyu.annotation.iocimpl.service;

import com.xinyu.annotation.iocimpl.dao.StudentDao;

/**
 * @author zhang xinyu
 * @version v1.0
 * @description:
 * @date Created in 2021-05-16 20:05
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = null;

    /**
     * 调用该方法的对象，需要提供studentDao
     * @param studentDao
     */
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void login() {
        studentDao.add(null);
        studentDao.update(null);
    }
}
