package com.z2xinyu.net.udp.demo3;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * io流+多线程 -〉网络编程
 * 模拟聊天室功能
 *
 * 同时启动数据接收端和发送端
 */
public class Main {
    public static void main(String[] args) throws SocketException {
        DatagramSocket send = new DatagramSocket();
        DatagramSocket receive = new DatagramSocket(12345);

        Thread st = new Thread(new SendThread(send));
        Thread rt = new Thread(new ReceiveThread(receive));

        st.start();
        rt.start();
    }
}
