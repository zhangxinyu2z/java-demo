package com.z2xinyu.net.base;

import junit.framework.TestCase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class TcpSocketTest extends TestCase {

    public void testStartSocket() throws IOException {
        startServer();
        startClient();
    }

    public void testUpload() throws IOException {
        startUploadServer();
        startUploadClient();
    }
    
    public void testUploadImg() throws IOException {
        startUploadImgServer();
        startUploadImgClient();
    }


    /**
     * 图片数据有丢失，因为有数据缓冲没有完全写出 用flush()
     */
    private void startUploadImgClient() throws IOException {
        // 1 创建客户端Socket对象
        Socket client = new Socket("192.168.3.5", 10086);
        // 2 字节包装流读取文件
        BufferedInputStream bis =
            new BufferedInputStream(new FileInputStream("C:\\Users\\zhang xinyu\\Pictures\\刘亦菲.jpg"));
        // 3 获取通道写入流
        BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());
        // 4 把数据写入通道流中
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
            bos.flush();
        }

        // 通知服务端数据已经读完了
        client.shutdownOutput();

        // 5 接收服务端反馈
        InputStream in = client.getInputStream();
        byte[] b2 = new byte[1024];
        int len2 = in.read(b2);
        String s = new String(b2, 0, len2);
        System.out.println(s);
        // 6 释放资源
        bis.close();
        client.close();
    }

    private void startUploadImgServer() throws IOException {
        // 1 创建接收端ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(10086);
        // 2 监听客户端连接请求，没有就一直阻塞
        Socket accept = serverSocket.accept();
        // 3 获取输入流，读取数据
        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        // 4 获取输入流，把数据写入到文件中
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("刘亦菲.jpg"));

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
            bos.flush();
        }

        // 5 给客户端一个反馈
        OutputStream out = accept.getOutputStream();
        out.write("图片上传成功".getBytes());
        // 6 释放资源
        bos.close();
        accept.close();
    }

    public void startClient() throws IOException {
        // 1 创建发送端Socket对象
        Socket client = new Socket("192.168.3.5", 10086);

        // 2 获取输出流，向通道写数据
        OutputStream out = client.getOutputStream();
        out.write("刘亦菲是个大美女".getBytes());

        // 接受服务端发过来的反馈
        InputStream in = client.getInputStream();
        byte[] b = new byte[1024];
        int len = in.read(b);
        String backInfo = new String(b,0,len);
        System.out.println("服务端返回的信息：" + backInfo);
        // 3 释放资源
        client.close();
    }

    public void startServer() throws IOException {
        // 1 创建接收端ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(10086);

        // 2 监听客户端连接,返回一个对应的Socket对象 服务端和客户端建立连接，accept方法只要没有侦听到新的客户端请求连接，就会一直阻塞，等待
        Socket accept = serverSocket.accept(); // 侦听并接受到此套接字的连接。此方法在连接传入之前一直阻塞。

        // 3 获取输入流，读取数据显示在控制台
        InputStream in = accept.getInputStream();
        byte[] b = new byte[1024];
        int length = in.read(b);
        String s = new String(b, 0, length);
        String ip = accept.getInetAddress().getHostAddress();
        System.out.println("来自" + ip + ":" + s);

        // 对客户端进行反馈，表示数据已经接收到
        OutputStream out = accept.getOutputStream();
        out.write("已经收到来自客户端的信息了".getBytes());

        // 4 关闭客户端通道
        accept.close();
    }

    /**
     * 上传文件到服务器端
     * 客户端发送数据，使用字符包装流 <br/>
     * 问题：<br/>
     * 服务端向客户端发送反馈数据，但是客户端并没有输出。原因是通道流中readLine并无法用null判断是否读取完数据，产生了阻塞
     * <p>
     * 解决方法一种是客户端向通道中写入一个结束标记,另一种就是使用jdk提供的shutdownOuput方法，告知服务器已经没有数据了，无需等待
     */
    public void startUploadClient() throws IOException {
        // 1 键盘录入数据
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1 从文本文件读取数据
        BufferedReader br = new BufferedReader(new FileReader("sw.txt"));
        // 2 创建客服端Socket对象
        Socket client = new Socket("192.168.3.5", 10086);
        // 3 获取通道输出流，把数据写入通道流中
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String line = null;
        while ((line = br.readLine()) != null) {
            /*if (line.equals("over")) {
                break;
            }*/
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 通知服务器不会有传输的数据了
        client.shutdownOutput();
        // 接收服务器端发送的信息
        BufferedReader backReceive = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String s = backReceive.readLine();
        System.out.println(s);
        // 4 释放资源
        client.close();
        br.close();
    }

    /**
     * 上传文件到服务器端
     * 使用字符包装流读取数据
     */
    public void startUploadServer() throws IOException {
        // 1 建议服务端ServerSocket
        ServerSocket serverSocket = new ServerSocket(10086);

        // 2 监听客户端连接
        Socket accept = serverSocket.accept();

        // 3 从通道中读入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        String line = null;
        // 把数据写入到文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("udp.txt"));

        while ((line = br.readLine()) != null) {
            // System.out.println(line); // 打印在控制台上
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 给客户端一个反馈
        BufferedWriter bwBack = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bwBack.write("数据下载成功");
        bwBack.flush();

        // 4 释放资源
        accept.close();
        bw.close();
        bwBack.close();

    }
}
