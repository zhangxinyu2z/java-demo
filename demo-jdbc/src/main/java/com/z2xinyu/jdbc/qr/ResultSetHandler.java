package com.z2xinyu.jdbc.qr;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhangxinyu
 * @create 2021-05-11 14:16
 */
public interface ResultSetHandler<T> {
    /**
     * 用来将结果集中的数据封装到bean中
     * @param rs
     * @return
     */
    public T handle(ResultSet rs) throws SQLException;
}
