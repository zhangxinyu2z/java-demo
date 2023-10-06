package com.z2xinyu.jdbc.latest_demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xinyu.utils.v4.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试多线程下同一个Connection的问题，以及事务开启前后Connection的一些方法
 *
 * c3p0调用close方法，会关闭事务,会调用setAutoCommit(true);
 *
 * @author zhang xinyu
 * @version v1.0
 * @date created in 2021-05-18 13:49
 */
public class JdbcUtilsTest {
    @Test
    public void fun() throws SQLException {
        /*
            事务默认是关闭的，此时不能调用commit和rollback方法
            Can't call commit  when autocommit=true
            Can't call rollback  when autocommit=true
            开始事务之后，commit close rollback 可以重复调用
         */
        Connection connection = JdbcUtils.getConnection();
        connection.setAutoCommit(false);
        connection.commit();
        connection.close();
        connection.rollback();
        DataSource dataSource = new ComboPooledDataSource();
        Connection c1 = dataSource.getConnection();
        Connection c2 = dataSource.getConnection();
        System.out.println(c1);
        System.out.println(c2);
        // false,NewProxyConnection是代理类，不是同一个connection
        System.out.println(c1 == c2);
    }

    @Test
    public void fun2() throws SQLException, InterruptedException {
        /*
            多线程条件下， 如果是同一个connection,
                线程1开始事务，进行转账业务：张三-1000，李四+1000，线程2也开启事务：张三-1000，李四+1000
                线程1执行-1000后， 被线程2抢走执行权，线程2执行完业务，然后将事务提交,并关闭了事务
                此时线程1再提交事务就会出现了sql异常
         */
        Connection conn = JdbcUtils.getConnection();

        class TransferAccountThread extends Thread {
            @Override
            public void run() {
                try {
                    conn.setAutoCommit(false);
                    update(conn, "刘亦菲", -1000);
                    update(conn, "刘德华", 1000);
                    conn.commit();
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                } finally {
                    try {
                        conn.rollback();
                        conn.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        new TransferAccountThread().start();
        Thread.sleep(3000);
        new TransferAccountThread().start();

    }

    public void update(Connection connection, String username, double balance) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update account set balance=balance+? where name=?";
        queryRunner.update(connection, sql, balance, username);
    }
}

