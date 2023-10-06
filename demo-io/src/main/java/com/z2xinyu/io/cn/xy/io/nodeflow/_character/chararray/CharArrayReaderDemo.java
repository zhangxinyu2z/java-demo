package com.z2xinyu.io.cn.xy.io.nodeflow._character.chararray;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * 用来操作字符的数据的节点流
 * <p>
 * CharArrayReader也是内部存在一个存储数据的缓存区 char[]
 *
 * @author z-xy
 */
public class CharArrayReaderDemo {
    public static void main(String[] args) throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        // 追加到Writer中
        caw.append("Hello");
        caw.append('a');
        caw.append('1');
        caw.write(98);

        char[] chars = caw.toCharArray();

        CharArrayReader car = new CharArrayReader(chars);
        int b = 0;
        while ((b = car.read()) != -1) {
            System.out.print((char) b);
        }
    }
}
