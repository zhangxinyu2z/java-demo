package com.z2xinyu.lambda.stream.api;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的生命周期有三个阶段：
 *
 * 起始生成阶段。
 *
 * 中间操作会逐一获取元素并进行处理。可有可无。所有中间操作都是惰性的，因此，流在管道中流动之前，任何操作都不会产生任何影响。
 *
 * 终端操作。通常分为 最终的消费 （foreach 之类的）和 归纳 （collect）两类。还有重要的一点就是终端操作启动了流在管道中的流动。
 * 只有终端操作执行后，中间操作才会执行。
 *
 * @author zhangxinyu
 * @since 2022/6/7 19:37
 */
public class StreamPeekApi {

    @Test
    public void tests() {
        // 没有输出
        Stream.of(10,11,12,13).peek(e-> System.out.println("Debug filtered value: " + e));
        // 调用终端输出后，得到peek的输出
        Stream.of(10,11,12,13).peek(e-> System.out.println("Debug filtered value: " + e)).collect(Collectors.toList());
    }

    @Test
    public void test() {
        List<Integer> collect = Stream.of(10, 11, 12, 13)
                .filter(n -> n % 2 == 0)
                .peek(e -> System.out.println("Debug filtered value: " + e))
                .map(n -> n * 10)
                .peek(e -> System.out.println("Debug mapped value: " + e))
                .collect(Collectors.toList());

    }

    @Test
    public void test2() {
        List<String> debugList = new ArrayList<>();
        List<String> names = Arrays.asList("Mahesh", "Suresh", "Mahendra");
        names.stream()
                .filter(el -> el.startsWith("M"))
                .peek(e -> debugList.add(e))
                .collect(Collectors.toList());
        System.out.println(debugList);
    }

    /**
     * peek并行流
     */
    @Test
    public void peekParallelStreamTest() {
        List<Integer> sortedList = Stream.of(15, 10, 17, 11)
                .parallel()
                .sorted()
                .peek(e -> System.out.println("Debug: " + e))
                .collect(Collectors.toList());

        System.out.println("---After sorting---");
        System.out.println(sortedList);
    }
}
