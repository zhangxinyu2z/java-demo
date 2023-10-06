package com.z2xinyu.net.socket.demo3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket s;

    public ServerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("serverThread.txt"));
            String str = null;
            while ((str = br.readLine()) != null) {
                bw.write(str);
                bw.newLine();
                bw.flush();
            }

            // 返回一条信息
            BufferedWriter info  = new BufferedWriter(
                    new OutputStreamWriter(s.getOutputStream()));
            info.write("已经接收到了。");
            info.newLine();
            info.flush();

            bw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
