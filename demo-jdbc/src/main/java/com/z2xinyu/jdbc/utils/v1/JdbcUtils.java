package com.z2xinyu.jdbc.utils.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhangxinyu
 * @version 1.0
 * @create 2021-05-10 10:24
 */
public class JdbcUtils {
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/company";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // register driver
        Class.forName(driverClassName);
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
