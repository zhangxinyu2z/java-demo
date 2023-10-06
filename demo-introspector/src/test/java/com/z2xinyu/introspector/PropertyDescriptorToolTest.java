package com.z2xinyu.introspector;

import com.bean.domain.Student;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author James
 * @version v1.0
 * @date created in 2021-10-16 15:40
 */
public class PropertyDescriptorToolTest {

    @Test
    public void setProperty() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptorTool.setProperty("username", new Student());
    }
}