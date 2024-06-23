package com.z2xinyu.reflection.util;

import java.lang.reflect.Field;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class ReflectUtils {

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
