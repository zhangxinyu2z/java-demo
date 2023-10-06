package com.z2xinyu.jdbc.qr;

import com.xinyu.utils.v3.JdbcUtils;
import junit.framework.TestCase;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhangxinyu
 * @create 2021-05-11 14:06
 */
public class CustomQueryRunnerTest extends TestCase {

    public void testUpdate() {
        DataSource dataSource = JdbcUtils.getDataSource();
        CustomQueryRunner<User> queryRunner = new CustomQueryRunner<>(dataSource);
        String sql = "update user set sname = ? where sid = ?";
        int i = queryRunner.update(sql, new Object[]{"刘亦菲", 1});
        System.out.println(i);
    }

    public void testQuery() {
        DataSource dataSource = JdbcUtils.getDataSource();
        CustomQueryRunner<User> queryRunner = new CustomQueryRunner<>(dataSource);
        String sql = "select * from user where sid=?";
        User user = queryRunner.query(sql, new ResultSetHandler<User>() {
            @Override
            public User handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }
                User user = new User();
                user.setSid(rs.getInt("sid"));
                user.setSname(rs.getString("sname"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                return user;
            }
        }, 2);
        System.out.println(user.getSid() + "," + user.getSname() + "," + user.getAge() + ","
                + user.getGender());
    }
}