package com.z2xinyu.keyword.constant;

/**
 * 常量什么时候放入常量池？？
 * 常量池：
 * 方法中的运行时常量池：可以在运行时添加常量，jdk1.7之后，运行常量池位于堆中。
 * class文件中的常量池：编绎时确定，符号引用和字面量（文本字符串，被申明为final的变量的值）
 *
 * @author zxy
 */
public class Demo {
    public static void main(String[] args) {

        // 访问类中的常量，不一定会加载类，static代码块没有执行
        System.out.println(Deal.x);
//        只有访问了类中的常量时，该常量才会在编译时被放入对应类class文件的常量池，否则不会被放入

        // intern的作用：
        // 1、s的字符串在常量池中，返回s的引用
        // 2、若m的字符串值不在常量池中（在堆内存中），但常量池中含有该字符串值，则返回常量池中该字符串值的引用。
        // 3、若m的字符串值不在常量池中（在堆内存中），且常量池中不含有该字符串值，则在常量池中创建一个与m的字符序列相同的字符串值，然后返回新创建字符串值的引用。

//        String s= new String("1") + new String("2");
        String s = "12";
        String m = s.intern();
    }
}

class Deal {
    static {
        System.out.println("加载了deal类");
    }

    static final int x = 10000000;
}

