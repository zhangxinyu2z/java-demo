package com.z2xinyu.oop.inherit;

/**
 * 继承间方法的关系
 * - 子类可以继承父类的静态方法
 * - 子类不能继承父类的私有方法
 * - 同名的方法：子类中有这个方法，就执行子类的方法，没有就执行父类的方法，这称为方法重写（override)
 * - 可以在子类的构造方法和普通方法中使用super调用父类的同名方法，其他位置不行；使用this.方法调用本类的其他方法
 * <p>
 * 方法重写：用来实现子类特有的方法。
 * 1、子类不能重写父类的私有方法 :因为根本无法继承，也就无法访问，如果定义一个同名的方法，实际上并不是重写
 * 2、子类不存在重写父类静态方法这个概念（无法加@Override)，但是可以一模一样，必须是staitc，否则编译报错
 * 3、子类重写父类方法时，访问权限不能更低，否则无法编译通过
 *
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 23:58
 */
public class MethodOfInherit {
    public static void main(String[] args) {
        Fu2 fu2 = new Zi2();
        fu2.show3();
    }
}

class Fu2 {

    private void show4() {
        System.out.println("父类的私有方法");
    }

    public void show() {
        System.out.println("父类的show方法");
    }

    public void show2() {
        System.out.println("父类中的show2方法");
    }

    public static void show3() {
        System.out.println("父类的静态方法show3");
    }
}

class Zi2 extends Fu2 {

    /**
     * 定义一个同名方法，并不是重写
     */
    public void show4() {

    }

    public void show() {
        System.out.println("子类的show方法");
    }

    public static void show3() {
        System.out.println("zi show3()");
    }

}
