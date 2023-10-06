package com.z2xinyu.jdbc.utils.v2;

import org.junit.Test;

import java.util.ResourceBundle;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-18 18:21
 */
public class NwTest {
    @Test
    public void test() {
        ResourceBundle dbconfig = ResourceBundle.getBundle("dbconfig");
        System.out.println(dbconfig.getString("driverClassName"));
    }
}
