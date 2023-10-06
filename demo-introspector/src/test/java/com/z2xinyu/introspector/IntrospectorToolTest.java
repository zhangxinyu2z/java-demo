package com.z2xinyu.introspector;

import com.bean.domain.Student;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 16:26
 */
public class IntrospectorToolTest {

    @Test
    public void writePropertyValue() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        IntrospectorTool.writePropertyValue("username", new Student());
    }

    @Test
    public void test() {
        Object x= 12;
        System.out.println(x.getClass().getTypeName());
    }
}