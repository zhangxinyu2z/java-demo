package com.z2xinyu.keyword._final;

/**
 * final修饰引用类型变量，地址值不能发生改变
 * <p>
 * final可以修饰类，该类不能被继承。
 * final可以修饰方法，该方法不能被重写。如果不想让继承的方法被覆盖重写，可以用final修饰
 *
 * @author zxy
 */
public class ModifyReferenceVariable {
    static final String s1 = "hello";

    public static void s1(String s) {
        s = "world";
        System.out.println(s);
    }

    public static void main(String[] args) {
        final People p1 = new People("xinyu", 24);
//        p1 = new People();// 无法重新分配p1,但是可以修改对象的成员变量

    }
}

