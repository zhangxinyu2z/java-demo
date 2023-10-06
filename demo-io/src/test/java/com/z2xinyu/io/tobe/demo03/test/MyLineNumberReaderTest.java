package com.z2xinyu.io.tobe.demo03.test;

import java.io.FileReader;
import java.io.IOException;

/**
 * 测试MyLineNumberReader
 * 
 * @author zhang xinyu
 * @date 2021-04-13_20:31:06
 * @version v1.0
 */
public class MyLineNumberReaderTest {
    public static void main(String[] args) throws IOException {
        MyLineNumberReader in = new MyLineNumberReader(new FileReader("泰山吟.txt"));
        String line = null;
        // in.setLineNumber(10);
        // System.out.println(in.getLineNumber());
        while ((line = in.readLine()) != null) {
            System.out.println(in.getLineNumber() + " " + line);
        }
        in.close();
    }
}
