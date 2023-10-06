package com.z2xinyu.reflection.ModifyGenericInspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 绕过泛型对指定类型的List集合添加不同类型的元素<br/>
 * 泛型只是给编译期看的，可以用反射修改字节码对象
 * 
 * @author zhang xinyu
 * @date 2021-04-16 21:49:49
 * @version v1.0
 */
public class Demo01ModifyGenericInspect {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException {
        ArrayList<String> al = new ArrayList<String>();
        // al.add(12); // wrong
        Class alClass = al.getClass();
        Method method = alClass.getMethod("add", Object.class);
        Object obj = method.invoke(al, 25);
        System.out.println(obj);
        System.out.println(al);
    }
}
