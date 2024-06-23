package com.z2xinyu.reflection.cases;

import com.z2xinyu.reflection.po.Student;
import com.z2xinyu.reflection.util.ReflectUtils;
import junit.framework.TestCase;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectCasesTest extends TestCase {

    public void testReadProperties()
        throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
        NoSuchMethodException, InvocationTargetException {
        // 1 使用io流把classinfo信息读取到Properties中
        Properties p = new Properties();
        p.load(new FileReader("classinfo.txt"));

        // 2 获取类名和方法名
        String className = p.getProperty("ClassName");
        String methodName = p.getProperty("MethodName");

        // 3 通过反射创建该类对象，并调用方法
        Class<?> c = Class.forName(className);

        // 4 创建该类对象
        Object obj = c.newInstance();

        // 5 获取方法对象
        Method method = c.getMethod(methodName);

        // 6 调用方法
        method.invoke(obj);
    }

    /**
     * 绕过泛型对指定类型的List集合添加不同类型的元素<br/>
     * 泛型只是给编译期看的，可以用反射修改字节码对象
     */
    public void testSetGenericElement() throws NoSuchMethodException, InvocationTargetException,
        IllegalAccessException {
        List<String> al = new ArrayList<String>();
        // al.add(12); // wrong
        Class<? extends List> alClass = al.getClass();
        Method method = alClass.getMethod("add", Object.class);
        Object obj = method.invoke(al, 25);
        System.out.println(obj);
        System.out.println(al);
    }

    public void testSetPropertyValue() throws NoSuchFieldException, IllegalAccessException {
        Student s = new Student();
        ReflectUtils.setPropertyName(s, "name", "刘亦菲");
        ReflectUtils.setPropertyName(s, "age", 19);
        System.out.println(s);
    }
}
