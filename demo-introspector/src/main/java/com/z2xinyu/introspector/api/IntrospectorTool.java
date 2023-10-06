package com.z2xinyu.introspector.api;

import com.bean.domain.Student;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Introspector：内省是针对Javabean的一种缺省处理方法，提供了一套API用来访问某个属性的getter/setter方法,
 * 再通过反射机制来调用这些方法
 * </p>
 * Instropector -> BeanInfo -> PropertyDescriptor -> getter/setter -> 反射调用
 * 1、Introspector：是一个工具类，提供了一系列取得BeanInfo的方法；
 * 2、BeanInfo：对一个JavaBean的描述，可以通过它取得Bean内部的信息；
 * 3、PropertyDescriptor：对一个Bean属性的描述，它提供了一系列对Bean属性进行操作的方法
 * <p>
 * <p>
 * getBeanInfo(bean.class, stop.class) : stop.class是bean的基类，表示不内省基类的属性
 *
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 16:15
 */
public class IntrospectorTool {
    public static void writePropertyValue(String propertyName, Student student) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
//        BeanInfo : 一个Bean的所有【属性、方法、事件】信息
        // Object.class 表示不自省super class的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class);
        // 返回一个Bean的所有属性描述
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (null != propertyDescriptors && propertyDescriptors.length > 0) {
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                System.out.println(propertyDescriptor.getName());
                /*if(propertyDescriptor.getName().equals(propertyName)) {
                    propertyDescriptor.getWriteMethod().invoke(student, "共工");
                    System.out.println(student.getUsername());
                    break;
                }*/
            }
        }
    }

    public void testPropertyDescriptor() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("username", Student.class);
        Method readMethod = propertyDescriptor.getReadMethod();
        readMethod.invoke(student, "wangwu");
        System.out.println(student);
    }
}
