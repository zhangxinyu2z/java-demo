package com.z2xinyu.syntax.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays工具类的应用
 *
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 21:41
 */
public class ArraysApply {

    /**
     * copy拷贝的一个新得数组，不会影响原数组
     * 还有System.arraycopy()方法
     */
    @Test
    public void copyOf() {
        char[] ch = {'a', 'b', 'c', 'c', 'd'};
        char[] copy = Arrays.copyOf(ch, 4);
        copy[0] = 'x';
        System.out.println(Arrays.toString(ch));
        System.out.println(Arrays.toString(copy));
    }

    /**
     * asList接收的参数是引用类型的
     * 假如传递一个基本类型的数组，是把该数组整体作为一个参数传递进去的
     */
    @Test
    public void asList() {
        List<Integer> list = Arrays.asList(1, 32, 12);

        int[] t = {1,31,131,1};
        int[] t1 = {1,31,131,1};
        List<int[]> ints = Arrays.asList(t,t1);
        System.out.println(ints.toString());

        Integer[] s = {142,14123,421};
        List<Integer> list1 = Arrays.asList(s);
    }

    @Test
    public void t() {
    }
}
