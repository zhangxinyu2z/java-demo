package com.z2xinyu.jdbc.basic;

import com.xinyu.utils.v2.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * Jdbc的基本操作
 *
 * @author zhangxinyu
 * @create 2021-05-10 0:46
 */
public class JdbcTest {
    /**
     * 测试insert、update、delete语句
     * executeUpate()返回的是匹配的行数，而不是影响的行数。
     *
     * @throws SQLException
     */
    @Test
    public void testExecuteUpdate() throws SQLException {
        Connection con = JdbcUtils.getConnection();
        Statement statement = con.createStatement();
        String sql = "insert into emp values (1018,'张三丰','修仙', 1007, now(), 40000, null, 20)";
        String sql2 = "update emp set ename= '阳顶天' where empno = 1015"; // 返回匹配的行数，不是影响的行数
        int affectedRows = statement.executeUpdate(sql2);
        System.out.println(affectedRows);
    }

    /**
     * 测试select语句及sql注入
     *
     * @throws SQLException
     */
    @Test
    public void testExecuteQuery() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        String deptno = "' or 1 =1";
        String sql = "select * from dept where deptno ='" + deptno;
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String deptNo = resultSet.getString(1);
            String dName = resultSet.getString(2);
            String loc = resultSet.getString(3);
            System.out.println(deptNo + ":" + dName + ":" + loc);
        }
    }

    /**
     * 获取表数据，通过元数据的方式遍历
     *
     * @throws SQLException
     */
    @Test
    public void testQueryByMetaData() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        Statement statement = conn.createStatement();
        String sql = "select * from emp";
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String s = rs.getString(i);
                System.out.print(s + ",");
            }
            System.out.println();
        }
    }

    /**
     * 测试预处理语句
     */
    @Test
    public void testPreparatedStatement() throws SQLException {
        /**
         * 测试预处理statement，把sql语句发发送给数据库进行语法检测，然后进行编译，最后传递参数
         * 下次再执行sql语句就不必再检测语法
         */
        String sql = "select *  from emp  where empno=?";
        Connection con = JdbcUtils.getConnection();
        System.out.println(con.getClass().getName());
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, 1002);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + ":");
            }
            System.out.println();
        }
    }

    /**
     * 测试statement批处理
     */
    @Test
    public void testStatementBatchUpdate() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        statement.addBatch("update emp set ename= '乾坤' where empno = 1015");
        statement.addBatch("update emp set ename= '乾坤' where empno = 1015");
        statement.addBatch("delete from emp where empno = 1016");
        int[] affectedRows = statement.executeBatch();
        System.out.println(Arrays.toString(affectedRows));
        affectedRows = statement.executeBatch(); // 再次执行，已经清空了批处理sql
        System.out.println(Arrays.toString(affectedRows));
    }

    /**
     * 测试PreparedStatement批处理
     */
    @Test
    public void testPreparedStatementBatchUpdate() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "update emp set comm = ? and deptno= ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            preparedStatement.setDouble(1, 40000 + i);
            preparedStatement.setInt(2, 30);
            preparedStatement.addBatch();
        }
        int[] affectedRows = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(affectedRows));
        affectedRows = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(affectedRows));
    }
}
