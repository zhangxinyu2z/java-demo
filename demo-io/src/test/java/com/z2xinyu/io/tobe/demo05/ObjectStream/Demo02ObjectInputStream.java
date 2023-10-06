package com.z2xinyu.io.tobe.demo05.ObjectStream;

import java.io.*;

/**
 * 解序列化
 * 
 * @author zhang xinyu
 * @date 2021-04-14_12:01:35
 * @version v1.0
 */
public class Demo02ObjectInputStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Student.txt"));
        Object student = ois.readObject();
        System.out.println(student);
        ois.close();
    }

}
