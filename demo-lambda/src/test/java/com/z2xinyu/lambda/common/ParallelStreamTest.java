package com.z2xinyu.lambda.common;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangxinyu
 * @date 2024/6/4
 **/
public class ParallelStreamTest {

    /**
     * peek，监控方法,可用来窥看流内的数据
     * 串行流的执行顺序
     */
    @Test
    public void testPeek() {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1).limit(10);
        stream.peek(this::peek1).filter(x -> x > 5).peek(this::peek2).filter(x -> x < 8).peek(this::peek3)
            .forEach(System.out::println);
    }

    /**
     * 并行流的执行顺序
     * <br>
     * 将stream.filter(x -> x > 5).filter(x -> x < 8).forEach(System.out::println)
     * 的过程想象成图(串行流和并行流的执行效果.png)的管道，我们在管道上加入的peek相当于一个阀门，透过这个阀门查看流经的数据，
     * 1）当使用顺序流时，数据按照源数据的顺序依次通过管道，当一个数据被filter过滤，或者经过整个管道而输出后，第二个数据才会开始重复这一过程
     * 2）当使用并行流时，系统除了主线程外启动了n个线程来执行处理任务，因此执行是无序的，但同一个线程内处理的数据是按顺序进行的。
     */
    @Test
    public void testPeekPal() {
        Stream<Integer> stream = Stream.iterate(1, x -> x + 1).limit(10).parallel();
        stream.peek(this::peek1).filter(x -> x > 5).peek(this::peek2).filter(x -> x < 8).peek(this::peek3)
            .forEach(System.out::println);
    }

    public void peek1(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek1->" + x);
    }

    public void peek2(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek2->" + x);
    }

    public void peek3(int x) {
        System.out.println(Thread.currentThread().getName() + ":->final result->" + x);
    }

    /**
     * tip
     *
     * 1.并行流和排序是不冲突的，
     * 2.一个流是否是有序的，对于一些api可能会提高执行效率，对于另一些api可能会降低执行效率
     * 3.如果想要输出的结果是有序的，对于并行的流需要使用forEachOrdered(forEach的输出效率更高)
     *
     * 对于串行流.distinct().sorted()方法对于运行时间没有影响
     * 有待考证：但是对于串行流，会使得运行时间大大增加，因此对于包含sorted、distinct()等与全局数据相关的操作，不推荐使用并行流
     */
    @Test
    public void test1() {
        Random random = new Random();
        List<Integer> list = Stream.generate(() -> random.nextInt(100)).limit(100000000).collect(Collectors.toList());

        long begin1 = System.currentTimeMillis();
        list.stream().filter(x->(x > 10)).filter(x->x<80).count();
        long end1 = System.currentTimeMillis();
        System.out.println(end1-begin1);
        list.stream().parallel().filter(x->(x > 10)).filter(x->x<80).count();
        long end2 = System.currentTimeMillis();
        System.out.println(end2-end1);

        long begin1_ = System.currentTimeMillis();
        list.stream().filter(x->(x > 10)).filter(x->x<80).distinct().sorted().count();
        long end1_ = System.currentTimeMillis();
        System.out.println(end1-begin1);
        list.stream().parallel().filter(x->(x > 10)).filter(x->x<80).distinct().sorted().count();
        long end2_ = System.currentTimeMillis();
        System.out.println(end2_-end1_);

    }
}
