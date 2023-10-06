package com.z2xinyu.string.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * String的每次拼接都会产生新的对象，还会分配新的空间，造成浪费，提供了StringBuffer
 * <p>
 * StringBuffer: 线程安全的可变字符序列
 *
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public class StringBufferTest {

    @Test
    public void testBase() {
        StringBuffer sb = new StringBuffer();
        // 初始容量16
        Assert.assertEquals(sb.capacity(), 16);
    }

    /**
     * StringBuffer append(boolean b)   [char double float int long String StringBuffer]
     * StringBuffer append(Object obj)
     * StringBuffer append(char[] str)
     * StringBuffer append(char[] str, int offset, int len)
     * StringBuffer append(CharSequence s)
     * StringBuffer append(CharSequence s, int start, int end)
     * StringBuffer appendCodePoint(int codePoint)
     */
    @Test
    public void testAppend() {
        StringBuilder sb = new StringBuilder();
        sb.append('a').append(1).append(12L).append("hello ").append("world").append(new Object());
        System.out.println(sb);
        sb.append(new char[] {'a', 'b', 'c', 'd', 'e', 'f'});
        // 98 -> b
        sb.appendCodePoint(98);
        System.out.println(sb);
    }

    /**
     * int capacity()
     * char charAt(int index)
     * int codePointAt(int index)
     * int codePointBefore(int index)  指定索引前一个的字符
     * int codePointCount(int beginIndex, int endIndex)
     * void getChars(int srcBegin, int srcEnd, char[] dst, int offset) 字符从该序列复制到目标字符数组 dst 。
     * <p>
     * int indexOf(String str) 返回指定子字符串第一次出现的字符串内的索引。
     * <p>
     * int indexOf(String str, int fromIndex) 返回指定子串的第一次出现的字符串中的索引，从指定的索引开始。
     */
    @Test
    public void get() {
        StringBuilder sb = new StringBuilder("woiw laflkdsa");
        Assert.assertEquals(119, 'w');
        Assert.assertEquals(119, sb.charAt(0));

        char[] ch = new char[24];
        sb.getChars(1, 6, ch, 2);
        System.out.println(ch);
    }

    /**
     * StringBuffer delete(int start, int end)
     * StringBuffer deleteCharAt(int index)
     */
    @Test
    public  void testDelete() {
        StringBuffer sb = new StringBuffer("abcdefghijklmnopkrstuvwxyz");
        Assert.assertEquals("acdefghijklmnopkrstuvwxyz", sb.deleteCharAt(1).toString());
        Assert.assertEquals("abmnopkrstuvwxyz", sb.deleteCharAt(1).toString());
        sb = new StringBuffer("abcdefghijklmnopkrstuvwxyz");
        Assert.assertEquals("abmnopkrstuvwxyz", sb.delete(2, 12).toString());
    }

    /*
    改：
    StringBuffer insert(int offset, char c)
    StringBuffer replace(int start, int end, String str)
    StringBuffer reverse()
    void setCharAt(int index, char ch)
    void setLength(int newLength)  和容量没有关系
    CharSequence subSequence(int start ,int end)
    String subString(int start)
    String subString(int start, int end)
    void trimToSize()
     */
    @Test
    public  void testModify() {
        StringBuilder sb = new StringBuilder("hijklmn");
        sb.insert(2, "hello", 1, 2);
        Assert.assertEquals("hiejklmn", sb.toString());
        Assert.assertEquals(6, sb.offsetByCodePoints(1, 5));
        // index 2插入12
        Assert.assertEquals("hi12ejklmn",    sb.insert(2, 12).toString());
        Assert.assertEquals("hi1bc12ejklmn",  sb.insert(2, new char[] {'1', 'b', 'c'}).toString());
        Assert.assertEquals("hibc1bc12ejklmn", sb.insert(2, new char[] {'a', 'b', 'c', 'd'}, 1, 2).toString());
        Assert.assertEquals("ooo1bc12ejklmn",   sb.replace(0, 4, "ooo").toString());
        Assert.assertEquals("nmlkje21cb1ooo", sb.reverse().toString());

        sb.setCharAt(1, 'x');
        Assert.assertEquals("nxlkje21cb1ooo",  sb.toString());

        Assert.assertEquals("lkje21cb1ooo",  sb.substring(2));

        StringBuilder sb2 = new StringBuilder("dj dkis djsio, dds");
        sb2.trimToSize();
        System.out.println(sb2);

    }

}
