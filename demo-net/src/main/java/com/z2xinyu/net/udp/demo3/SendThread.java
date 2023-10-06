package com.z2xinyu.net.udp.demo3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable {
    private DatagramSocket ds;

    public SendThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DatagramPacket dp;
            for (String str = null;
                 (str = br.readLine()) != null && !"break".equals(str);
                 ds.send(dp)) {
                byte[] b = str.getBytes();
                dp = new DatagramPacket(b, 0, b.length
                        , InetAddress.getByName("192.168.1.4")
                        , 12345);
            }
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
