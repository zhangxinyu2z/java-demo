package com.z2xinyu.net.udp.demo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收数据端，不需要关闭流
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        // 创建socket接收数据包
        DatagramSocket ds = new DatagramSocket(10086);
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            // 接收数据
            ds.receive(dp);
            byte[] receiveData = dp.getData();

            System.out.println(dp.getAddress().getHostAddress()
                    + ":" + dp.getPort() + "," + new String(receiveData, 0, receiveData.length));
        }
    }
}

