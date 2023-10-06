package com.z2xinyu.introspector.api;

import com.z2xinyu.introspector.po.Student;
import com.z2xinyu.introspector.po.Teacher;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static org.apache.commons.beanutils.BeanUtils.getProperty;

/**
 * BeanUtils对内省API的封装调用
 * <p>
 * PropertyUtils：对基本数据类型不会进行类型转换，使用原始的类型（包装类型）
 * BeanUtils:对传递的基本数据类型会进行类型转换，比如传递String类型给int，会自动转换，获取的也是String类型
 * <p>
 * 对于引用数据类型：
 * 1、String类型自动转化仅限于8种基本类型，该API无法将String转换为Date
 * 2、成员属性的值不能为Null：操作的是对象的属性，而不是该对象
 *
 * 基于此，一种是注册自己定义的数据类型转换器，另一种使用BeanUtils提供的DateLocaleConverter
 *
 * BeanUtils的api：
 * populate(bean, Map)：将Map集合中的值填充到Bean中
 * setProerty(bean, propertyName,
 *
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 16:48
 */
public class BeanUtilsApply {

    /**
     * BeanUtils对引用数据类型的转换
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void setReferenceProperty() throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        BeanUtils.setProperty(student, "date.time", "2000000000");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(student.getDate());
        System.out.println(date);
    }

    /**
     * 由于只能将基本数据类型进行类型转换成String，自定义转化器将传入的String转换成Date
     * 自定义类型转换
     */
    @Test
    public void setProperty() throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();

        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                if(null == value) {
                    return null;
                }
                // 只对String类型的日期进行转换
                if(!(value instanceof  String)) {
                    throw new ConversionException("仅支持String类型的日期转换");
                }
                String str = (String) value;
                if (str.trim().equals("")) {
                    return null;
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    return df.parse(str);
                } catch (ParseException e) {
                    throw new RuntimeException(e); // 异常链不能断
                }
            }
        }, Date.class);
        // 这个是api的日期类型转换器
        //ConvertUtils.register(new DateLocaleConverter(Locale.CHINESE,"yyyy-MM-dd HH:mm:ss"),Date.class);
        BeanUtils.setProperty(student,"date", "1996-7-12 12:23:23");
        System.out.println(student.getDate());

    }

    @Test
    public void testPopulate() throws InvocationTargetException, IllegalAccessException {
        HashMap<Object, Object> hm = new HashMap<>();
        hm.put("username", "张三");
        hm.put("age", 12);
        hm.put("date", "1949-04-12 12:23:23");
        ConvertUtils.register(new DateLocaleConverter(Locale.CHINESE,"yyyy-MM-dd HH:mm:ss"),Date.class);
        Student student = new Student();
        BeanUtils.populate(student, hm);
        System.out.println(student);
    }

    @Test
    public void testCopy() throws InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        student.setUsername("zhangsan");
        student.setAge(12);
        Teacher teacher = new Teacher();
        teacher.setUsername("lisi");
        BeanUtils.copyProperties(student, teacher);
        System.out.println(student);
        System.out.println(teacher);

    }


    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student student = new Student();
        // 设置Bean属性的值
        BeanUtils.setProperty(student, "username", "天帝");
        // 得到属性对应的值
        String username = getProperty(student, "username");
        // Test
        BeanUtils.setProperty(student, "age", "12");
        System.out.println(getProperty(student, "age").getClass().getName());

        PropertyUtils.setProperty(student, "age", new Integer(10));
        System.out.println(student);
    }
}
