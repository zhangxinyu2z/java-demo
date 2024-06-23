package com.z2xinyu.reflection.base;

import com.z2xinyu.reflection.po.Student;
import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectConstructorsTest extends TestCase {

    public void testGetAllConstructors() {
        // 获得Class对象
        Class<Student> c = Student.class;
        // 获得所有的public构造函数,不包括父类的
        // Constructor<?>[] cons = c.getConstructors();
        // 获得所有的构造方法对象(所有权限)
        Constructor<?>[] cons = c.getDeclaredConstructors();
        loopConstructorInfo(cons);
    }

    public void testNewInstanceByConstructor()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Student> c = Student.class;
        // 1
        // Constructor<Student> constructor = c.getConstructor();// NoSuchMethodException,只能访问public构造方法
        // 可以访问非public的构造方法
        Constructor<Student> constructor = c.getDeclaredConstructor();
        // 通过构造方法对象，创建一个该类的对象
        Student stu = constructor.newInstance();
        System.out.println("真正参与运行的对象类型：" + stu.getClass());
        System.out.println(stu);

        // 2 获取带参的public构造方法
        Constructor<Student> constructor2 = c.getDeclaredConstructor(String.class, int.class);
        // Student stu2 = constructor2.newInstance(); // wrong 使用带参的构造方法创建对象，就必须要传入参数，否则：IllegalArgumentException
        Student stu2 = constructor2.newInstance("刘亦菲", 18);
        System.out.println(stu2);

        // 3 获取带参的private构造方法
        Constructor<Student> constructor3 = c.getDeclaredConstructor(String.class);
        // 让private构造方法可以访问，否则：IllegalAccessException
        constructor3.setAccessible(true);
        Student stu3 = constructor3.newInstance("王祖贤");
        System.out.println(stu3);
    }

    private void loopConstructorInfo(Constructor<?>[] cons) {
        int count = 0;
        for (Constructor<?> con : cons) {
            // 获取构造函数的名字
            // System.out.println(con.getName());
            // 获取构造函数的修饰符，参数类型
            System.out.println("第" + (++count) + "个构造方法的信息：\n" + con);
            Parameter[] parameters = con.getParameters();
            System.out.println("方法的参数信息：");
            for (Parameter p : parameters) {
                // System.out.println("\t" + p.getName()); // arg0
                // System.out.println("\t" + p.getType()); // class java.lang.String
                System.out.println("\t" + p.toString()); // java.lang.String arg0
            }
        }
    }
}
