package com.z2xinyu.io.otherstream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * InputStream
 *  | - SequenceInputStream
 * 其他输入流的逻辑串联。 它从一个有序的输入流集合开始，从第一个读取到文件结束，
 * 直到最后一个包含的输入流到达文件末尾。
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws IOException {
        // 合并两个输入流
        SequenceInputStream sis = new SequenceInputStream(
                new FileInputStream("input.txt")
                ,new FileInputStream("output.txt"));

        // 对多个输入流的合并,通过Vector获得Enumeration
        Vector<InputStream> v = new Vector<>();
        v.add(new FileInputStream("input.txt"));
        v.add(new FileInputStream("output.txt"));
        v.add(new FileInputStream("print.txt"));
        Enumeration<InputStream> e = v.elements();
        sis = new SequenceInputStream(e);

        BufferedReader br = new BufferedReader(new InputStreamReader(sis));
        BufferedWriter bw = new BufferedWriter(new FileWriter("sequence.txt"));

        String str = null;
        while((str = br.readLine()) != null) {
            bw.write(str);
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
