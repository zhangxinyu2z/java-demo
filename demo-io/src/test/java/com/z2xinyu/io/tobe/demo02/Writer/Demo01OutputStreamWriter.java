package com.z2xinyu.io.tobe.demo02.Writer;

import java.io.*;

public class Demo01OutputStreamWriter {
    public static void main(String[] args) throws IOException {
        String path = "一剪梅·红藕香残玉簟秋.txt";
         write(path);
        writeString(path);

    }

    /**
     * 一次写一个字符串
     * 
     * @param path
     */
    private static void writeString(String path) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path));
        String s = "\t一剪梅·红藕香残玉簟秋";
        String s1 = "红藕香残玉簟秋。轻解罗裳，独上兰舟。云中谁寄锦书来？雁字回时，月满西楼。";
        String s2 = "花自飘零水自流。一种相思，两处闲愁。此情无计可消除，才下眉头，却上心头。";
        osw.write(s);
        osw.write("\r\n");
        osw.write(s1);
        // 换个行
        osw.write("\r\n");
        osw.write(s2);
        osw.flush();
        osw.close();
    }

    /**
     * 写单个字符
     * 
     * @param path
     * @throws IOException
     */
    private static void write(String path) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path));
        osw.write('a');
        osw.write('b');
        // 字符流如果不flush或者close，缓冲的数据不会被写入文件
        osw.flush();
        osw.close();
    }
}
