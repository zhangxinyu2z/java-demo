package com.z2xinyu.net.socket.demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket服务端套接字，等待请求，可能返回结果
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务端套接字
        ServerSocket ss = new ServerSocket(10086);
        // 用来监听访问服务端的客户端
        Socket s = ss.accept();
        // 封装套接字的输入流来读取信息
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("copy.txt"));
        String str = null;
        while ((str = br.readLine()) != null) {
            // readLine读取文件，读到null就会结束
            // 但是Socket中不是，虽然流中的数据读写完了，但是流还在继续等待客户端的写入
            // readLine()不会返回null,在等待接收数据，此时，客户端也在等待服务器返回信息，就发生了阻塞
            // 在客户端使用shutdownoutput,等于告诉服务器流中已经没有数据了，可以结束读取了
            bw.write(str);
            bw.newLine();
            bw.flush();
        }

        // 返回客户端一个接收的回应
        BufferedWriter bws = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bws.write("已经收到你的请求。");
        bws.newLine();
        bws.flush();  // SocketException: Connection reset 必要

        bw.close();
        s.close();
    }
}
