package com.z2xinyu.net.socket.demo3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        // 建立客户端的套接字
        Socket s = new Socket("127.0.0.1",10000);

        BufferedReader br = new BufferedReader(new FileReader("src\\socket\\demo3\\ServerThread.java"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String str = null;
        while((str= br.readLine())!=null) {
            bw.write(str);
            bw.flush();
            bw.newLine();
        }
        s.shutdownOutput();

       BufferedReader receive = new BufferedReader(new InputStreamReader(s.getInputStream()));
       System.out.println(receive.readLine());

       br.close();
       s.close();

    }
}
