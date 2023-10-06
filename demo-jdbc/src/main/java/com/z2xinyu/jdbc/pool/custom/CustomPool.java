package com.z2xinyu.jdbc.pool.custom;

import com.xinyu.utils.v2.JdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 自定义连接池，继承自定义的DataSource
 *
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-04 22:55
 */
public class CustomPool extends CustomPoolDataSource {
    private List<Connection> connectionList = new ArrayList<Connection>();
    private static final String driverClassName;
    private static final String url;
    private static final String user;
    private static final String password;

    /**
     * 读取配置文件中的信息，加载驱动
     */
    static {
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driverClassName = properties.getProperty("driverClassName");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomPool() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        // 初始添加10个连接，和数据库的连接是同一个
        for (int i = 0; i < 10; i++) {
            CustomConnection customConnection = new CustomConnection(connection, this);
            connectionList.add(customConnection);
        }
    }

    public void add(Connection connection) {
        connectionList.add(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connectionList.size() > 0) {
            return connectionList.remove(0);
        }
        throw new SQLException("请先归还连接到池中");
    }
}
