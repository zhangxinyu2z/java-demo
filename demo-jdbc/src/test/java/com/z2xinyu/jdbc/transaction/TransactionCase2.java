package com.z2xinyu.jdbc.transaction;

import com.xinyu.utils.v2.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * 案例：李四骗了张三1w块
 * 保存点的使用
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-04 16:56
 */
public class TransactionCase2 {
    /*
     * 李四对张三说，如果你给我转1W，我就给你转100W。
     * ==========================================
     *
     * 张三给李四转1W（张三减去1W，李四加上1W）
     * 设置保存点！
     * 李四给张三转100W（李四减去100W，张三加上100W）
     * 查看李四余额为负数，那么回滚到保存点。
     * 提交事务
     */
    @Test
    public void fun() {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcUtils.getConnection();
            //手动提交
            con.setAutoCommit(false);

            String sql = "update account set balance=balance+? where name=?";
            pstmt = con.prepareStatement(sql);

            //操作1（张三减去1W）
            pstmt.setDouble(1, -10000);
            pstmt.setString(2, "zs");
            pstmt.executeUpdate();

            //操作2（李四加上1W）
            pstmt.setDouble(1, 10000);
            pstmt.setString(2, "ls");
            pstmt.executeUpdate();

            // 设置保存点
            Savepoint sp = con.setSavepoint();

            //操作3（李四减去100W）
            pstmt.setDouble(1, -1000000);
            pstmt.setString(2, "ls");
            pstmt.executeUpdate();

            //操作4（张三加上100W）
            pstmt.setDouble(1, 1000000);
            pstmt.setString(2, "zs");
            pstmt.executeUpdate();

            //操作5（查看李四余额）
            sql = "select balance from account where name=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "ls");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            double balance = rs.getDouble(1);
            //如果李四余额为负数，那么回滚到指定保存点
            if(balance < 0) {
                con.rollback(sp);
                System.out.println("张三，你上当了！");
            }

            //提交事务，回滚保存点不会提交事务
            con.commit();
        } catch(Exception e) {
            //回滚事务
            if(con != null) {
                try {
                    con.rollback();
                } catch(SQLException ex) {}
            }
            throw new RuntimeException(e);
        } finally {
            //关闭
            JdbcUtils.close(con, pstmt);
        }
    }
}
