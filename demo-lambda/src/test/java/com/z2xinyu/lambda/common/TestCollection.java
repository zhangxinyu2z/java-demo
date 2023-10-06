package com.z2xinyu.lambda.common;

import junit.framework.TestCase;
import lombok.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Stream API 提供了丰富的中间函数，归并函数和终端函数，这些函数还支持并行化执行。而归并流的操作的作用是从一个序列的元素重复应用合并操作，最后产生一个单一的结果返回
 * <p>
 * 对集合的流式操作
 * <p>
 * 过滤 filter
 * map
 * 去重 distinct
 * 排序 sort
 * 中间流  peek
 * reduce  引用：https://blog.csdn.net/lijingronghcit/article/details/108348728
 */
public class TestCollection extends TestCase {

    //------------------------------------reduce-----------------------------------------------------

    /**
     * 初始值的定义（Identity)：定义一个元素代表是归并操作的初始值，如果Stream 是空的，也是Stream 的默认结果
     * 累加器（Accumulator)：定义一个带两个参数的函数，第一个参数是上个归并函数的返回值，第二个是Stream中下一个元素，如果Stream 是
     * 空的，也是Stream 的默认结果
     * 组合器（Combiner）:调用一个函数来组合归并操作的结果，当归并是并行执行或者当累加器的函数和累加器的实现类型不匹配时才会调用此函数。
     */
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
            /*
                第一个参数0是初始值，用来保存归并参数的初始值（stream为空作为默认的返回值）
                (subtotal, element)：subtotal是上次累计的和，element是数据流的下一个元素
             */.reduce(0, (subtotal, element) -> subtotal + element);
        assertThat(result).isEqualTo(21);
    }

    public void testReduce2() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", null);
        String result = letters.stream().reduce("", (partialString, element) -> partialString + element);
        //        .reduce("",String::concat); // 使用方法引用，元素为null会有NullPointerException
        assertThat(result).isEqualTo("abcde");
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
    public void testParallelStreamReduce() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        assertThat(computedAges).isEqualTo(160);
    }

    public void testReduceAttention() {
        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
        // 流中包含的是User 对象，但是累加函数的参数分别是数字和user 对象，而累加器的实现是求和，所以编译器无法推断参数 user 的类型, 当顺序读流或者累加器的参数和它的实现的类型匹配时，不需要使用组合器。
        //        int computedAges =
        //            users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge()); // 编译失败
        int computedAges =
            users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    }

    /**
     * 处理异常的方式
     */
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


    //------------------------------------去重：distinct filter toMap：key去重  还可以用collectingAndThen接合TreeSet来去重
    public void testDistinctFilter() {
        testDistinct();
        testFilter();
        deduplicate();

    }

    public void deduplicate() {
        List<String> data = new ArrayList<>(Arrays.asList("1", "3", "51", "1"));
        Set<String> set = new LinkedHashSet<>(data);
        data.clear();
        data.addAll(set);
        System.out.println(data);
    }

    public void testFilter() {
        Map<Integer, User> userMap =
            Stream.of(new User("John", 30), new User("Julie", 35)).filter(distinctByKey(t -> t))
                .collect(Collectors.toMap(User::getAge, o -> o));
    }

    /**
     * 自定义去重逻辑：使用对象的属性来去重
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * java.util.stream.Stream.distinct test
     *
     * <p>
     * 1. 重复的数据取第一个出现的，后面重复的忽略
     * 2. 使用元素的hashcode()和equals()方法来取不同的元素
     * 3. 有序流，那么对于重复元素，将保留以遭遇顺序首先出现的元素，这种选择不同元素是稳定的;无序流的情况下，不同元素的选择不一定是稳定的,是可发生改变的
     * 4。不提供按照指定的属性对对象去重，可以使用示例中的 distinctByKey
     *
     * ref doc:
     *     https://blog.csdn.net/haiyoung/article/details/80934467
     *     https://blog.csdn.net/Lamb_IT/article/details/100186435  Stream: api
     */
    public void testDistinct() {
        Stream.of("I", "love", "you", "and", "you", "love", "me").distinct().forEach(System.out::print);
        System.out.println();
        List<Integer> numList = Arrays.asList(12, 24, 12, 60);
        numList.stream().distinct().forEach(System.out::print);
        System.out.println();
        // 并行流
        Stream.of("1", "2", "3", "4", "4", "3", "2", "1").parallel().distinct().forEach(System.out::print);
    }

    @AllArgsConstructor
    @Setter
    @Getter
    @Data
    class User {
        private String username;
        private int age;
    }

    // ===============================peek=====================================

    /**
     * peek是一个中间操作，接收的是一个consumer，当不想改变流中的元素的类型或改变元素内部状态时，会很有用
     *
     * 如果想对流经的每个元素应用一个函数，从而改变某些状态，那么请用 forEach()；
     * 如果想打印流经的每个元素的状态（日志或 debug），这时应该用 peek()；
     *
     */
    public void testPeek() {
        Stream<User> us = Stream.of(new User("007", 47), new User("9527", 42));
        us.peek(u->u.setUsername(u.getUsername() + " -> movie star")).forEach(System.out::println);

        // 根据输出的结果发现，流中的元素是不发生改变的
        Stream.of("a","b")
                .peek(x -> x.toUpperCase())
                .forEach(System.out::println);

    }
}
