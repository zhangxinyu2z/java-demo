package com.z2xinyu.io.tobe.demo06.PrintStream;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 打印流
 * @author zhang xinyu
 * @date 2021-04-14_12:37:58
 * @version v1.0
 */
public class Demo01PrintStream {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream("打印流.txt");

        /**
         * ps调用自己的功能，传入的数据不会改变 ps使用继承的功能，会去查编码表
         * 
         */
        ps.print(true);
        ps.println(97);
        ps.println('a');
        ps.write(97);
        /**
         * 把输出语句的目的地改为打印流的目的地
         */
        System.setOut(ps);
        System.out.println("我在文件中输出");

    }
}
