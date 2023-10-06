package com.z2xinyu.net.udp.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 控制台上录入数据接收发送到控制台上
 */
public class Send {
    public static void main(String[] args) throws IOException {
        // 创建发送数据包的套接字
        DatagramSocket ds = new DatagramSocket();
        // 封装要发送的数据报包

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s = br.readLine()) != null) {
            /**控制结束标记*/
            if (s.equals("break")) {
                break;
            }
            byte[] b = s.getBytes();
            DatagramPacket dp = new DatagramPacket(b,0,b.length,
                    InetAddress.getByName("127.0.0.1"), 10086);
            ds.send(dp);
        }
        ds.close();
    }
}
