package com.z2xinyu.keyword.constant;


/**
 * 编译期常量：static final int A = 1024;
 * 编译时，所有A的引用都将被替换成字面量（即1024），类型必须是基本类型或String。
 * 运行时常量：static final int len = "Rhine".length();
 * 运行时才能确定它的值。
 * 编译期常量不依赖类，不会引起类的初始化；而运行时常量依赖类，会引起类的初始化。
 *
 * @author zxy
 */
public class NewTest {
    /**
     * 这个类测试的是：NewTest类编译后，cost变量会替换为price字面量，如果修改Book的price，
     * 只编译book类，而不重新编译NewTest,输出的结果不会发生修改。
     */
    public int cost = Book.price;

    public void get() {
        System.out.println(cost);
    }

    public static void main(String args[]) {
        (new NewTest()).get();
    }
}

