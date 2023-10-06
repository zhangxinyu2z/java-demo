package com.z2xinyu.enums;

/**
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public enum DirectionWithMethod {

    // 枚举类本身是一个抽象类，枚举实例是继承自枚举类，所以重写抽象方法
    /**
     *
     */
    FRONT("前") {
        @Override
        public void print() {
            System.err.println("front");
        }
    },
    /**
     *
     */
    BEHIND("后") {
        @Override
        public void print() {
            System.out.println("behind");
        }
    },
    /**
     *
     */
    LEFT("左") {
        @Override
        public void print() {
            System.out.println("left");
        }
    },
    /**
     *
     */
    RIGHT("右") {
        @Override
        public void print() {
            System.out.println("right");
        }
    };

    private String name;

    private DirectionWithMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void print();
}
