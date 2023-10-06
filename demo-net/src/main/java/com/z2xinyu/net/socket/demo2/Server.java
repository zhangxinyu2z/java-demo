package com.z2xinyu.net.socket.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收数据并输出到控制台上
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 服务端套接字
        ServerSocket ss = new ServerSocket(12345);
        // 监听客户端访问
        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str= null;
        while((str= br.readLine()) !=null) {
            System.out.println(str);
        }

        s.close();
    }
}
