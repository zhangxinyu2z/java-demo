package com.z2xinyu.annotation.jpaimpl.dao;

import com.xinyu.annotation.jpaimpl.domain.User;

/**
 * 实现这样的效果，只需要传递参数信息就完成dao的功能
 * txqueryrunner封装了数据库连接和一些功能的实现，但是还是需要自己定义sql,params这些参数信息，这些代码是
 * 相同的，能不能将它们也封装起来？
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 21:25
 */
public class BaseDaoImpl extends BaseDao<User> {

    @Override
    public void add(User bean)  {
        super.add(bean);
    }
}
