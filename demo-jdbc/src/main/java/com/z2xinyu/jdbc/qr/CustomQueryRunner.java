package com.z2xinyu.jdbc.qr;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模拟实现QueryRunner类
 *
 * @author zhangxinyu
 * @create 2021-05-11 13:57
 */
public class CustomQueryRunner<T> {
    private DataSource dataSource;

    public CustomQueryRunner() {
    }

    public CustomQueryRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public T query(String sql, ResultSetHandler<T> rsHandler, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {
            return rsHandler.handle(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int update(String sql, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = createPreparedStatement(conn, sql, params)
        ) {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过Conn创建一个预处理sql，并赋值完毕
     *
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    private PreparedStatement createPreparedStatement(Connection connection, String sql,
                                                      Object[] params) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        return pstmt;
    }
}
