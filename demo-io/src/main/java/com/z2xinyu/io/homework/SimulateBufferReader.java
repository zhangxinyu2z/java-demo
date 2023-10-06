package com.z2xinyu.io.homework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

// 简单模拟缓冲流的newLine()
public class SimulateBufferReader {
    public static void main(String[] args) throws IOException {
        MyBufferedReader mr = new MyBufferedReader(new FileReader("input.txt"));
        String str= null;
        while((str=mr.readLine())!=null) {
            System.out.println(str);
        }
    }
}

class MyBufferedReader {
    private Reader in;

    public MyBufferedReader(Reader in) {
        this.in = in;
    }

    public String readLine() throws IOException {
        StringBuilder sb=  new StringBuilder();
        int i = 0;

        while((i = in.read())!=-1) {
            if (i =='\r') {
                continue;
            }
            if(i=='\n') {
                return sb.toString();
            } else {
                sb.append((char)i);
            }
        }
        return null;
    }
}


