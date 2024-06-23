package com.z2xinyu.io.cn.xy.io.sms.dao.impl;

import com.z2xinyu.io.cn.xy.io.sms.dao.UserDao;
import com.z2xinyu.io.cn.xy.io.sms.pojo.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDaoImpl implements UserDao {
    /**
     * 在学习集合的时候使用集合作为数据库，保存用户信息
     * 这里使用配置文件保存用户信息
     */
    private static File file = new File("用户信息.txt");

    static {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean register(User u) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
             BufferedReader br = new BufferedReader(new FileReader(file));) {
            String info = null;
            while ((info = br.readLine()) != null) {
                String[] s = info.split("=");
                if (s[0].equals(u.getUsername())) {
                    return false;
                }
            }
            bw.write(u.getUsername() + "=" + u.getPassword());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("用户注册失败。");
        }
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        boolean flag = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String info = null;
            while ((info = br.readLine()) != null) {
                String[] user = info.split("=");
                if (username.equals(user[0]) && password.equals(user[1])) {
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
