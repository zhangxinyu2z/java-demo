package com.z2xinyu.io.encode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 为什么复制文件的时候不会出现拼写字节的情况呢？
 *   计算机是如何识别什么时候把两个字节转换为一个中文呢？
 */

/**
 * 对于汉字文件进行读写进文件是没有问题的，但是输出到控制台中就会出现都不懂的情况
 * 字节流操作汉字不方便，提供了转换流
 * 字符流= 字节流+编码表
 *
 * 编码表：现实世界的字符和对应的数值组成的一张表
 * ASCII表：最高位表示符号位，（1个字节的7个位）其余位数值位
 * ISO-8859-1:拉丁码表  8位表示一个数据
 * gb2312:简体中文
 * gbk:
 * gb18030（替代gbk）
 * Unicode:国际标准码  2个字节 表示一个
 * UTF-8:最多用3个字节表示一个字符（该编码尽量能少用字节就少用）
 */
public class ChineseEncode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "我";
        System.out.println(Arrays.toString(str.getBytes("utf-8"))); // [-26, -120, -111]

        System.out.print(Arrays.toString(str.getBytes("gbk"))); // [-50, -46]
        /**
         * 计算机存储汉字,第一个一定是复数
         */
    }
}
