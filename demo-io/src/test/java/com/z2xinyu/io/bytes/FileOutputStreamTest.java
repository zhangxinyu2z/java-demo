package com.z2xinyu.io.bytes;

import org.junit.Test;

import java.io.*;

/**
 * @author zhangxinyu
 * @date 2023/8/28
 **/
public class FileOutputStreamTest {

    @Test
    public void testWrite() {
        String fileName = "File.txt";
        // try中的变量只在try中有效，为了finally中能访问fos变量
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            // fos.write(97);
            // fos.write(98);
            byte[] b = {97, 98, 99}; // 计算机底层是二进制，记事本会根据编码区查找对应的字符

            fos.write(b); // 1次写1个
            //            fos.write(b,0,b.length);  // 1次写1个数组


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) { // 如果fos出现异常未完成初始化，会出现空指针异常
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
