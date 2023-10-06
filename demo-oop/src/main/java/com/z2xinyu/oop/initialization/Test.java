package com.z2xinyu.oop.initialization;

/**
 * 静态代码块Fu
 * 静态代码块zi
 * 构造代码块Fu
 * 构造方法Fu
 * 构造代码块zi
 * 构造方法zi
 * <p>
 * zi和fu的字节码文件被加载器引入，先执行fu的静态代码块，在执行zi的静态代码块，
 * 然后开始new，先执行fu的构造代码块，再执行构造函数，接着执行zi的构造代码块和构造函数。
 */

class Fu {
    static {
        System.out.println("静态代码块Fu");
    }

    {
        System.out.println("构造代码块Fu");
    }

    public Fu() {
        System.out.println("构造方法Fu");
    }
}

class Zi extends Fu {
    static {
        System.out.println("静态代码块zi");
    }

    {
        System.out.println("构造代码块zi");
    }

    public Zi() {
        System.out.println("构造方法zi");
    }
}

public class Test {
    public static void main(String[] args) {
        Zi zi = new Zi();
    }
}