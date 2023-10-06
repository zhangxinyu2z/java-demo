package com.z2xinyu.net.udp.demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receive {
    public static void main(String[] args) throws IOException {
        // 创建接收数据包的套接字
        DatagramSocket dg = new DatagramSocket(10086);
        // 创建数据包
        byte[] b = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b,b.length);
        // 接收数据
        dg.receive(dp); // 阻塞式，直到接收到数据
        // 得到ip和端口
        InetAddress address = dp.getAddress();
        String ip = address.getHostAddress();
        int port = dp.getPort();
        System.out.println("ip:" + ip + ",port:" + port);
        // 得到传输的数据
        byte[] data = dp.getData();
        System.out.println(new String(data, 0,data.length));
        // 关闭流
        dg.close();
    }
}
