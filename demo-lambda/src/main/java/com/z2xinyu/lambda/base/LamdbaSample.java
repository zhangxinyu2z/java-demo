package com.z2xinyu.lambda.base;

import org.junit.Test;

import java.util.*;

/**
 * lambda关注函数的主体
 * <p>
 * 1、参数的类型可以省略，多个参数必须同时省略（因为接口方法中的参数类型，lambda可以自动推断类型）
 * <p>
 * 2、如果参数只有1个，小括号可以省略
 * <p>
 * 3、如果代码块只有1条，可以省略大括号和分号，并且如果有return，也可以省略掉
 */
public class LamdbaSample {

    @Test
    public void test() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
/*
            compare方法如何决定是升序还是降序？
            如果a.compreTo(b)大于0，交换元素，小于0则不交换，即码值大的元素放在后面：升序
            反之，b.compareTo(a)>0,码值大的元素被放置到前：降序
            比较的是相邻的元素，索引是，j-1作为this，代表参数a，j作为other，代表参数b
            仅用List集合测试，使用Arrays.sort来排序，跟踪到归并排序算法，排序的前后取决于排序算法
         */
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

// 使用lambda
        names.sort((String a, String b) -> {
            return b.compareTo(a);
        });

//        函数体只有一行代码的，可以去掉大括号{}以及return关键字
        names.sort((a, b) -> a.compareTo(b));

// 替换为方法引用
        names.sort(String::compareTo);
    }

}
