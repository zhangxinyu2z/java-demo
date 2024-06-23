package com.z2xinyu.lambda.common;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhangxinyu
 * @date 2024/5/28
 **/
public class StreamCreateTest {

    /**
     * Stream.of
     */
    @Test
    public void testOf() {
        Stream<Integer> stream1 = Stream.of(1, 2, 34, 5, 65);
        stream1.forEach(System.out::println);
        int[] arr = new int[] {1, 2, 34, 5};
        Stream<int[]> stream2 = Stream.of(arr, arr);
        stream2.forEach(System.out::println);
    }

    /**
     * Arrays.stream
     */
    @Test
    public void testArraysStream() {
        int[] arr = new int[] {1, 2, 34, 5};
        IntStream intStream = Arrays.stream(arr);

        //第一个参数告诉 stream() 从数组的哪个位置开始选择元素，第二个参数告知在哪里停止
        Arrays.stream(new int[] {1, 3, 5, 7, 15, 28, 37}, 3, 6).forEach(System.out::print);
    }

    /**
     * 通过集合创建流
     */
    @Test
    public void testCollectionStream() {
        List<String> strs = Arrays.asList("11212", "dfd", "2323", "dfhgf");
        //创建普通流
        Stream<String> stream = strs.stream();
        //创建并行流
        Stream<String> stream1 = strs.parallelStream();
    }

    /**
     * Stream.empty
     */
    @Test
    public void testEmptyStream() {
        //创建一个空的stream
        Stream<Integer> stream = Stream.empty();
    }

    /**
     * Stream.generate
     *
     * 创建无限流
     */
    @Test
    public void testUnlimitStream() {
        //创建无限流，通过limit提取指定大小
        Stream.generate(() -> "number" + new Random().nextInt()).limit(100).forEach(System.out::println);
        Stream.generate(() -> new String("123")).limit(20).forEach(System.out::println);
    }

    /**
     * Stream.iterate
     *
     * 创建规律的无限流
     *
     * Stream.iterate() 产生的流的第一个元素是种子，然后把种子传递给方法，方法的运行结果被添加到流，并作为下次调用 iterate() 的第一个参数
     */
    @Test
    public void testUnlimitStream1() {
        //1
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);
        //2
        Stream.iterate(0, x -> x).limit(10).forEach(System.out::println);
        //3 等同于 2
        Stream.iterate(0, UnaryOperator.identity()).limit(10).forEach(System.out::println);
        //4 斐波那契
        Stream.iterate(new int[] {0, 1}, n -> new int[] {n[1], n[0] + n[1]}).limit(20).map(n -> n[0])
            .forEach(System.out::println);
        int sum = Stream.iterate(new int[] {0, 1}, n -> new int[] {n[1], n[0] + n[1]}).limit(10).map(n -> n[0])
            .mapToInt(n -> n).sum();
        System.out.println("Fibonacci 10 sum : " + sum);
    }

    /**
     * Stream.builder
     */
    @Test
    public void testStreamBuilder() {
        Stream.Builder<String> builder = Stream.builder();
        builder.add("a");
        builder.add("b");
        builder.build();	// 创建流
        // builder.add("c")	// 调用 build() 方法后继续添加元素会产生异常
    }

    /**
     *  IntStream.range()
     *
     *  生成整型序列的流
     */
    @Test
    public void testIntStream() {
        IntStream.range(10, 20).sum();	// 求得 10 - 20 的序列和
        IntStream.range(10, 20).forEach(System.out::print);	// 循环输出 10 - 20
    }

    /**
     * 随机数流
     *
     * boxed() 操作会自动把基本类型包装为对应的装箱类型
     */
    @Test
    public void testRandomStream() {
        Random rand = new Random(47);
        // 产生一个随机流
        rand.ints().boxed();
        // 控制上限和下限
        rand.ints(10, 20).boxed();
        // 控制流的大小
        rand.ints(2).boxed();
        // 控制流的大小和界限
        rand.ints(3, 3, 9).boxed();

        new Random(10).ints(3, 10, 20).boxed().forEach(System.out::println);
    }

    @Test
    public void testReaderStream() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);
    }

    /**
     * 正则
     * 根据传入的公式将字符序列转化为流
     */
    @Test
    public void testPatternStream() {
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }
}
