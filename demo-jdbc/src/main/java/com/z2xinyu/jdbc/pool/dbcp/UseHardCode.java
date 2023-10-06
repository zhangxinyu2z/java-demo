package com.z2xinyu.jdbc.pool.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 10:50
 */
public class UseHardCode {
    static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        // 硬编码会覆盖掉配置文件的配置
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/company");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("");

        // 设置初始化连接数
        basicDataSource.setInitialSize(10);
        // 设置最大连接数，20个后不再创建  版本不同的方法：setMaxTotal
        basicDataSource.setMaxTotal(20);
        // 设置最大空闲连接，多于就会销毁
        basicDataSource.setMaxIdle(10);
        // 设置最小空闲连接，少于2个就会增量
        basicDataSource.setMinIdle(2);
        // 设置最大等待时间 版本 setMaxWait
        basicDataSource.setMaxWaitMillis(1000);
    }

    public static void main(String[] args) {
        try {
            // for循环测试连接是否成功
            for (int i = 0; i < 10; i++) {
                Connection conn = basicDataSource.getConnection();
                System.out.println(conn.hashCode() + "...." + i);
                System.out.println(conn.getClass().getName());
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
