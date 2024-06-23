package com.z2xinyu.reflection.base;

import com.z2xinyu.reflection.po.Student;
import junit.framework.TestCase;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectClassTest extends TestCase {

    public void testGetClassObject() throws ClassNotFoundException {
        // 1 常用于方法中指定参数类型
        Class<Student> c = Student.class;
        System.out.println(c.getName());

        // 2 获取对象真正的类型(想想多态多中运行中实际的对象类型)
        Student stu1 = new Student();
        Student stu2 = new Student("刘亦菲", 24);
        Class<? extends Student> c1 = stu1.getClass();
        Class<? extends Student> c2 = stu2.getClass();
        assert c1 == c2;

        // 3 常用于读取配置文件
        Class<?> c3 = Class.forName("com.z2xinyu.reflection.po.Student");
        System.out.println(c3.getName());

        // 4 类加载器的方式
        ClassLoader classLoader = c.getClassLoader();
        Class<?> loadClass = classLoader.loadClass("com.z2xinyu.reflection.po.Student");
        System.out.println(loadClass.getName());
    }
}
