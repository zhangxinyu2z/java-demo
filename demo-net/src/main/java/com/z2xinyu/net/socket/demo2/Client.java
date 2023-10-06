package com.z2xinyu.net.socket.demo2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 客户端录入
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //
        Socket client = new Socket("127.0.0.1", 12345);
        BufferedReader br
                = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw
                = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String s = null;
        while((s = br.readLine())!=null) {
            if(s.equals("end")) {
                break;
            }
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
//        client.shutdownOutput();
        client.close();
    }
}
