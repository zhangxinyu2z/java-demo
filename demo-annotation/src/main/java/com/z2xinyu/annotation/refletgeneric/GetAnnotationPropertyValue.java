package com.z2xinyu.annotation.refletgeneric;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * 获取注解作用目标（类、方法等等）上的值
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 19:46
 */
public class GetAnnotationPropertyValue {
    @Test
    public void fun1() throws NoSuchMethodException {
        // 得到注解作用目标
        Class<Demo> demoClass = Demo.class;
        // 获取指定作用目标上的注解，注解可以有多个，所以用.class区分
        MyAnno1 myAnno1 = demoClass.getAnnotation(MyAnno1.class);
        System.out.println(myAnno1.name() + "," + myAnno1.age() + "," + myAnno1.sex());

        // 得到注解作用目标：方法
        Method fun1 = demoClass.getMethod("fun1");
        MyAnno1 myAnno2 = fun1.getAnnotation(MyAnno1.class);
        System.out.println(myAnno2.name() + "," + myAnno2.age() + "," + myAnno2.sex());

    }
}

@MyAnno1(name = "A类", age = 1, sex = "男")
class Demo {
    @MyAnno1(name = "fun1方法", age = 2, sex = "女")
    public void fun1() {

    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1 {
    String name();

    int age();

    String sex();
}
