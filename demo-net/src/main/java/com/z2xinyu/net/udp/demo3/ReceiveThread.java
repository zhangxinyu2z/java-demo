package com.z2xinyu.net.udp.demo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class ReceiveThread implements Runnable {
    private DatagramSocket ds;

    public ReceiveThread(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] b = new byte[1024];
                DatagramPacket dp = new DatagramPacket(b, b.length);

                ds.receive(dp);
                byte[] data = dp.getData();
                System.out.println("from " + dp.getAddress().getHostAddress() + ":" + dp.getPort()
                        + " : " + new String(data, 0, data.length));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
