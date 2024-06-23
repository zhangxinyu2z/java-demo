package com.z2xinyu.lambda.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxinyu
 * @date 2024/5/27
 **/
public class ConstructorRefTest {

    @Test
    public void demo01() {
        List<Integer> labels = Arrays.asList(1,2,3,4);
        String[] strings = labels.stream().toArray(String[]::new);
    }

    @Test
    public void demo02() {
        List<String> strings = this.asList(ArrayList::new, "aaa", "bbb", "ccc");
        System.out.println(strings);
    }

    public  <T> List<T> asList(MyCreator<List<T>> creator,T... a){
        List<T> list =  creator.create();
        for (T t : a) {
            list.add(t);
        }
        return list;
    }

    interface MyCreator<T extends List<?>>{
        T create();
    }
}
