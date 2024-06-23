package com.z2xinyu.lambda.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhangxinyu
 * @date 2024/5/27
 **/
public class StreamFeatureTest {

    @Test
    public void demo01() {
        //对于stream的聚合、消费或收集操作只能进行一次，再次操作会报错
        Stream<String> stream = Stream.generate(() -> "user").limit(20);
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    @Test
    public void demo02() {
        //在聚合操作执行前修改数据源是允许的,否则无法定义流操作的输出
        List<String> wordList = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
                add("g");
            }
        };
        Stream<String> words1 = wordList.stream();
        words1.forEach(s -> {
            System.out.println("s->"+s);
            if (s.length() < 4) {
                System.out.println("select->"+s);
                wordList.remove(s);
                System.out.println(wordList);
            }
        });
    }
}
