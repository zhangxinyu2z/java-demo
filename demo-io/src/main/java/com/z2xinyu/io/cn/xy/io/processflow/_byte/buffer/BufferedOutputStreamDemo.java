package com.z2xinyu.io.cn.xy.io.processflow._byte.buffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 这是一个缓冲输出处理流
 *
 * @author z-xy
 */
public class BufferedOutputStreamDemo {
    public static void main(String[] args) throws IOException {

        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("output.txt"));
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("input.txt"));

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        bis.close();
        bos.close();
    }
}
