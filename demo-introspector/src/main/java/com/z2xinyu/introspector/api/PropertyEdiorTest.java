package com.z2xinyu.introspector.api;

import java.beans.PropertyEditorSupport;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-10 9:40
 */
public class PropertyEdiorTest extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(100);
    }

    public static void main(String[] args) {

        PropertyEdiorTest propertyEdiorTest = new PropertyEdiorTest();
        propertyEdiorTest.setAsText("142421");
        Object value = propertyEdiorTest.getValue();
        System.out.println(value);
    }
}
