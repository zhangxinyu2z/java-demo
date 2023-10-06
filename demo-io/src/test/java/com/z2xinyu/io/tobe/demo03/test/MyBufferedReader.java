package com.z2xinyu.io.tobe.demo03.test;

import java.io.IOException;
import java.io.Reader;

public class MyBufferedReader {
    private Reader in;

    public MyBufferedReader(Reader in) {
        this.in = in;
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch = 0;
        while ((ch = this.in.read()) != -1) {
            if (ch == '\r') {
                continue;
            }
            if (ch == '\n') {
                return sb.toString();
            }
            sb.append((char)ch);
        }
        // 避免出现：如果没有读到换行符，导致返回null
        if(sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }

    public void close() throws IOException {
        this.in.close();
    }
}
