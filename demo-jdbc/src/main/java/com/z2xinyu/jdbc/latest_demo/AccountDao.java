package com.z2xinyu.jdbc.latest_demo;

import com.xinyu.utils.v5.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 针对c3p0，不同的连接池close方法的实现不一样
 *
 * @author zhangxinyu
 * @create 2021-05-11 17:08
 */
public class AccountDao {
    public void update(String username, double balance) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        // 开启事务后，得到的是一个事务连接
        Connection connection = JdbcUtils.getConnection();
        String sql = "update account set balance=balance+? where name =?";
        queryRunner.update(connection, sql, balance, username);
    }
}
