package com.z2xinyu.net.base;

import junit.framework.TestCase;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class InetAddressTest extends TestCase {

    public void testGetInfo() throws UnknownHostException {
        // 根据主机名或者IP地址的字符串表示得到IP地址对象
        InetAddress inetAddress = InetAddress.getByName("192.168.1.4");

        String hostName = inetAddress.getHostName();
        String hostAddress = inetAddress.getHostAddress();

        System.out.println(hostName); // DESKTOP-HGEPP82 ipconfig
        System.out.println(hostAddress); // 192.168.3.5
    }
}
