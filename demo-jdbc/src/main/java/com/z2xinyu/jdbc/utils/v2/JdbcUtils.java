package com.z2xinyu.jdbc.utils.v2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 读取配置文件参数，解耦
 *
 * @author zhangxinyu
 * @version 1.1
 * @create 2021-10-03
 */
public class JdbcUtils {
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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
