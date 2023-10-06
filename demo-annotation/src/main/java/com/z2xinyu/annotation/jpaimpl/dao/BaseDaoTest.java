package com.z2xinyu.annotation.jpaimpl.dao;

import com.xinyu.annotation.jpaimpl.domain.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

/**
 * @author dhjy
 * @version v1.0
 * @date created in 2021-05-27 23:20
 */
public class BaseDaoTest {
    @Test
    public void fun1() throws SQLException {
        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.setUsername("aaaa");
        user.setPassword("dafasfsa");
        new BaseDaoImpl().add(user);
    }
}
