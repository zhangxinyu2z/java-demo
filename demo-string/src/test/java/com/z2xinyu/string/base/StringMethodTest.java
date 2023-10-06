package com.z2xinyu.string.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public class StringMethodTest {

    /**
     * 字符串比较：equals 比较的是两个字符串的字符序列,==比较的是字符串对象在内存中的地址值
     */
    @Test
    public void testEquals() {
        String s1 = "helloworld";
        String s2 = "hello" + "world";
        String s21 = new String("helloworld");
        String s3 = s1;
        String s4 = "hello";
        String s5 = "world";
        String s6 = s4 + s5;
        String s7 = s1.substring(0, 5);
        String s8 = new String("hello");
        String s9 = "hello";

        assert(s1.equals(s3));
        assert(s1.equals(s2));

        assert(s1.equals(s6));
        assert(s1.equals("helloworld"));

        assert(s4.equals(s7));
        assert(s4.equals(s8));
        assert(s4.equals(s9));

        assert(s1 != s21);
        assert(s2 != s21);

        assert(s2 != s6);
        assert(s2 != s6);

        assert(s4 != s8);
        assert(s4 == s9);

    }

    /**
     * 1、只有字符串常量是共享的，存放在String池中
     * <br>
     * 2、变量拼接，先为这两个变量开辟空间，拼接后产生一个新的地址；
     * <b2>
     * 3、两个常量拼接，先在常量池中查找，没有找到才会创建一个新的字符串，分配一个新地址
     */
    @Test
    public void testEq() {
        String s2 = "Hello";
        String s3 = "World";
        // s2 = s2 + "World"; 这里开辟了两个空间 常量池中没有World，开辟了新的空间

        // 创建了两个对象
        String s5 = new String("HelloWorld");
    }

    /**
     * 基本数据类型和String的转换方式
     */
    @Test
    public void testConvertToString() {
        // byte[] to String, 不能用valueOf
        String s1 = "ABCD";
        byte[] b1 = { 65, 66, 67, 68 };
        assert(s1.equals(new String(b1)));
        assert("BC".equals(new String(b1, 1, 2)));
        // String to bytes
        Assert.assertArrayEquals(b1, s1.getBytes());

        // char[] to String
        char[] c3 = {'a', 'b', 'c', 'd'};
        assert("abcd".equals(new String(c3)));
        assert("abcd".equals(new String(c3, 0, 4)));
        assert("abcd".equals(String.valueOf(c3)));
        // String to char[]
        String s4 = "String to char[]";
        Assert.assertArrayEquals(c3, s4.toCharArray());

        // 基本类型转String valueOf

        // 7 toUpperCase toLowerCase
        // 8 concat
    }

    /**
     * compareTo()方法测试
     */
    @Test
    public  void testCompareTo() {
        String s1 = "hello";
        String s2 = "hello world";
        String s3 = "hallo";
        // s1的字符和s2的前缀部分相同，最后返回的是(s1-s2)长度的数字
        Assert.assertEquals(s1.compareTo(s2), s1.length() - s2.length());
        // 比较s1的第二个字符和s2的第二个字符
        Assert.assertEquals(s1.compareTo(s3), s1.charAt(1) - s3.charAt(1));
    }

    @Test
    public void testSubstring() {
        String s = "helloworld";
        Assert.assertEquals("lloworld", s.substring(2));
        // 返回从beginIndex到endIndex-1的字串
        Assert.assertEquals("llow", s.substring(2, 6));
    }

    @Test
    public void tesCodePoint() {
        char c = 'a';
        int unicode = (int) c;
        System.out.println("Character: " + c);
        // Unicode值是97
        System.out.println("Unicode value: " + unicode);
        // 十六进制表示为61
        System.out.println("Unicode value (hex): " + Integer.toHexString(unicode));
        // Unicode格式
        System.out.println("Unicode format: " + "\\u" + Integer.toHexString(unicode));

        int unicodeValue = '\u0061';
        char character = (char) unicodeValue;
        System.out.println(character);
    }
}
