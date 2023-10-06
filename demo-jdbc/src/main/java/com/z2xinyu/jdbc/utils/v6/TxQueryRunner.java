package com.z2xinyu.jdbc.utils.v6;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 封装Connection到QueryRunner的方法中，不必在使用中提供Connection和关闭Connection等操作
 * <p>
 * 通过JdbcUtils.getConnection()得到连接！有可能是事务连接，也可能是普通的连接！
 * JdbcUtils.releaseConnection()完成对连接的释放！如果不是事务连接，会直接关闭，事务连接在提交和回滚时就关闭了
 *
 * @author cxf
 */
public class TxQueryRunner extends QueryRunner {
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        int[] result = super.batch(con, sql, params);
        JdbcUtils.releaseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
            throws SQLException {
        Connection con = JdbcUtils.getConnection();
        T result = super.query(con, sql, rsh, params);
        JdbcUtils.releaseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        T result = super.query(con, sql, rsh);
        JdbcUtils.releaseConnection(con);
        return result;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        int result = super.update(con, sql);
        JdbcUtils.releaseConnection(con);
        return result;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        int result = super.update(con, sql, param);
        JdbcUtils.releaseConnection(con);
        return result;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection con = JdbcUtils.getConnection();
        int result = super.update(con, sql, params);
        JdbcUtils.releaseConnection(con);
        return result;
    }
}
