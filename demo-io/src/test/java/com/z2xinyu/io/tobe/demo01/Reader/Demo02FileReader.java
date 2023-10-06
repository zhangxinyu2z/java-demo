package com.z2xinyu.io.tobe.demo01.Reader;

import java.io.FileReader;
import java.io.IOException;

public class Demo02FileReader {
    public static void main(String[] args) throws IOException {
        String path = "File.txt";
        FileReader fr = new FileReader(path);

        // int i = fr.read();
        // System.out.println(i);
        char[] c = new char[3];
        int len = fr.read(c);
        System.out.println("len=" + len);
        System.out.println(new String(c));
        len = fr.read(c);
        System.out.println("len=" + len);
        System.out.println(new String(c));
        len = fr.read(c);
        System.out.println("len=" + len);
        System.out.println(new String(c));
        len = fr.read(c);
        System.out.println("len=" + len);
        System.out.println(new String(c));

        /**
         * read方法读取到文件末尾返回-1 len是读取到的字符的个数 字符流读取的是一个一个字符，好像没有字节编码的问题
         */
        fr.close();

        test1("泰山吟.txt");
    }

    public static void test1(String path) throws IOException {
        FileReader fr = new FileReader(path);
        int len = 0;
        char[] c = new char[1024];
        while ((len = fr.read(c)) != -1) {
            System.out.print(new String(c, 0, len));
        }
        fr.close();
    }
}
