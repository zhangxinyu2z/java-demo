package com.z2xinyu.io.inout.process.stream.print_control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  字符打印流，底程调用了缓冲流
 */
public class PrintWriterDemo {
    public static void main(String[] args) throws IOException {
        String path = "print.txt";
        PrintWriter pw = new PrintWriter(new FileWriter(path),true);
        // PrintWriter不设置自动刷新，需要flush或者close才能将流中的数据输出
        // 设置自动刷新后，需要遇到println()才会生效
        pw.write(98);
        pw.print('c');
        pw.println("我");
//        pw.print("Hello");
//        pw.flush();
        pw.close();

    }
}
