package com.z2xinyu.net.socket.demo1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * socket是协议和你应用程序的一个接口，你通过它来实现通信,是用来做通信的一套API
 * socket就是插座的意思，把2个东西通过电线插在插座上，就可以通信了
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1", 10086);

        // 读取本地文件中的数据，写入到流中
        BufferedReader br =
                new BufferedReader(new FileReader("terminal.txt"));
        BufferedWriter bw
                = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String str = null;
        while((str= br.readLine())!= null) {
            bw.write(str);
            bw.newLine();
            bw.flush();
        }

        bw.write(System.lineSeparator());
        s.shutdownOutput(); // 告知服务器流中已经没有数据了，可以结束读取了
        // 接收服务端返回信息
        try {
            BufferedReader brc = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println(brc.readLine()); // 阻塞
        } catch (IOException e) {
            e.printStackTrace();
        }
        br.close(); // br是读取文件的流，必须要关闭
        s.close();  // socket关闭后，brc自然无法获取到输入流

    }
}
