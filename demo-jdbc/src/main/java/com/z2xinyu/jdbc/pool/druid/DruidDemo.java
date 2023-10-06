package com.z2xinyu.jdbc.pool.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * druid连接池的使用
 *
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-05 12:43
 */
public class DruidDemo {
    @Test
    public void hardCode() throws SQLException {
        //数据源配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1/company?serverTimezone=UTC");
        //这个可以缺省的，会根据url自动识别
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        //下面都是可选的配置
        //初始连接数，默认0
        dataSource.setInitialSize(10);
        //最大连接数，默认8
        dataSource.setMaxActive(30);
        //最小闲置数
        dataSource.setMinIdle(10);
        //获取连接的最大等待时间，单位毫秒
        dataSource.setMaxWait(2000);
        //缓存PreparedStatement，默认false
        dataSource.setPoolPreparedStatements(true);
        //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码
        dataSource.setMaxOpenPreparedStatements(20);
        //获取连接
        Connection connection = dataSource.getConnection();
        //关闭连接
        connection.close();
    }

    /**
     * 使用配置文件
     *
     * @throws Exception
     */
    @Test
    public void softCode() throws Exception {
        //数据源配置
        Properties properties = new Properties();
        //通过当前类的class对象获取资源文件
        InputStream inputStream = Test.class.getResourceAsStream("druid.properties");
        properties.load(inputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());
    }
}
