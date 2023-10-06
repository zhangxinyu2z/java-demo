package com.z2xinyu.net.socket.demo3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多个客户端访问服务器，上传文件
 * 由于文件大小和代宽，上传的时间是不同的
 * 让服务器一直开启，监听客户端的访问，但是单纯使用while()让服务端监听，是按照代码的执行顺序进行的
 * 如果文件大，但是代宽小，就会导致时间的问题
 * 所以，利用线程，速度快的会优先执行。
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss= new ServerSocket(10000);

        Socket s = ss.accept();

        new Thread(new ServerThread(s)).start();
    }
}
