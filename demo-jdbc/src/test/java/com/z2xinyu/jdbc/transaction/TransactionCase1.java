package com.z2xinyu.jdbc.transaction;

import com.xinyu.utils.v2.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC事务案例模拟：
 * 刘亦菲转账给刘德华1000元，刘亦菲账户扣款1000元，此时，银行服务器故障，刘德华账户没有增加1000元。
 *
 * @author zhangxinyu
 * @create 2021-05-11 1:03
 */
public class TransactionCase1 {
    @Test
    public void test() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = JdbcUtils.getConnection();
            // 开启事务，默认true，则表示每条sql都是单独的事务
            conn.setAutoCommit(false);
            String sql = "update account set balance = money + ? where name=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1, -1000);
            preparedStatement.setString(2, "刘亦菲");
            // 刘亦菲转账给刘德华1000元，亦菲-1000，德华+1000
            preparedStatement.executeUpdate();
            // 程序异常，但不处理
            if (true) {
                throw new RuntimeException();
            }
            preparedStatement.setDouble(1, 1000);
            preparedStatement.setString(2, "刘德华");
            preparedStatement.executeUpdate();
            conn.commit(); // 提交事务
        } catch (Exception e) { // 捕获程序异常进行处理
            // 如果发生异常导致程序终止，就回滚
            conn.rollback();
        } finally {
            JdbcUtils.close(conn,preparedStatement);
        }
    }
}
