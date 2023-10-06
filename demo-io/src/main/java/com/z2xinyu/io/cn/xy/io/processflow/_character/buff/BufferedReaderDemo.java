package com.z2xinyu.io.cn.xy.io.processflow._character.buff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符缓冲流
 *
 * @author z-xy
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

        String str = null;
        while ((str = br.readLine()) != null) {
            bw.write(str);
            bw.newLine();
            // 缓冲区中的字符需要刷新
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
