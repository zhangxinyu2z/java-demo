package com.z2xinyu.io.otherstream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 随机访问
 * DataOutput
 *  |- RandomAccessFile
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf =
                new RandomAccessFile("RandomAccess.txt", "rw");
        raf.write(97);
        raf.write(2);
        raf.write(3);
        raf.writeChars("我"); // 以两个字节的形式写入

        // 修改偏移量
        raf.seek(2);

        // 返回文件中的偏移量，在此处发生下一次的读写
        System.out.println(raf.getFilePointer());

        System.out.println(raf.read());
        System.out.println(raf.read()); // 98
        System.out.println(raf.read()); // 17
        System.out.println(raf.read()); //-1


        System.out.print(new File("RandomAccess.txt").length()); // 5个字节


    }
}
