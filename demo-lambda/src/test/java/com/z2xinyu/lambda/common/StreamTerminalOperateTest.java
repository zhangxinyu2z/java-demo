package com.z2xinyu.lambda.common;

import com.z2xinyu.lambda.entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author zhangxinyu
 * @date 2024/6/23
 **/
public class StreamTerminalOperateTest {

    private final String[] arr1 = {"abc", "a", "bc", "abcd", "abcde"};

    /**
     * findFirst
     * 查找第一个
     */
    @Test
    public void testFindFirst() {
        String str = Stream.of(arr1).parallel().filter(x -> x.length() > 3).findFirst().orElse("noghing");
        System.out.println(str);
    }

    /**
     * findAny
     * 找到所有匹配的元素
     * 对并行流十分有效
     * 只要在任何片段发现了第一个匹配元素就会结束整个运算
     */
    @Test
    public void testFindAny() {
        Optional<String> optional = Stream.of(arr1).parallel().filter(x -> x.length() > 3).findAny();
        optional.ifPresent(System.out::println);
    }

    /**
     * anyMatch
     * 是否含有匹配元素
     */
    @Test
    public void testAnyMatch() {
        Boolean aBoolean = Stream.of(arr1).anyMatch(x -> x.startsWith("a"));
        System.out.println(aBoolean);
    }

    /**
     * 初始值的定义（Identity)：定义一个元素代表是归并操作的初始值，如果Stream 是空的，也是Stream 的默认结果
     * 累加器（Accumulator)：定义一个带两个参数的函数，第一个参数是上个归并函数的返回值，第二个是Stream中下一个元素，如果Stream 是
     * 空的，也是Stream 的默认结果
     * 组合器（Combiner）:调用一个函数来组合归并操作的结果，当归并是并行执行或者当累加器的函数和累加器的实现类型不匹配时才会调用此函数。
     */
    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
            /*
                第一个参数0是初始值，用来保存归并参数的初始值（stream为空作为默认的返回值）
                (subtotal, element)：subtotal是上次累计的和，element是数据流的下一个元素
             */.reduce(0, (subtotal, element) -> subtotal + element);
        assertThat(result).isEqualTo(21);

        List<String> letters = Arrays.asList("a", "b", "c", "d", null);
        String result2 = letters.stream().reduce("", (partialString, element) -> partialString + element);
        //        .reduce("",String::concat); // 使用方法引用，元素为null会有NullPointerException
        assertThat(result2).isEqualTo("abcde");
    }

    /**
     * 操作并行流
     * <p>
     * 一般并行处理包含大量数据的流或者耗时的操作。
     * 结果和处理的顺序无关
     * 操作不影响原有数据
     * 操作没有状态和同样的输入有一样的输出结果
     * <p>
     * 当对一个流进行并行操作时，在运行时会把流分割多个子流来并行操作。需要一个函数来组合各个子流返回的结果，这个函数就是Combiner。
     */
    @Test
    public void testParallelStreamReduce() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        assertThat(computedAges).isEqualTo(160);
    }

    @Test
    public void testReduceAttention() {
        List<Student> users = Arrays.asList(new Student("John", 30.0), new Student("Julie", 35.0));
        // 流中包含的是User 对象，但是累加函数的参数分别是数字和user 对象，而累加器的实现是求和，所以编译器无法推断参数 user 的类型,
        // 当顺序读流或者累加器的参数和它的实现的类型匹配时，不需要使用组合器。
        //        int computedAges =
        //            users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge()); // 编译失败
        double computedAges =
            users.stream().reduce(0.0, (partialAgeResult, user) -> partialAgeResult + user.getScore(), Double::sum);
    }

    @Test
    public void testReduceDealException() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int divider = 2;
        int result = divideListElements(numbers, divider);
    }

    private int divideListElements(List<Integer> numbers, int divider) {
        //        return numbers.stream().reduce(0, (a, b) -> {
        //            try {
        //                return a / 2 + b / 2;
        //            } catch (ArithmeticException e) {
        //                e.printStackTrace();
        //            }
        //            return 0;
        //        });
        // 优化
        return numbers.stream().reduce(0, (a, b) -> divide(a, divider) + divide(b, divider));
    }

    private int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return result;
    }
}
