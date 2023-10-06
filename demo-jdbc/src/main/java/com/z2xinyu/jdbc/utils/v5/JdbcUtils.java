package com.z2xinyu.jdbc.utils.v5;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 把事务的功能封装到jdbcutils中，同时解决了多个线程共用一个Connection可能产生的线程并发安全问题
 * <p>
 * 状况1：线程1开启事务，线程2也开启事务，线程1检测没有事务Connection，生成事务Connection后，线程2抛出异常，事务Connection已经存在
 * 状况2：线程1开启了事务，创建了事务Connection，线程2开启事务方法跳过了检测，生成了新的事务Connection，事务Connection被设置成了全局的静态变量，
 * 如果线程1提交了事务，Connection就被关闭了，线程2调用Connection的方法就会抛出异常。
 * <p>
 * 如何解决：同一个线程中，获取的Connection是同一个
 *
 * @author zhangxinyu
 * @version 1.5
 * @create 2021-05-11 13:37
 */
public class JdbcUtils {
    /**
     * c3p0连接池
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 存储事务专用连接，保证线程安全
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    /**
     * 获取线程池数据源
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取线程池连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        // 如果当前线程不存在Connectioin，说明没有开启事务，反之，已经开启了事务
        if (connection != null) {
            return connection;
        }
        // 返回一个不是事务的连接
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
            throw new RuntimeException("你已经开启事务了，不要再重复操作");
        }
        // 事务开始
        connection = getConnection();
        // 事务开启
        connection.setAutoCommit(false);
        // 把事务线程存储
        threadLocal.set(connection);
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        //  获取beginTransaction提供的Connection
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new RuntimeException("你还没有开启事务");
        }
        connection.commit();
        // 关闭事务，把connection还给连接池
        connection.close();
        threadLocal.remove(); // 移除当前线程的对应的connection,这样获取当前线程的Connection就是null了

        /*
        close这一步做了什么？ 看下获取前和close后的connection对象
        com.mchange.v2.c3p0.impl.NewProxyConnection@68837a77 [wrapping: com.mysql.jdbc.JDBC4Connection@6be46e8f]
        com.mchange.v2.c3p0.impl.NewProxyConnection@68837a77 [wrapping: null]
        debug这个close方法，里面有两个方法，一个方法走到最后有一个returnObject 方法用来归还connection
        另一个setPelegate方法，把poolableConnection赋值为（Connection)null  抛出sql异常时做了一个判断，如果为null，就抛出connection
        已经关闭的提示，但实际上mysql的jdbc4Connection并没有关闭
         */
    }

    /**
     * 回滚事务，关闭事务
     *
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new RuntimeException("您还没有开启事务");
        }
        connection.rollback();
        connection.close();
        threadLocal.remove();
    }

    /**
     * 关闭非事务connection
     * 判断它是不是事务专用，如果是，就不关闭！
     * 如果不是事务专用，那么就要关闭！
     *
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(Connection connection) throws SQLException {
        Connection conn = threadLocal.get();
        // 如果不是事务专用连接，关闭
        if (conn == null) {
            // 说明现在没有开启事务,那么关闭的就是普通的Connection
            connection.close();
        }
        // 如果conn!=null,说明存在事务连接，判断和参数Connection是否相同，如果不同，说明不是事务连接，关闭
        if (conn != connection) {
            connection.close();
        }
    }
}
