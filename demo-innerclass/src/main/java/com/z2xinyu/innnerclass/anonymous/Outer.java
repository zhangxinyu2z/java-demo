package com.z2xinyu.innnerclass.anonymous;

/**
 * 匿名内部类的用法：可以认为类的实现类。
 *
 * @author zxy
 * @version v1.0
 * @date created in 2021-12-07 20:58
 */
public class Outer {
    public static void main(String[] args) {
        // 匿名内部类
        new Animal() {
            private int age;

            // 匿名内部类没有构造函数
//            public Animal() {
//            }
            // 通过代码块模拟构造函数初始化
            {
                this.age = 12;
                super.weight = 20;
            }

            @Override
            public void eat() {
                System.out.println("eat bouns");
            }
        };

        // 实体类也可以
        Dog dog = new Dog() {
            @Override
            public void eat() {
                System.out.println("eat bouns");
            }
        };

        // 接口也可以
        new Jump() {
            @Override
            public void jump() {
                System.out.println("ju,mp");
            }
        };

    }
}
