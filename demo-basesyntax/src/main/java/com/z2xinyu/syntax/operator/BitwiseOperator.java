package com.z2xinyu.syntax.operator;

/**
 * 位运算符：& | ~ ^ >> << >>>
 *
 * @author zxy
 */
public class BitwiseOperator {
    public static void main(String[] args) {

    }

    /**
     * 两个变量交换的方式
     */
    public static void demo() {
        int a = 1;
        int b = 2;

        // 1、第三方变量
        int c = a;
        a = b;
        b = c;

        // 2、变量相加
        a = a + b;
        b = a - b;
        a = a - b;

        // 3、亦或
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        // 4
        b = (a + b) - (a = b);
    }
}
