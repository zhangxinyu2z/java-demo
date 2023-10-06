package com.z2xinyu.enums;

/**
 * 1. 枚举类都是enum的子类
 * 2. 构造方法默认是private
 * 3. 枚举可以用再switch中
 *
 *  * enum关键字用来表示该类是一个枚举类，继承自Enum类
 *  * 第一行也必须是第一行，都是该类的实例，看起来像是匿名对象一样的格式
 *  * 枚举类无法用“=“赋值，但可以通过方法间接的显示赋值
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public enum Direction {
    /**
     * 方向
     */
    FRONT("FRONT"),
    /**
     * 方向
     */
    BEHIND("BEHIND"),
    /**
     * 方向
     */
    LEFT("LEFT"),
    /**
     * 方向
     */
    RIGHT("RIGHT");

    private String name;

    private Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
