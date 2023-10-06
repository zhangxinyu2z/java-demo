package com.z2xinyu.io.cn.xy.io.nodeflow._character;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reader
 *  | - InputStreamReader
 *      | - FileReader
 *      FileReader没有扩展什么新的的功能
 */
public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        String path = "input.txt";
        FileReader fr = new FileReader(path);
        FileWriter fw=  new FileWriter("output.txt");
        char[] ch = new char[1024];
        int len = 0;
        while ((len = fr.read(ch))!= -1) {
//            System.out.print(new String(ch,0,len));
            fw.write(ch,0,len);
        }
        fr.close();
        fw.close();
    }
}
