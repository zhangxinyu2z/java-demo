package com.z2xinyu.io.bytes;

import java.io.*;

/**
 * @author zhangxinyu
 * @date 2023/8/28
 **/
public class ByteArrayStreamTest {

    /*把一个文件转成字节数组的实现
     *
     */
    public void fun6() throws IOException {
        String path = "C:\\Users\\zhang xinyu\\Pictures\\刘亦菲.jpg";
        FileInputStream fis = new FileInputStream(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while((len = fis.read(bytes))!=-1) {
            bos.write(bytes, 0, len);
        }
        byte[] byteArray = bos.toByteArray();
        System.out.println(byteArray.length);
        fis.close();
    }

    public void fun1() throws IOException {
        // ByteArrayOutputStream: 可以捕获内存缓冲区的数据，转换成字节数组。
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bout.write(12);
        byte[] bytes = "helloword".getBytes();
        bout.write(bytes, 0, bytes.length);
        byte[] buf = bout.toByteArray();// 获取内存缓冲中的数据
        for (int i = 0; i < buf.length; i++) {
            System.out.println((char)buf[i]);
        }
        // 关闭这个流没什么意思，因为并没有绑定文件
    }

    public void fun3() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new ByteArrayOutputStream());
        dataOutputStream.writeChars("helloworld");

        String s = "31421daf";
        new DataInputStream(new ByteArrayInputStream(s.getBytes()));

    }

    public void fun4() throws IOException {
        String file = "C:\\Users\\zhang xinyu\\Desktop\\测试.txt";
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        /*        // 写
        dataOutputStream.writeBytes("北京"); // 2字节，看源码去
        // 读出来
        byte[] bytes = new byte[2];
        dis.read(bytes); // 数据丢肯定读不出来
        // 这种写法不可取，可以用 dos.write(String.getBytes("指定编码"));
        System.out.println(new String(bytes, 0, bytes.length));*/

        /*      dataOutputStream.writeChars("北京"); // 任意字符都用2个自己表示一个字符
        System.out.println(dis.readChar()); // 读取没问题
        */

        dataOutputStream.writeUTF("北京"); // 8字节，前面2字节表示长度
        System.out.println(dis.readUTF()); // 读取问题

    }

    public void fun2() {
        // ByteArrayInputStream 将字节数组读入到输入流中
        String s = "helloworld";
        byte[] bytes = s.getBytes();
        ByteArrayInputStream inputByteArray = new ByteArrayInputStream(bytes);
        int i = 0;
        while ((i = inputByteArray.read()) != -1) {
            System.out.println((char)i);
        }
    }
}
