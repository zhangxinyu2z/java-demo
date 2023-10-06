package com.z2xinyu.oop.inherit;

import java.util.Vector;

/**
 * 继承中，private的理解
 *
 * @author zxy
 */
public class Super {
    /**
     * 私有集合容器
     */
    private Vector objects;

    /**
     * 在构造函数
     */
    public Super() {
        objects = new Vector();
    }

    /**
     * 公有函数,向动态数组成员objects添加字符串
     *
     * @param str
     */
    @SuppressWarnings("unchecked")
    public void addStr2Obs(String str) {
        // 如果子类没有私有objects对象,那addStr2Obs方法里面的objects又是什么?
        objects.add(str);
    }

    /**
     * 公有函数,打印objects中的诸元素
     */
    public void printAll() {
        for (int i = 0; i < objects.size(); i++) {
            System.out.println("序号=" + i + "\t元素=" + objects.get(i));
        }
    }
}