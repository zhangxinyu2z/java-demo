package com.z2xinyu.jdbc.pool.custom;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 自定义Connection
 * @author dhjy
 * @version v1.0
 * @date created in 2021-10-04 22:54
 */
public class CustomConnection extends ConnectionWrapper {
    private CustomPool customPool;

    public CustomConnection(Connection con, CustomPool customPool) {
        super(con);
        this.customPool = customPool;
    }

    @Override
    public void close() throws SQLException {
        customPool.add(this);
    }
}
