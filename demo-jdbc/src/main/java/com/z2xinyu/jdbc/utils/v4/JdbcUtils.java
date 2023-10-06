package com.z2xinyu.jdbc.utils.v4;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 把业务层的事务方法封装起来，隐藏Connection，在单线程的情况下使用同一个Connection
 *
 * <p>
 * c3p0的close()会关闭事务,会调用setAutoCommit(true)，事务连接关闭后，再次调用Connection的方法会抛出异常：无法操作closed的Connection
 * 事务未开启前，不能调用Connection的commit、rollback方法，事务开启后，可以多次调用。
 * <p>
 * 事务的操作在service层完成，dao层只关注对数据的操作
 * 事务要保证是同一个Connection，Connection也不应该出现在Service层，全部交给jdbcutils
 * 修改jdbcutils,把Connection隐藏起来，封装并提供开启务，提交事务，关闭事务的方法，以及获取connection的方法
 * <p>
 * 如何关闭没有关闭（归还）没有开启事务的Connection?（一直不归还连接池的资源就耗尽了）
 * 在dao中是不明确事务是事务连接还是非事务连接的，servie根据业务需求，确定是否需要开启事务，但是service应该只关注业务，
 * 为了避免connection的出现，将处理事务的方法交给jdbcutils来处理，所以jdbcutils是知道connection是事务连接还是非事务连接
 * </p>
 *
 * @author zhangxinyu
 * @version 1.4
 * @create 2021-05-11 13:37
 */
public class JdbcUtils {
    /**
     * 使用c3p0连接池，配置文件的名称固定
     */
    private static DataSource dataSource = new ComboPooledDataSource();

    /**
     * 事务Connection，保证事务中的Connection是同一个
     */
    private static Connection connection = null;

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
        // 业务逻辑调用beginTransaction()开启事务，bt方法获取事务专用连接
        if (connection != null) {
            return connection;
        }
        // 无需开启事务，不必确认是同一个Connection
        return dataSource.getConnection();
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() throws SQLException {
        // 校验：已经存在事务连接
        if (connection != null) {
            throw new RuntimeException("你已经开启事务了，不要再重复操作");
        }
        // 获取一个事务专用连接
        connection = getConnection();
        // 开启事务（设置手动提交,默认是自动提交的，mysql默认每一条sql语句都是单独的一个事务）
        connection.setAutoCommit(false);
    }

    /**
     * 提交事务，结束事务
     *
     * @throws SQLException
     */
    public static void commitTransaction() throws SQLException {
        // 如果没有开启事务，不合理的调用会导致调用commit出现NullPointerException,
        if (connection == null) {
            throw new RuntimeException("你还没" +
                    "有开启事务");
        }
        // 提交事务
        connection.commit();
        // 关闭事务，把Connection还给连接池（c3p0连接池的实现，close()方法会调用setAutoCommit(true))
        // 连接池中获取的Connection是对JDBC4Collection的包装，close方法会调用inner=null关闭数据库连接，
        connection.close();
        // 连接池获取的Connection对象没有被回收，但是inner=null被设置为null,再调用就会出现NullpointerException
        connection = null;
    }

    /**
     * 回滚事务，结束事务
     *
     * @throws SQLException
     */
    public static void rollbackTransaction() throws SQLException {
        if (connection == null) {
            throw new RuntimeException("您还没有开启事务");
        }
        // 回滚事务
        connection.rollback();
        // 关闭事务，归还连接
        connection.close();
        // 销毁事务连接
        connection = null;
    }

    /**
     * 关闭非事务connection
     *
     * @param conn
     * @throws SQLException
     */
    public static void releaseConnection(Connection conn) throws SQLException {
        // 说明现在没有开启事务,那么关闭的就是普通的Connection
        if (connection == null) {
            conn.close();
        }
        // 如果connection!=null,说明存在事务连接，判断和conn是否相同，如果不同，说明不是事务连接
        if (connection != conn) {
            conn.close();
        }
    }
}
