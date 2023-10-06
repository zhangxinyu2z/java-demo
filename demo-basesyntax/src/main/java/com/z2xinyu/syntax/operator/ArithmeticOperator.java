package com.z2xinyu.syntax.operator;

/**
 * 算数运算符：+ - * / ++ -- %
 * @author zxy
 */
public class ArithmeticOperator {
    public static void main(String[] args) {
        test1();
        test2();

        // 98hello
        String s = 'a' + 1 + "hello";
        System.out.println(s);
    }

    /**
     * 对++ --的测试
     */
    public static void test1() {
        int a = 10;
        int b = 10;
        int c = 10;
        a = b++;
        c = --a;
        b = ++a;
        a = c--;
//        a :9 b :10 c: 8
        System.out.println("a :" + a + " b :" + b + " c: " + c);
    }

    public static void test2() {
        int x = 4;
        // 4 + 6 + 60
        int y = (x++) + (++x) + (x * 10);
//        x : 6 y: 70
        System.out.println("x : " + x + " y: " + y);
    }
}
