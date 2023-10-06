package com.z2xinyu.io.wrapper;

import java.io.*;

/**
 * 简单的装饰器模式的应用
 * @author zhang xinyu
 * @date 2021-05-11 15:17:10
 * @version v1.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
        decode();
    }
    
    public static void encode() throws IOException {
        MyInputStream myInputStream = new MyInputStream(new FileInputStream("C:\\Users\\zhang xinyu\\Pictures\\刘亦菲.jpg"), 4);
        FileOutputStream fo = new FileOutputStream("C:\\Users\\zhang xinyu\\Desktop\\x.jpg");
        
        int len = 0;
       byte[] bytes= new byte[1024];
       while((len = myInputStream.read(bytes)) != -1) {
            fo.write(bytes, 0, len);
            fo.flush();
       }
       // 加秘成功
       myInputStream.close();
       fo.close();
       
    }
    
    /**
     * 解密
     * @throws IOException
     */
    public static void decode() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhang xinyu\\Desktop\\x.jpg");
        FileOutputStream fo = new FileOutputStream("C:\\Users\\zhang xinyu\\Desktop\\y.jpg");
        int i =0;
        while((i = fileInputStream.read()) !=-1) {
            fo.write(i-4);
            fo.flush();
        }
        
        fileInputStream.close();
        fo.close();
    }
}
