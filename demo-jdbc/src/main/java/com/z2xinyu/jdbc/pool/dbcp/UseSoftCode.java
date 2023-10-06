package com.z2xinyu.jdbc.pool.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 11:09
 */
public class UseSoftCode {
    // 声明一个DataSource源,也就是驱动
    static BasicDataSource bs = null;
    // properties集合，读取properties文件
    static Properties properties = new Properties();

    static {
        // 用类加载器加载文件获得流
        InputStream rs = UseSoftCode.class.getClassLoader().getResourceAsStream("dbcp.properties");
        try {
            properties.load(rs);
            bs = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // 来个for循环测试配置是否正确
            for (int i = 0; i < 50; i++) {
                // 从数据池中取出连接
                Connection connection = bs.getConnection();
//                System.out.println(connection.getClass().getName());          // org.apache.commons.dbcp2.PoolingDataSource$PoolGuardConnectionWrapper
                // 使用完毕将连接放回数据池(这里是装饰后的的close方法，并不是JDBC原生的close)
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
