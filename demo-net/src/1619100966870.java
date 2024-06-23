package com.dhjy.d03.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 文件上传客户端
 * 
 * @author zhang xinyu
 * @date 2021-04-22 22:15:16
 * @version v1.0
 */
public class D04UploadClient {
    public static void main(String[] args) throws IOException {
        // 1
        Socket client = new Socket("192.168.3.5", 10086);
        // 2
        BufferedInputStream bis =
            new BufferedInputStream(new FileInputStream(".\\src\\com\\dhjy\\d03\\tcp\\D04UploadClient.java"));
        // 3
        BufferedOutputStream bos = new BufferedOutputStream(client.getOutputStream());

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
            bos.flush();
        }
        // 4
        client.shutdownOutput();
        // 5
        byte[] b2 = new byte[1024];
        int len2 = 0;
        InputStream in = client.getInputStream();
        len2 = in.read(b2);
        String s = new String(b2, 0, len2);
        System.out.println(s);
        // 6
        bis.close();
        client.close();
    }
}
