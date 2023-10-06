package com.z2xinyu.io.cn.xy.io.nodeflow._byte.bytearray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 操作数组的字节流，是一个节点流
 * <p>
 * 内存操作流一般用来存储临时信息,程序结束，内存中消失，不需要close(),因为数据是存放在该流的缓存数组中的
 *
 * @author z-xy
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 把数据通过流写到内存中
        bos.write(12);
        bos.write('b');

        // 创建一个新分配的字节数组,使用缓存区的有效字节数作为数组的容量，用的是Arrays.copy()方法
        byte[] bytes = bos.toByteArray();

        // 读取数组字节作为缓存区，和bytes没有关联
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        int b = 0;
        while ((b = bis.read()) != -1) {
            System.out.print(b);
        }
    }
}
