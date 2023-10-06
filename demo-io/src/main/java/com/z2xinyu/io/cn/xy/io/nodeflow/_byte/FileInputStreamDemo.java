package com.z2xinyu.io.cn.xy.io.nodeflow._byte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FileInputStream是字节输入流,也是一个节点流
 * <p>
 * 读取文件数据的方法
 *
 * @author z-xy
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {

        // 获取文件的抽象路径
        File file = new File("input.txt");

        // 创建一个字节输入流,与指定文件建立连接
        FileInputStream fis = new FileInputStream(file);
/*
        int b = 0;
        // read方法来读取文件中的信息,会读取换行符
        while ((b = fis.read()) != -1) {
            // 汉字在不同的字符编码集有不同的字节组成，单独的char转换会导致乱码
            System.out.print((char) b);
        }
        */
        // 换一种方式来读取 数组
        // 数组容量不能太小
        byte[] bytes = new byte[1024];
        // 返回读取的字节数
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            // new string 使用系统默认的字符编码来decode
            System.out.print(new String(bytes, 0, len));
        }
        // 关闭与文件的连接
        fis.close();
    }
}
