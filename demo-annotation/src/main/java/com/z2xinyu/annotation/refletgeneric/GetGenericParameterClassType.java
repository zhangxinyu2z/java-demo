package com.z2xinyu.annotation.refletgeneric;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 如何获取子类明确的类型参数变量的Class类型： B extends A<T>:参数T的实际类型
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 19:14
 */
public class GetGenericParameterClassType {
    @Test
    public void fun1() {
        new B();
    }
}


abstract class A<T> {
    public A() {
        /*
         * 在这里获取子类传递的泛型信息，要得到一个Class！
         * new B(),初始化的只有一个B对象，this指向的是B对象
         */
        // 得到子类的Class类型
        Class clazz = this.getClass();
        // 返回带有泛型参数信息的父类的Class对象   com.dhjy.annotation.refletgeneric.A<java.lang.String>
        Type type = clazz.getGenericSuperclass();
        // 强转成Type的子类接口ParameterizedType     com.dhjy.annotation.refletgeneric.A<java.lang.String>
        ParameterizedType pType = (ParameterizedType) type;
        // 返回表示此类型的实际类型参数的Type对象数组，泛型可以有有多个类型参数
        Type[] types = pType.getActualTypeArguments();
        // 得到实际类型参数的Class对象          java.lang.String
        Class c = (Class) types[0];

        /*
        Class c = (Class)((ParameterizedType)this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        */
        System.out.println(c.getName());
    }
}

class B extends A<String> {

}

class C extends A<Integer> {

}
