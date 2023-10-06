package com.z2xinyu.jdbc.pool.custom;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试自定义连接池
 *
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 0:42
 */
public class CustomPoolTest {
    @Test
    public void test() throws SQLException {
        CustomPool customPool = new CustomPool();
        Connection con1 = customPool.getConnection();
        Connection con2 = customPool.getConnection();
        // 底层同一个Connection
        con1.close();
        con2.close();
    }
}