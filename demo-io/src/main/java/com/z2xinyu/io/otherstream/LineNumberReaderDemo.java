package com.z2xinyu.io.otherstream;


import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Reader
 *  |- BufferedReader
 *      |- LineNumberReader 读取行号的缓冲处理流
 *  参考：https://www.cnblogs.com/xxNote/p/6664592.html
 */
public class LineNumberReaderDemo {
    public static void main(String[] args) throws IOException {
        LineNumberReader ln = new LineNumberReader(new FileReader("用户信息.txt"),2);

        /**
         * mark标记一个char,可以reset到标记点，但是buffersize-markedchar >= readAheadLimit
         * 的时候，readAheadLimit会失效
         * TODO:readAheadLimit是什么？
         */
//        System.out.println(ln.getLineNumber());
//        ln.read();
        ln.mark(1);
        ln.read();
//        ln.reset();
//        System.out.println(ln.read());

        /// 2、设置行号,但不会更改流中的实际位置
        ln.readLine();
        ln.readLine();
        System.out.println(ln.getLineNumber()); // 2
        ln.setLineNumber(1);
        ln.readLine();
        ln.readLine();
        System.out.println(ln.getLineNumber()); // 3

        // 3、模仿行号功能
        MyLineNumberReader my = new MyLineNumberReader(new FileReader("倚天屠龙记.txt"));
        my.setLineNumber(12);
        my.readLine();
        System.out.println(my.getLineNumber());
    }
}

/**
 * 模拟功能实现
 */
class MyLineNumberReader  {
    private Reader reader;
    private int lineNumber = 0;

    public MyLineNumberReader(Reader reader) {
        this.reader = reader;
    }
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    public int getLineNumber() {
        return lineNumber;
    }

    public String readLine() throws IOException {
        lineNumber++;

        StringBuilder sb= new StringBuilder();
        int ch = reader.read();
        if (ch == '\r') {
            sb.append(ch);
        } else if (ch=='\n') {
            return sb.toString();
        } else {
            sb.append(ch);
        }
        return null;
    }

}