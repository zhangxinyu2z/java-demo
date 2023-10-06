package com.z2xinyu.io.tobe.demo05.ObjectStream;

import java.io.*;

/**
 * 序列化流
 * 
 * @author zhang xinyu
 * @date 2021-04-14_11:55:31
 * @version v1.0
 */
public class Demo01ObjectOuputStream {

    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Student.txt"));
        oos.writeObject(new Student("刘亦菲", 25));
        oos.close();
    }

}
