package com.z2xinyu.oop.initialization;

/**
 * 初始化执行顺序
 *
 * new Z():先执行默认初始化，super()对父类X进行初始化，x先默认初始化，再显示初始化，然后执行构造方法
 * 接着Z开始显示初始化，然后执行构造方法
 */
public class Z extends X {
    Y y = new Y();
    int a = 10;
    public Z() {
        super();
        System.out.println("z");
    }

    public static void main(String[] args) {
        new Z();
    }
}
class X {
    Y b = new Y();
    int z = 10;
    public X() {
        System.out.println("x");
    }
}

class Y {
    public Y() {
        System.out.println("y");
    }
}
// y  x y  z
