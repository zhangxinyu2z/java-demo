package com.z2xinyu.io;

import org.junit.Test;

import java.io.*;

/**
 * @author zhangxinyu
 * @date 2023/8/28
 **/
public class PrintWriterTest {

    @Test
    public void testWrite() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("少司命.txt"));
        PrintStream ps = new PrintStream(new FileOutputStream("少司命-copy.txt"),true);

        int i = 0;
        byte[] b = new byte[1024];
        while ((i = bis.read(b)) != -1) {
            ps.println(new String(b, 0, i));
        }

        // ps.close();
    }
}
