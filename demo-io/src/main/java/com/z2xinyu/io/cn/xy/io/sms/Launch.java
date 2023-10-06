package com.z2xinyu.io.cn.xy.io.sms;

import cn.xy.io.sms.dao.UserDao;
import cn.xy.io.sms.dao.impl.UserDaoImpl;
import cn.xy.io.sms.pojo.User;

import java.util.Scanner;

/**
 * 用户注册登录
 * 用文本文件替换用集合来存储用户信息
 *
 * @author z-xy
 */
public class Launch {
    public static void main(String[] args) {
        while (true) {
            System.out.println("欢迎进入：\n请选择：1、注册 2、登陆 3、退出");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            UserDao ud = new UserDaoImpl();
            // 1.7
            switch (choice) {
                case "1":
                    while (true) {
                        System.out.println("注册界面：");
                        System.out.println("请输入用户名：");
                        String username = sc.nextLine();
                        System.out.println("请输入密码：");
                        String password = sc.nextLine();
                        boolean result = ud.register(new User(username, password));
                        if (!result) {
                            System.out.println("用户名或密码重复，请重新输入");
                            continue;
                        } else {
                            System.out.println("注册成功，请登陆使用功能。");
                            break;
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        System.out.println("登陆界面：");
                        System.out.println("请输入用户名：");
                        String username = sc.nextLine();
                        System.out.println("请输入密码：");
                        String password = sc.nextLine();
                        boolean result = ud.login(username, password);
                        if (!result) {
                            System.out.println("用户名或密码错误，请重新输入。");
                            continue;
                        } else {
                            System.out.println("用户登陆成功");
                            System.out.println("跳转到使用界面。");
                            System.exit(0);
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.println("欢迎下次使用。");
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
