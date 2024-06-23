package com.z2xinyu.reflection.base;

import com.z2xinyu.reflection.po.Student;
import junit.framework.TestCase;

import java.lang.reflect.Field;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectFieldsTest extends TestCase {

    public void testGetAllFields() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.z2xinyu.reflection.po.Student");
        // 1 仅获取所有public字段对象
        // Field[] fields = c.getFields();
        // 2 获取所有的字段对象
        Field[] fields = c.getDeclaredFields();
        loopFieldInfo(fields);
    }

    public void testSetField()
        throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("com.z2xinyu.reflection.po.Student");
        Field nameField = c.getDeclaredField("name");
        // 1 Field的方法，设置字段的值
        Object stu = c.newInstance();
        // nameField.set(stu, 100); // IllegalArgumentException, name是String类型，不可设置Integer类型
        nameField.set(stu, "100");
        System.out.println(stu);

        // 2 是静态属性时，传入的引用可以为null
        Field colorField = c.getDeclaredField("high");
        colorField.set(null, 200);
        System.out.println("color=" + Student.high);
    }

    public void loopFieldInfo(Field[] fields) {
        for (Field field : fields) {
            // System.out.println(field.getName());
            // System.out.println(field.getType());
            // 访问修饰符+数据类型+全类名
            System.out.println(field.getName() + "\n\t" + field.toString());
        }
    }
}
