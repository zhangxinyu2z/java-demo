package com.z2xinyu.syntax.method;

/**
 * 方法中的参数传递：都是值传递
 * 基本类型：形参的改变对实参没有影响
 * 引用类型：参数传递的是地址值，对象的本身内容是有影响的
 *
 * @author zxy
 */
public class ParameterPassing {
    public static void main(String[] args) {
        // 基本类型作为参数传递
        int a = 20;
        int b = 10;
        change(a, b);
        // 20 10
        System.out.println("调用change()后：a = " + a + " b =" + b);
    }

    public static void change(int a, int b) { // 形参接收的是一个值的拷贝，不会影响原值
        a = 40;
        b = 30;
        System.out.println("change()方法中：a = " + a + " b =" + b);
    }
}
