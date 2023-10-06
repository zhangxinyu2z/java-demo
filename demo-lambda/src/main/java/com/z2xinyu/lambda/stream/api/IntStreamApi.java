package com.z2xinyu.lambda.stream.api;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java.util.stream.IntStream API test
 *
 * @author zhangxinyu
 * @since 2022/6/8 17:50
 */
public class IntStreamApi {
    @Test
    public void testIntStreamBoxed() {
        // 返回一个包含此流元素的流，每个元素都在Integer中
        // Creating an IntStream
        IntStream stream = IntStream.range(3, 8);

        // Creating a Stream of Integers
        // Using IntStream boxed() to return
        // a Stream consisting of the elements
        // of this stream, each boxed to an Integer.
        Stream<Integer> stream1 = stream.boxed();

        // Displaying the elements
        stream1.forEach(System.out::println);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 5);
        // 存放int的stream
        IntStream intStream = numbers.stream().mapToInt(i -> i);  //转成IntStream
        // 转成存放Integer的stream
        Stream<Integer> boxed = intStream.boxed();
    }
}
