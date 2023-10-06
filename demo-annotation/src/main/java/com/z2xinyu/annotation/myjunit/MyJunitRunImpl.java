package com.z2xinyu.annotation.myjunit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 自定义注解，模拟实现junit的运行测试
 *
 * @author dhjy
 * @version v1.0
 * @date created in 2021-05-27 22:26
 */
public class MyJunitRunImpl {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        /*
        1、获取测试类的字节码文件对象
        2、得到所有的public方法
        3、通过方法上的注解类型，对方法的功能分类
        4、迭代调用，invoke调用方法
         */
        //1
        Class<MyJunitTest> clazz = MyJunitTest.class;
        MyJunitTest myJunitTest = clazz.newInstance();
        // 2
        Method[] methods = clazz.getMethods();
        // 3
        ArrayList<Method> beforeMethod = new ArrayList<>();
        ArrayList<Method> testMethod = new ArrayList<>();
        ArrayList<Method> afterMethod = new ArrayList<>();
        // 如果这些方法上有这些注解，那么就调用执行
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeMethod.add(method);
            }
            if (method.isAnnotationPresent(MyTest.class)) {
                testMethod.add(method);
            }
            if (method.isAnnotationPresent(MyAfter.class)) {
                afterMethod.add(method);
            }
        }
        //4
        for (Method test : testMethod) {
            for (Method before : beforeMethod) {
                before.invoke(myJunitTest);
            }
            test.invoke(myJunitTest);
            for (Method after : afterMethod) {
                after.invoke(myJunitTest);
            }
        }
    }
}
