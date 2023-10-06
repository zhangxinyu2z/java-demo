package com.z2xinyu.io.cn.xy.io.processflow._byte.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataOutputStream：数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。然后应用程序可以使用数据输入流将数据读入。
 * DataInputStream：数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。对于多线程访问不一定是安全的。
 * 线程安全是可选的，它由此类方法的使用者负责。
 * InputStream
 * | - DataInputStream
 * 写入到文件中的数据看不懂,但是可以读
 *
 * 应用：
 * 有时候没有必要存储整个对象的信息，而只是要存储一个对象的成员数据，成员数据的类型假设都是Java的基本数据类型，
 * 这样的需求不必使用到与Object输入、输出相关的流对象，可以使用DataInputStream、DataOutputStream来写入或读出数据
 *
 * https://www.iteye.com/blog/zds420-897686
 *
 * @author z-xy
 */
public class DataInputStreamDemo {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
        dos.writeInt(23);
        dos.writeChar('2');
        dos.writeBoolean(false);
        dos.writeUTF("啊");
        dos.close();

        DataInputStream dis = new DataInputStream(new FileInputStream(path));
        int i1 = dis.readInt();
        // 4个字节
        System.out.println(i1);
        // 2个字节
        System.out.println(dis.readChar());
        System.out.println(dis.readBoolean());
        System.out.println(dis.readUTF());
        dis.close();
    }
}
