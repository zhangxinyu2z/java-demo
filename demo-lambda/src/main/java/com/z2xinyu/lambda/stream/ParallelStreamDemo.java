package com.z2xinyu.lambda.stream;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhangxinyu
 * @since 2022/6/7 19:07
 */
public class ParallelStreamDemo {
    public static void main(String[] args) {
        IntStream list = IntStream.range(0, 10);
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        //开始并行执行
        list.parallel().forEach(i -> {
            Thread thread = Thread.currentThread();
            System.err.println("integer：" + i + "，" + "currentThread:" + thread.getName());
            threadSet.add(thread);
        });
        System.out.println("all threads：" + threadSet.stream().map(Thread::getName).collect(Collectors.joining(":")));
    }

}
