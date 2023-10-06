package com.z2xinyu.net.udp.demo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Send {
    public static void main(String[] args) throws IOException {
        // 创建发送数据包的套接字
        DatagramSocket sendSocket = new DatagramSocket();

        // 数据
        byte[] b = "我爱你".getBytes();
        // 得到ip port
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        // 打包数据
        DatagramPacket dp  = new DatagramPacket(b, b.length,ip,port);
        // 发送数据报
        sendSocket.send(dp);

        // 关闭流
        sendSocket.close();
    }
}
