package com.z2xinyu.jdbc.qr;

import com.xinyu.utils.v3.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 使用dbutils提供的api和封装好的ResultSetHandler
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 16:49
 */
public class DbutilsQueryRunnerTest {
    /**
     * BeanHandler : 单行处理器,Bean
     * @throws SQLException
     */
    public void testQueryUserDbutils() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user where sid=?";
        Object[] params = {1};
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), params);
        System.out.println(user.getSid() + "," + user.getSname() + "," + user.getAge() + ","
                + user.getGender());
    }

    /**
     * MapHandler:单行处理器，Map<String,Object> key为列名
     * @throws SQLException
     */
    @Test
    public void testQueryByMapHandler() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user where sid=?";
        Object[] params = {1};
        Map<String, Object> rs = queryRunner.query(sql, new MapHandler(), params);
        System.out.println(rs);
    }

    /**
     * ScalarHandler:单行单列处理器
     * @throws SQLException
     */
    @Test
    public void testQueryByScalarHandler() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select count(*) from user";
        Number count = queryRunner.query(sql, new ScalarHandler<>());
        System.out.println(count.intValue());
    }

    /**
     * BeanListHandler:多行处理器，封装成List<Bean>
     */
    @Test
    public void testQueryByBeanListHandler() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user";
        List<User> userList = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        System.out.println(userList);
    }

    /**
     * 多行处理器！把结果集转换成List<Map<String,Object>>
     */
    @Test
    public void testQueryByMapListHandler() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user";
        List<Map<String, Object>> userList = queryRunner.query(sql,new MapListHandler());
        System.out.println(userList);
    }

    /**
     * ColumnListHandler:多行单列处理器， List<Object> 指定列名的一列
     * @throws SQLException
     */
    @Test
    public void testQueryByColumnListHandler() throws SQLException {
        DataSource dataSource = JdbcUtils.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select * from user";
        List<Object> userList = queryRunner.query(sql, new ColumnListHandler<>("sname"));
        System.out.println(userList);
    }
}
