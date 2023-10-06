package com.z2xinyu.jdbc.utils.v6;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC工具类（去除详细注释）
 *
 * @author zhangxinyu
 * @create 2021-05-11 20:08
 * version 1.6
 */
public class JdbcUtils {
    /**
     * 获取数据库连接池，需配置文件c3p0-config.xml
     */
    private static final DataSource dataSource = new ComboPooledDataSource();

    /**
     * 使用指定线程存储事务connection，避免并发异常
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 获取DataSource
     *
     * @return
     */
    private static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取线程池connection
     * threadlocal中有事务connection，返回该connection，否则从连接池中获取一个connection返回
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            return connection;
        }
        return dataSource.getConnection();
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    public static void beginTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            throw new SQLException("已经开启了事务，请不要重复开启");
        }
        // 从连接池中获取connection
        connection = dataSource.getConnection();
        // 设置手动提交，开启事务
        connection.setAutoCommit(false);
        // 把事务连接存储到threadLocal中
        threadLocal.set(connection);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new SQLException("当前没有事务，无法提交");
        }
        connection.commit();
        // 归还connection，并关闭数据库连接
        connection.close();
        // 移除当前线程中的Connection
        threadLocal.remove();
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new SQLException("当前没有事务，无法回滚");
        }
        connection.rollback();
        // 归还connection，并关闭数据库连接
        connection.close();
        // 移除当前线程中的connection
        threadLocal.remove();
    }

    /**
     * 关闭非事务connection
     *
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        Connection conn = threadLocal.get();
        // 如果connection不是事务连接
        if (connection != conn) {
            // 非事务连接和事务连接同时存在是可能的：先获取非事务连接，再开启事务
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        // 如果connection是事务连接，必然和conn相同
    }
}
