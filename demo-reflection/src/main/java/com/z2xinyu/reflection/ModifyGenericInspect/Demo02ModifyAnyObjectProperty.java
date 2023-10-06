package com.z2xinyu.reflection.ModifyGenericInspect;

import java.lang.reflect.Field;

/**
 * 修改对象成员属性的值
 * 
 * @author zhang xinyu
 * @date 2021-04-23 16:55:12
 * @version v1.0
 */
public class Demo02ModifyAnyObjectProperty {

    public static void main(String[] args) throws Exception {
        Student s = new Student();
        Tools.setPropertyName(s, "name", "刘亦菲");
        Tools.setPropertyName(s, "age", 19);
        System.out.println(s);
    }
}

class Student {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

}

class Tools {
    /**
     * 为对象的字段赋值
     */
    public static void setPropertyName(Object obj, String propertyName, Object value)
        throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // 1 获取obj的Class对象
        Class<? extends Object> c = obj.getClass();
        // 2 获取Obj的指定的Field对象
        Field f = c.getDeclaredField(propertyName);
        // 3 取消访问检查
        f.setAccessible(true);
        // 4 设置指定对象的属性的值
        f.set(obj, value);
    }
}
