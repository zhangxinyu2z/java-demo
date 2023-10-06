package com.z2xinyu.io.cn.xy.io.processflow._byte.buffer;

import java.io.*;

/**
 * 这是一个字节包装处理流
 * 思考水杯装水
 * 水杯可以用来缓冲，但是还是依赖水
 * <p>
 * 增加缓冲功能，避免频繁读写硬盘，可以初始化缓冲数据的大小，由于带了缓冲功能，所以就写数据的时候需要使用flush方法
 * 字节缓冲区流仅提供缓冲区，为高效设计，但是真正的读写操作还是靠基本的流对象实现
 *
 * @author z-xy
 */
public class BufferedInputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedInputStream fis = new BufferedInputStream(
                new FileInputStream(file));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, len));
        }
        fis.close();
    }
}
