package com.z2xinyu.oop.inherit;

/**
继承间构造函数间的关系 ：
    1、因为子继父，可能会使用父类中的数据，所以要先完成父类数据的初始化，子类的所有构造方法都默认访问父类中的空参数构造方法
        子类所有构造方法第一条语句默认是super();

    2、如果父类设置了一个有参的构造方法，系统就不会自动提供无参的构造方法，需要手动使用super(...)传参初始化有参的父类构造方法

    3、this是用来调用同类中的其他构造方法：this(...)用于调用本类的其他构造方法

 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 23:58
 */
public class ContructorMetodOfInherit {
}


class Fu1 {
    /*
    public Fu1() {
        System.out.println("父类的构造方法");
    }
    */
    public Fu1(String name) {
        System.out.println("父类中的带参构造方法");
    }
}

class Zi1 extends Fu1 {
    public Zi1() {
        super("用super来调用父类中的有参构造方法"); // 隐藏
        System.out.println("子类的构造方法");
    }

    public Zi1(String name) {
        this(); // 调用本类的空参数构造方法,相当于间接的调用了父类的有参构造方法
        System.out.println("子类中的带参构造方法");
    }
}
