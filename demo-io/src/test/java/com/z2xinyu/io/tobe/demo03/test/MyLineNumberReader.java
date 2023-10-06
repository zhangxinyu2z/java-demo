package com.z2xinyu.io.tobe.demo03.test;

import java.io.IOException;
import java.io.Reader;

/**
 * 模拟LineNumberReader的功能
 * 
 * @author zhang xinyu
 * @date 2021-04-13_20:27:41
 * @version v1.0
 */
public class MyLineNumberReader extends MyBufferedReader {
    private Reader in;
    private int lineNumber = 0;

    public MyLineNumberReader(Reader in) {
        super(in);
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String readLine() throws IOException {
        lineNumber++;
        return super.readLine();
    }
}
