package com.z2xinyu.jdbc.sqldate;

import com.xinyu.utils.v2.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * jdbc的日期类和util中日期类的转换
 *
 * @author zhangxinyu
 * @create 2021-05-10 16:20
 */
public class JdbcSqlDateConversionTest {
    @Test
    public void testUpdate() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into test_date value (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        // java.util.Date到java.sql.Date 、Time、TimeStamp的转换
        Date date = new Date();
        statement.setDate(1, new java.sql.Date(date.getTime()));
        statement.setTime(2, new Time(date.getTime()));
        statement.setTimestamp(3, new Timestamp(date.getTime()));
        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    @Test
    public void selectTest() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "select * from test_date";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Date date = resultSet.getDate(1);
             Date time = resultSet.getTime(2);
          Date timestamp = resultSet.getTimestamp(3);
            System.out.println(date + "   " + time + "   " + timestamp);
        }
    }
}
