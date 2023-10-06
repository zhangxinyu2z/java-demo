package com.z2xinyu.syntax.operator;

/**
 * 三目运算符
 *
 * @author zxy
 */
public class TernaryOperator {
    public static void main(String[] args) {
        int a = 10, b = 20, c = 30;
        int max = (a > b) ? (a > c ? a : c) : (b > c ? b : c);
        System.out.println(max);
    }

    public static int getMax(int a, int b, int c) {
//        return (a > b) ? (a > c ? a : c) : (b > c ? b : c);
        int temp = a > b ? a : b;
        return temp > c ? temp : c;
    }
}
