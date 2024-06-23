package com.z2xinyu.reflection.base;

import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectMethodsTest extends TestCase {

    public void testGetAllMethods()
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException,
        InvocationTargetException {
        // 获取Class对象
        Class<?> c = Class.forName("com.z2xinyu.reflection.po.Student");

        // 获取所有的方法
        Method[] ms = c.getDeclaredMethods();
        loopMethodInfo(ms);
    }

    public void testGetMethod()
        throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException,
        InvocationTargetException {
        Class<?> c = Class.forName("com.z2xinyu.reflection.po.Student");

        // 获取指定参数类型的方法
        Method m = c.getDeclaredMethod("show", String.class);
        // 构建一个实例对象
        Object o = c.newInstance();
        // 取消访问检查
        m.setAccessible(true);
        // 调用方法
        Object invokeReturn = m.invoke(o, "100"); // IllegalAccessException: The show method is private
        System.out.println(invokeReturn);


        Method m2 = c.getDeclaredMethod("show2", String.class, int.class);
        Object obj = m2.invoke(o, "hello", 2);
        System.out.println("return obj=" + obj);

        // System.out.println(int.class.getclass == java.lang.Integer.class); // false

        System.out.println(int.class.getName()); // int
        System.out.println(Integer.class.getName()); // java.lang.Integer
        System.out.println(int.class == Integer.TYPE);
    }

    /**
     * 方法的信息：访问权限、返回类型、名字、参数类型、参数 <br/>
     * 默认 0 public 1 private 2 protected 4 static 8 final 16
     */
    private void loopMethodInfo(Method[] ms) {
        for (Method m : ms) {
            Class<?> type = m.getReturnType();
            System.out.println("方法的Class形式返回类型\r\t" + type);
            int modifiers = m.getModifiers();
            System.out.println("方法的访问修饰符\r\t" + modifiers);
            System.out.println("详细信息\r\t" + m.toString()); // private java.lang.String + ""
            // + com.dhjy.demo04.ClassObjectMethods.Student.show(java.lang.String)
            System.out.println("----------");
        }
    }
}
