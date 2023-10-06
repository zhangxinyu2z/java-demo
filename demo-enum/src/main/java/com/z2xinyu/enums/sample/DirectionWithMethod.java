package com.z2xinyu.enums.sample;

/**
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public abstract class DirectionWithMethod {
    public static final DirectionWithMethod FRONT = new DirectionWithMethod("前") {

        @Override
        public void print() {
            System.out.println("front");
        }
    };

    public static final DirectionWithMethod BEHIND = new DirectionWithMethod("后") {

        @Override
        public void print() {
            System.out.println("behind");
        }
    };
    public static final DirectionWithMethod LEFT = new DirectionWithMethod("左") {

        @Override
        public void print() {
            System.out.println("left");
        }
    };
    public static final DirectionWithMethod RIGHT = new DirectionWithMethod("右") {

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

    /**
     * 提供一个抽象方法
     */
    public abstract void print();
}
