package com.z2xinyu.io.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 采用内存映射文件的方式，将文件或文件的一段区域映射到内存中
 */
public class NioDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("output.txt");

        // 将文件中的所有字节复制到输出流。
        // Paths.get(Uri uri) 返回表示该路径的Path对象
        long counts = Files.copy(Paths.get("input.txt"), fos);
        // JDK11
//        long counts = Files.copy(Path.of("input.txt"),fos);

        fos.write(68);
        System.out.print(counts); // 写入的字节数。

        List al = new ArrayList();
        al.add("yi fei");
        al.add("yi fei");
        // 将文本写入文件utf-8编码， 文件不存在会自动创建
        Files.write(Paths.get("array.txt"), al
                , Charset.forName("utf-8"));


    }
}
