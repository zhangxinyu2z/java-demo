package com.z2xinyu.io.cn.xy.io.nodeflow._byte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileOutputStream是一个字节输出流,也是一个节点流
 * <p>
 * 演示写入数据到文件中
 * 正常换行:
 * windows ：\r\n
 * linux：   \n
 * mac：\r
 *
 * @author z-xy
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) {
        File file = new File("input.txt");

        /*
        // 不需要自己创建，构建FileOutputStream对象会创建该文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        // 建立与input.txt文件连接的字节输出流
        FileOutputStream fos = null;
        try {
            /**
             * 这一行代码进行了三步操作
             * 1:没有file文件就创建,有则覆盖;2:创建了fos对象;3:与file对象建立了联系
             * tip:如果不想覆盖,追加参数true
             */
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // ASCII码 48 对应 0
            fos.write(48);
            fos.write(65);
            fos.write(97);
            // 57 对应 9
            fos.write(57);
            // 55 对应 7
            fos.write(55);
            fos.write("我".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fos为null,自然不需要close()
            if (fos != null) {
                try {
                    /*
                     *  这一步的操作:
                     *  1:关闭输出流,释放与此流相关的系统资源
                     *  2:让流对象变成垃圾,这样可以被垃圾回收器回收
                     *  3:通知系统去释放和该文件相关的资源（是系统创建了这个文件，会参与管理）
                     */
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
