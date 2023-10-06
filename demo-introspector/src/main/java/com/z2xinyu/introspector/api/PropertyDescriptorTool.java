package com.z2xinyu.introspector.api;

import com.bean.domain.Student;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * PropertyDescriptor：描述通过一对访问器方法导出的一个javabean属性
 *
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 15:23
 */
public class PropertyDescriptorTool {
    public static void setProperty(String propertyName, Student student) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 已经确定了属性的名字和所属的类
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, Student.class);
        // class java.lang.String
        Class<?> propertyType = propertyDescriptor.getPropertyType();
        //        获取写的方法
        Method writeMethod = propertyDescriptor.getWriteMethod();
        //        设置入参，调用方法
        writeMethod.invoke(student, "张三");
        // setRead/WriteMethod() : 设置读写属性的方法
        System.out.println(student.getUsername());
    }

    /**
     * 反射是如何调用方法的
     */
    @Test
    public void demoHowoToInvoke() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, NoSuchFieldException {
        Class aClass = Class.forName("com.bean.domain.Teacher");
        Object o = aClass.newInstance();
        // 获取指定的字段
        Field field = aClass.getDeclaredField("username");
        // 获取属性的描述器
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), aClass);
        // 获取读写的方法
        Method writeMethod = propertyDescriptor.getWriteMethod();
        // 对传入的参数类型需要判断 instance of ,类型要匹配
        // Class<?>[] parameterTypes = writeMethod.getParameterTypes();
        writeMethod.invoke(o, "12");
        // read方法同理
    }

}
