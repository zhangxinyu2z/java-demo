package com.z2xinyu.io.cn.xy.io.nodeflow._character.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * 用来操作字符串的内存流
 * <p>
 * StringReader和StringWriter是使用StringBuffer作为数据存放的缓存区
 *
 * @author z-xy
 */
public class StringReaderDemo {
    public static void main(String[] args) throws IOException {
        StringWriter sw = new StringWriter();
        sw.append("Hello");
        sw.write(new char[]{'b', 'c', 'd'});

        // 返回sw的缓冲区
        String buffer = sw.getBuffer().toString();

        StringReader sr = new StringReader(buffer);
        // 读取方式，读完
//        int ch = 0;
//        while ((ch = sr.read()) != -1) {
//            System.out.print((char) ch);
//        }

        // StringReader继承自Reader,可以使用缓冲流来读取
        BufferedReader br = new BufferedReader(sr);
        String str = null;
        while ((str = br.readLine()) != null) {
            System.out.print(str);
        }
    }
}
