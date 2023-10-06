package com.z2xinyu.io.cn.xy.io.sms.dao;

import cn.xy.io.sms.pojo.User;

public interface UserDao {
    // 注册
    public abstract boolean register(User u);
    // 登陆
    public abstract boolean login(String username,String password);
}
