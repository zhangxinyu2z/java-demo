package com.z2xinyu.net;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * InetAddress ： 构造方法权限是default，无法在其他包外访问，但可以静态方法返回该类的对象
 */
public class InetAddressDemo {
    public static void main(String[] args) throws IOException {
        InetAddress i = InetAddress.getLocalHost();
        // 主机名：DESKTOP-8J9R7K0
        System.out.println(i.getHostName());
        // ip:192.168.1.4
        System.out.println(i.getHostAddress());
        System.out.println(i.getCanonicalHostName());

        // 通过ip或主机名获取InetAddress对象
        InetAddress i2 = InetAddress.getByName("DESKTOP-8J9R7K0");

        // 主机名不是电脑名
        InetAddress i3 = InetAddress.getByName("zhang xinyu");
        System.out.println(i2.getHostAddress());
        System.out.println(i3.toString());


//        Socket s = new Socket("127.0.0.1",10086);
//        InetAddress ia = s.getInetAddress();
//        System.out.println(Arrays.toString(ia.getAddress()));
//        System.out.println(ia.getHostName());
//        System.out.println(ia.getHostAddress());
//        System.out.println(ia.getCanonicalHostName());
    }
}
