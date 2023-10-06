package com.z2xinyu.string.base;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * String 不可变字符串
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public class StringStructureTest {

    @Test
    public void testConstructorMethod() {
        // 1、空的字符序列 new String();
        String s = "";

        // 2、默认字符集解码制定的字节数组
        byte[] bytes = new byte[] {2, 31, 41, 12, -12};
        String s2 = new String(bytes, StandardCharsets.ISO_8859_1);
        System.out.println(Arrays.toString(s2.getBytes(StandardCharsets.ISO_8859_1)));

        // 3、默认字符集解码制定的字节子列
        byte[] bytes2 = new byte[] {1, -2, 13, 4, 5, 5};
        String s3 = new String(bytes2, 1, 4); // length 2

        // 4、指定字符集解码
        String s4 = new String(bytes2, 1, 2, StandardCharsets.ISO_8859_1);
        System.out.println(Arrays.toString(s4.getBytes(StandardCharsets.ISO_8859_1)));

        // 6、String?(char[] value)
        // String?(char[] value, int offset, int count)
        String s6 = new String(new char[] {'1', 'b', 'c', '2'});
        System.out.println(s6);
        String s7 = new String(new char[] {'1', '2', 's', 'a'}, 1, 2);
        System.out.println(s7);

    }
}
