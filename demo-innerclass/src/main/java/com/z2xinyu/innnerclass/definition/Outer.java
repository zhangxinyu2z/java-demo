package com.z2xinyu.innnerclass.definition;

import sun.java2d.pipe.SpanIterator;

import static java.lang.System.*;

/**
 * <p>
 * 成员内部类可以用private static修饰，局部内部类不可
 *
 * 成员/局部内部类，可以直接访问外部类的所有成员、方法，但是静态成员内部类，只能访问外部类的静态成员
 * <p>
 * 获取成员内部类对象的格式
 * 1. 在本类中
 *  1. 在方法内部直接创建内部类对象（包括private）：InnerA a = new InnerA(); 方法内部也可以通过2的方式
 * 2. 在其它类中
 *  1. （非private)：Outer.Inner oi = new Outer().new Inner();  必须是public或同包下可以是protected
 *  2. 其他类可以调用外部类的方法来访问，在外部类的方法中创建内部类对象
 *  至于，子类中的访问，参考访问修饰符的权限
 *
 * 静态内部类只能访问静态成员，内部可以有非静态方法，同样只能访问静态成员
 * 获取静态内部类对象的方式：
 *      1. 一个内部类被静态修饰了，在本类中可以当作一个普通对象来使用，可以直接new该对象
 *      2. 在本类或其它类中：外部类.内部类 名 =  new 外部类.内部类()
 *      3. 通过类名的方式直接调用内部类的静态成员： 外部类.内部类.方法或成员变量。
 *
 * @author zxy
 */
public class Outer {
    protected int a = 12;

    /**
     * 私有成员变量，内部类可以直接访问
     */
    private int num = 12;

    /**
     * 静态成员变量
     */
    private static int num2 = 12;

    /**
     * 成员内部类
     */
    private class InnerA {
        public void show1() {
            out.println(num);
            out.println(num2);
            play();
            play2();
            Outer.play2();
        }
    }

    /**
     * 静态内部类的成员属性和成员方法，只能访问外部类的静态成员
     */
    private static class StaticInner {
        public void show() {
            //num = 13;
            //play();
            // ------------Non-static field 'num' cannot be referenced from a static context---------
            play2();
        }

        public static void show2() {
            // 静态方法不能访问非静态成员
             out.println(num2);
        }
    }

    /**
     * 局部内部类
     */
    public void local() {
        // 局部内部类
        class InnerB {
            public void show() {
                num = 12;
                play();
            }
        }
        InnerB innerB = new InnerB();
        innerB.show();

        //  局部内部类不能被private static修饰
        //private static final class InnerC{}
    }

    private void play() {
        out.println("outer: private play()");
    }

    private static void play2() {
        out.println("outer: private static play2()");
    }

    /**
     * 外部类访问内部类的第一种方式
     */
    public void show() {
        // 成员内部的方法可以创建内部类的对象，来访问内部类的属性和方法
        InnerA in = new InnerA();
        in.show1();
        // 调用静态内部类的静态成员方法
        StaticInner.show2();

    }

    public static void main(String[] args) {
        // 1、其他类，调用外部类的方法，在方法中创建内部类的对象调用其成员
        new Outer().show();

        // 2、外部类访问成员内部类
        InnerA in = new Outer().new InnerA();
        // 或者这样拆解也行
        Outer outer=  new Outer();
        in = outer.new InnerA();
        in.show1();
        // ---------------2的访问方式，在其他类中，只能访问public的内部类----------------------------------------
        // 3、外部类访问静态内部类
        StaticInner inner1 = new StaticInner();
        StaticInner inner2 = new StaticInner();

        // Outer.StaticInner不可赋值
        StaticInner.show2();
    }
}


