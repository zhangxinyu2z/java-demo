package com.z2xinyu.annotation.iocimpl;

import cn.itcast.beanfactory.BeanFactory;
import com.xinyu.annotation.iocimpl.dao.StudentDaoImpl1;
import com.xinyu.annotation.iocimpl.domain.Student;
import com.xinyu.annotation.iocimpl.domain.Teacher;
import com.xinyu.annotation.iocimpl.service.StudentServiceImpl;

/**
 * 通过配置文件实现面相接口编程
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 21:40
 */
public class Test {
    @org.junit.Test
    public void fun1() {
        /*
         * 1. 创建Bean工厂，创建时需要给工厂指定配置文件
         * 2. 从工厂中获取bean对象
         *  BeanFactory生成的对象是单例的，设置bean属性scope="prototype" 表示多例
         */
        BeanFactory beanFactory = new BeanFactory("beans.xml");
        Student s1 = (Student) beanFactory.getBean("stu1");
        Student s2 = (Student) beanFactory.getBean("stu1");
        System.out.println(s1 == s2);
        System.out.println(s1);

    }

    @org.junit.Test
    public void fun2() {
        BeanFactory beanFactory = new BeanFactory("beans.xml");
        Teacher t1 = (Teacher) beanFactory.getBean("t1");
        System.out.println(t1);
    }

    @org.junit.Test
    public void fun3() {
        BeanFactory beanFactory = new BeanFactory("beans.xml");
        StudentDaoImpl1 studentDaoImpl1 = (StudentDaoImpl1) beanFactory.getBean("studentDaoImpl");
        studentDaoImpl1.add(null);
        studentDaoImpl1.update(null);
    }

    @org.junit.Test
    public void fun4() {
        BeanFactory beanFactory = new BeanFactory("beans.xml");
        StudentServiceImpl stuservice = (StudentServiceImpl) beanFactory.getBean("stuservice");
        stuservice.login();
    }

}
