package com.z2xinyu.io.tobe.demo03.test;

import java.io.FileReader;
import java.io.IOException;

public class MyBufferedReaderTest {
    public static void main(String[] args) throws IOException {
        MyBufferedReader in = new MyBufferedReader(new FileReader("泰山吟.txt"));

       String line = null;
       while((line = in.readLine()) !=null) {
           System.out.println(line);
       }
    }
}
