package com.z2xinyu.reflection.ReflectAccessConstructor;

import java.lang.reflect.*;

/**
 * 通过反射获取构造方法并创建对象 <br/>
 * public T newInstance(Object... initargs) <br/>
 * public void setAccessible(boolean flag) 取消访问检查
 * 
 * @author zhang xinyu
 * @date 2021-04-16 20:42:02
 * @version v1.0
 */
public class Demo01GetConstructors {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
        IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 1 获得Class对象
        Class<Student> c = Student.class;
        // 2.1 获取所有的public构造函数,不包括父类的
        // Constructor<?>[] cons = c.getConstructors();
        // 2.2 获取所有的构造方法对象(所有权限)
        Constructor<?>[] cons = c.getDeclaredConstructors();
        // 测试2.1 And 2.2
        testConstructors(cons);

        System.out.println("下一个测试------------------------");

        // 3.1 获取指定的构造方法
        // Constructor<Student> constructor = c.getConstructor(); // NoSuchMethodException,只能访问public构造方法
        Constructor<Student> constructor = c.getDeclaredConstructor(); // 可以访问非public的构造方法
        /** 通过构造方法对象，创建一个该类的对象 */
        Student stu = constructor.newInstance();
        System.out.println("真正参与运行的对象类型：" + stu.getClass());
        System.out.println(stu);

        System.out.println("下一个测试------------------------");

        // 3.2 获取带参的public构造方法
        Constructor<Student> constructor2 = c.getDeclaredConstructor(String.class, int.class);
        // Student stu2 = constructor2.newInstance(); // wrong
        Student stu2 = constructor2.newInstance("刘亦菲", 18); // IllegalArgumentException,使用带参的构造方法创建对象，就必须要传入参数
        System.out.println(stu2);

        System.out.println("下一个测试------------------------");

        // 3.3 获取带参的private构造方法
        Constructor<Student> constructor3 = c.getDeclaredConstructor(String.class);
        /** 让private构造方法可以访问 */
        constructor3.setAccessible(true);
        Student stu3 = constructor3.newInstance("王祖贤"); // IllegalAccessException,私有的构造方法，需要先放开权限
        System.out.println(stu3);
    }

    /** 获取构造方法对象 */
    private static void testConstructors(Constructor<?>[] cons) {
        int count = 0;
        for (Constructor<?> con : cons) {
            // 获取构造函数的名字
            // System.out.println(con.getName());
            int modifiers = con.getModifiers();
            System.out.println("构造方法的访问修饰符：" + modifiers);
            
            
            // 获取构造函数的修饰符，参数类型
            System.out.println("第" + (++count) + "个构造方法的信息：\n\t" + con.toString());
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
