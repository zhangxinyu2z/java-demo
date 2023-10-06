package com.z2xinyu.keyword._final;

/**
 * 初始化顺序：
 * 1、静态成员变量先初始化
 * 2、静态代码块初始化
 * 3、成员变量默认初始化
 * 4、成员变量显示初始化
 * 5、代码块
 * 6、构造方法
 * <p>
 * final的两种初始化方式，对于变量的存储区域是没有任何影响
 * JVM规范中，类的静态变量存储在方法区，实例变量存储在堆区。也就是说static关键字才对变量的存储区域造成影响
 * final关键字来修饰变量表明了该变量一旦赋值就无法更改。
 * 同时编译器必须保证该变量在使用前被初始化赋值。
 */

/**
 * 总结：final修饰的基本类型变量，不能重新分配值
 * @author zxy
 */
public class InitializationProcess {

    /**
     * a是这个类的一个实例，类会在实例化的时候，将这个变量存储在堆区，无论是否有final修饰，
     * 而10是一个字面量，存放在常量池中（从常量池中取出10，赋给a）
     */
    final int a = 10;


    static final int d = 30;
    /**
     * 成员变量默认先初始化为0,申明final没有问题，但是要使用，就必须要赋值
     * <p>
     * final修饰的基本数据类型要么定义初始化，要么代码块中初始化，不可重新赋值
     */
    final int b;
    //    b = 10;
    /**
     *
     */
    static final int c;
    int e = 14;

    // 代码块
    {
        b = 10;

        System.out.println(a + b + e);
    }

    // 静态代码块
    static {
        c = 20;
    }

    public InitializationProcess() {
//        b = 10; // final修饰的变量不能在构造方法中初始化
        e = 10;
    }

    public static void main(String[] args) {
        new InitializationProcess();
    }

    public void test() {
        String s1 = "abc";
        String s2 = "abc";
        // s1==s2 它们都指向了常量池中的abc（同一个地址）,但是是s1和s2是分配在方法栈的局部变量的两个不同变量，
    }
}

/**

 */