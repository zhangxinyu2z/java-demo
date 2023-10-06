package com.z2xinyu.io.tobe.demo01.Reader;

import java.io.*;

/**
 * 查看api，FileReader继承自InputStreamReader，在构造方法中会先初始化InputStreamReader
 * 用来简写替代
 * @author zhang xinyu
 *
 */
public class Demo01InputStreamReader {
    public static void main(String[] args) throws IOException {

        String path = "泰山吟.txt";
        read(path);
        readByCharArray(path);
    }
    /**
     * 1次读取1个字符数组
     * @param path
     * @throws IOException
     */
    public static void readByCharArray(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        int len = 0;
        char[] ch = new char[1024];
        while ((len = isr.read(ch)) != -1) {
            String s = new String(ch, 0, len);
            System.out.println(s);
        }
        isr.close();
    }
    
    /**
     * 1次读取1个字符
     * @param path
     * @throws IOException
     */
    public static void read(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        int ch = 0;
        while ((ch = isr.read()) != -1) {
            System.out.print((char)ch);
        }
        isr.close();
    }

}
