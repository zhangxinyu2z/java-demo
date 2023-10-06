package com.z2xinyu.io.bytes;

import java.io.*;

/**
 * @author zhangxinyu
 * @date 2023/8/28
 **/
public class FileInputStreamTest {

    /**
     * 1次读取1个字节
     */
    public static void test1() {
        String fileName = "File.txt";
        FileInputStream fis = null; // 读取文件时，系统不会帮助创建文件
        try {
            fis = new FileInputStream(fileName);

            int b;
            while ((b = fis.read()) != -1) {
                System.out.println(b); // 转char会有问题，因为汉字的字节数不确定
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试一次读取一个数组方法
     */
    public static void test2() {
        String fileName = "File.txt";
        FileInputStream fis = null; // 读取文件时，系统不会帮助创建文件
        try {
            fis = new FileInputStream(fileName);

            // 文件内容 光标到java后完毕
            /*hello
             *world
             *java
             */
            byte[] bytes = new byte[5];
            // 第一次读
            int len = fis.read(bytes);
            System.out.println(len);
            System.out.print(new String(bytes));
            // 第2次读
            len = fis.read(bytes);
            System.out.println(len);
            System.out.print(new String(bytes));
            // 第3次读
            len = fis.read(bytes);
            System.out.println(len);
            System.out.print(new String(bytes));
            // 第4次读
            len = fis.read(bytes);
            System.out.println(len);
            /*
             最后一次读到的数据是ava
             用一个数组读取是元素替换，索引3，4的\n j没有替换掉
             所以读取到了ava \n j
             */
            System.out.print(new String(bytes));

            /*
             * 经过测试，read(byte)返回-1代表读取到文件末尾
             * 返回的len是读取的字节数
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 1次读取一个数组 最终版代码
     */
    public static void test3() {

        String fileName = "File.txt";
        FileInputStream fis = null; // 读取文件时，系统不会帮助创建文件
        try {
            fis = new FileInputStream(fileName);

            byte[] bytes = new byte[1024]; // 1kib = 1024b 1kb等于1024字节
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                // 或许没有这么多字节，但是数组的容量又那么大，截取有效部分
                String s = new String(bytes, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
