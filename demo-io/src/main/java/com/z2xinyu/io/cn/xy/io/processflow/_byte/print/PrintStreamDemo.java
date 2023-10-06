package com.z2xinyu.io.cn.xy.io.processflow._byte.print;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 字节打印流
 * <p>
 * 这类流在写入的时候有自动flush 的功能
 * <p>
 * <p>
 * PrintStream ps=new PrintStream(new OutputStream("c://..."));
 * System.setOut(ps);
 * System.Out.println("jfdajsflkajdf");//这样将不会在打印到命令行窗口，而是指定的
 * PrintStream目标文件里面去
 *
 * @author z-xy
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        String path = "print.txt";
        // path -> new FileOutputStream(path);
        PrintStream ps = new PrintStream(path);

        // PrintStream ps = new PrintStream(new FileOutputStream(path),true);  // 遇到换行会自动刷新缓冲区
        ps.append('a').append('b').append('c');
        ps.write(76);

        // print方法使用的BufferedWriter缓存区,把数据写进输出流中
        // 底程调用是write
        ps.println(98);
        // bw调用了flush,把写入基础输出流的缓存的字节刷新
        ps.print(67);
        ps.print("你好");

        BufferedOutputStream bos = new BufferedOutputStream(ps);
        // bos没有调用flush,所以需要手动flush,或者构造PrintStream时自动flush
        bos.write(69);
//        bos.flush();
        // 刷新流并关闭底程输出流
        bos.close();
    }
}
