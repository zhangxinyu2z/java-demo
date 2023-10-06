package com.z2xinyu.oop.inherit;

/**
 继承之间变量的关系
 - 访问子父类间的同名变量
    查找的范围：子类局部变量 > 子类的成员范围 > 父类的成员范围 > 还没有就报错

- 同名变量的就近原则，如果想访问同名的成员变量怎么办？
 this代表本类对应的引用。super代表父类存储空间的标识(可以理解为父类引用,可以操作父类的成员)
    + this.num;
    + super.num; // 访问父类的同名变量
 - 子类可以继承父类的所有成员属性，只是private不可见，持有父类的引用访问的是父类的成员属性，
    父类不可能访问本类中不存在的子类中的属性,对于同名变量的问题不要产生误解。

 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 23:58
 */
public class VariableOfInherit {
    public static void main(String[] args) {
        Zi zi = new Zi();
        zi.show(12); // 21 13 14
        System.out.println(zi.num); // 13
        System.out.println("------------------");
        Fu fu = new Zi();
        System.out.println(fu.num); // 14
    }
}

class Fu {
    int num = 14;
    int num2 = 15;
}

class Zi extends Fu {
    int num = 13;

    public void show(int num) {
        num = 21;
        System.out.println("num=" + num);
        System.out.println("this.num="+ this.num);
        System.out.println("super.num="+super.num);
        System.out.println("num2="+ num2);
    }

}
