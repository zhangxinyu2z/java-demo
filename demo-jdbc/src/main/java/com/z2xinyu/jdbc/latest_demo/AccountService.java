package com.z2xinyu.jdbc.latest_demo;

import com.xinyu.utils.v5.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * service层，用来处理业务逻辑
 *
 * @author zhangxinyu
 * @create 2021-05-11 17:15
 */
public class AccountService {
    /**
     * 依赖dao，如果学到spring，就可以通过让spring来管理dao的创建
     */
    AccountDao accountDao = new AccountDao();

    public void update() throws SQLException {
        try {
            // 开启事务
            JdbcUtils.beginTransaction();
            // 操作数据
            accountDao.update("刘亦菲", -1000);
            accountDao.update("刘德华", 1000);
            // 提交事务
            JdbcUtils.commitTransaction();
            /**
             * 如果不在事务中让conn=null,getConection获取的是已经inner=null的代理Connection，再次调用Connection的方法就会出现异常
             */
            // 得到的是一个没有开启事务的connection
            Connection connection = JdbcUtils.getConnection();
            System.out.println("service层再次获取connection" + connection);
            JdbcUtils.releaseConnection(connection);
        } catch (SQLException e) {
            // 回滚事务
            JdbcUtils.rollbackTransaction();
        }
    }
}
