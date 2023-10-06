package com.z2xinyu.io.cn.xy.io.processflow._character.convertflow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Reader
 *  | - InputStreamReader
 *      | - FileReader
 * 转换流，字节流到字符流的桥接器
 * 把字节流读取的字节进行缓冲而后在通过字符集解码成字符返回
 * @author z-xy
 */
public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        String path = "output.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
        /*int c = 0;
        while((c = isr.read())!= -1) {
            System.out.print((char)c);
        }*/
        BufferedReader br = new BufferedReader(isr);

        BufferedWriter bw= new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("input.txt")));

        String s=  null;
        while((s = br.readLine()) !=null) {
            // 输出到控制台，注意换行
//            System.out.println(s);
            // 写入到新的文件，注意换行
            bw.write(s);
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
