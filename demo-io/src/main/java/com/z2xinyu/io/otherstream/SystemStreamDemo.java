package com.z2xinyu.io.otherstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 键盘录入的方式:
 * 一：main()接收：`java SystemStreamDemo hello world`
 * 二：JDK1.5后 `new Scanner(System.in);`
 * 三：使用字符缓冲流包装标准输入字节流实现
 */
public class SystemStreamDemo {
    public static void main(String[] args) throws IOException {
//        System.out.print(Arrays.toString(args));

//        Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(str);
    }
}
