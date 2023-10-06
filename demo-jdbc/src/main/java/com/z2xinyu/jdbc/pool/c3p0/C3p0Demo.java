package com.z2xinyu.jdbc.pool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 11:32
 */
public class C3p0Demo {
    @Test
    public void hardCode() throws PropertyVetoException, SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/company");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("");
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        // 最大连接数
        comboPooledDataSource.setAcquireIncrement(5);
        // 初始连接数
        comboPooledDataSource.setInitialPoolSize(20);
        // 最小空闲连接
        comboPooledDataSource.setMinPoolSize(2);
        // 最大空闲连接
        comboPooledDataSource.setMaxPoolSize(50);

        Connection con = comboPooledDataSource.getConnection();
        // com.mchange.v2.c3p0.impl.NewProxyConnection@5ffead27 [wrapping: com.mysql.jdbc.JDBC4Connection@6356695f]
        System.out.println(con.getClass().getName());
        // 归还连接
        con.close();
    }

    /**
     * 使用配置文件
     * @throws SQLException
     */
    @Test
    public void softCode() throws SQLException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        Connection con = ds.getConnection();
        System.out.println(con);
        // 归还连接
        con.close();
    }
}
