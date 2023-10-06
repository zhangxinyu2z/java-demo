package com.z2xinyu.reflection.ReflectionAccessField;

import java.lang.reflect.Field;

/**
 * 通过反射获取字段 <br/>
 * public void set(Object obj, Object value)
 * 
 * @author zhang xinyu
 * @date 2021-04-16 21:02:43
 * @version v1.0
 */
public class Demo01GetFields {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException,
        InstantiationException, IllegalAccessException {
        // 获取Class对象
        Class<?> c = Class.forName("com.dhjy.demo03.ReflectionAccessField.Student");
        // 1.1 仅获取所有public字段对象
        // Field[] fields = c.getFields();
        // 1.2 获取所有的字段对象
        Field[] fields = c.getDeclaredFields();
        testFields(fields);
        System.out.println("------------------------");

        // 2.1 获取指定的字段对象
        Field salaryField = c.getDeclaredField("salary");
        // 3.1 Field的方法，设置字段的值
        // 创建一个该类的对象
        Object stu = c.newInstance();
        // salaryField.set(stu, 100); // IllegalArgumentException,Salary是String类型，不可设置Integer类型
        salaryField.set(stu, "100");
        /** set方法传入的对象似乎不必一定要new一个有参的对象 */
        System.out.println(stu);

        // 是静态属性时，传入的引用可以为null
        Field colorField = c.getDeclaredField("color");
        colorField.set(null, 200);
        System.out.println("color=" + Student.color);

    }

    /** 所有的字段信息 */
    private static void testFields(Field[] fields) {
        int count = 0;
        System.out.println("字段的所有信息：");
        for (Field field : fields) {
            // System.out.println(field.getName()); // money
            // System.out.println(field.getType()); // double
            // 访问修饰符+数据类型+全类名
            System.out.println(field.getName() + "\n\t" + field.toString()); // public double
                                                                             // com.dhjy.demo03.ClassObjectField.Student.money
        }
    }

}
