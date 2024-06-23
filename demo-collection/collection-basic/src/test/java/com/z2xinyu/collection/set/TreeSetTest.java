package com.z2xinyu.collection.set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * TreeSet是会对元素进行排序，且元素不会重复
 * 实现方式：
 * 方式一：添加的元素自身具备比较性，实现Comparable接口，重写compareTo方法，TreeSet底层用的是TreeMap的put方法，这个方法中会把对象向上转成Comparable类型,
 * 所以add的对象必须实现Comparable接口
 * 方式二：TreeSet具备比较性，传入一个Comparator比较器，重写compare方法
 *
 * @author zhangxinyu
 * @date 2023/7/10
 **/
public class TreeSetTest {

    @Test
    public void add() {
        TreeSet ts = new TreeSet();

        ts.add("what");
        ts.add("absou");
        ts.add("hello");
        ts.add("happy");
        // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer > 底层调用compareTo方法进行比较
        System.out.println("ts: " + ts);
    }

    @Test
    public void addAll() {
        TreeSet<String> ts = new TreeSet<>();

        List<String> lt = new ArrayList<>();
        lt.add("hello1");
        lt.add("happy");

        ts.addAll(lt);
    }

    @Test
    public void get() {
        TreeSet<String> ts = init();

        String first = ts.first();
        String last = ts.last();

        // 集合中小于或等于给定的元素的最大元素
        String ikd = ts.floor("ikd");

        Object higher = ts.higher("happy");
        Object lower = ts.lower("h");

    }

    @Test
    public void remove() {
        TreeSet<String> ts = new TreeSet<>();

        Object first = ts.pollFirst();
        System.out.println("first:" + first);
        System.out.println("ts:" + ts);
        Object last = ts.pollLast();
        System.out.println("last:" + last);
        System.out.println("ts:" + ts);

        boolean remove = ts.remove(new String("hello"));
        System.out.println("remove :" + remove);
    }

    public TreeSet<String> init() {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("hello1");
        ts.add("helll2");
        ts.add("happy");
        return ts;
    }

    /*
      TreeSet底层用的是TreeMap的put方法，这个方法中会把对象向上转成Compareable类型,
      所以添加的对象必须实现Compareable接口 才能排序
     */
}
