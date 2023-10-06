package com.z2xinyu.reflection.ReflectionAccesstMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射获取方法对象和如何调用 <br/>
 * public Object invoke(Object obj, Object... args):调用方法，并获取方法的返回值 <br/>
 * <br/>
 * public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
 * 
 * @author zhang xinyu
 * @date 2021-04-16 21:21:52
 * @version v1.0
 */
public class Demo01Methods {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
        IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        // 获取Class对象
        Class<?> c = Class.forName("com.dhjy.demo04.ReflectionAccesstMethod.Student");

        // 获取所有的方法
        Method[] ms = c.getDeclaredMethods();
        getMethodsInfo(ms);

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
    private static void getMethodsInfo(Method[] ms) {
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
