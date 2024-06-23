package com.z2xinyu.lambda.common;

import com.z2xinyu.lambda.entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * distinct
 * <p>
 * filter，参考案例{@link StreamIntermediateOperateTest#testFilter()}
 * <p>
 * map
 * flatMap
 * <p>
 * sort
 * <p>
 * peek
 *
 * @author zhangxinyu
 * @date 2024/5/30
 **/
public class StreamIntermediateOperateTest {
    private final String[] arr1 = {"abc", "a", "bc", "abcd", "abcde"};

    @Test
    public void testMap() {
        String[] arr = new String[] {"yes", "YES", "no", "NO"};
        Arrays.stream(arr).map(String::toLowerCase).forEach(System.out::println);
    }

    @Test
    public void testFlatMap() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        // Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);

        List<Student> students = Arrays.asList(new Student("daniel", "body,games"), new Student("jenil", "guidor,dei"));
        //java9：flatMapping
        // Map<String, List<String>> result = personList2.stream()
        //     .collect(Collectors.groupingBy(Person::getName, Collectors.flatMapping(this::trailTypeStream,
        //     Collectors.toList())));
        List<String> hobbies = students.stream().flatMap(this::trailTypeStream).distinct().collect(Collectors.toList());
        System.out.println(hobbies);
    }

    public Stream<String> trailTypeStream(Student person) {
        return Arrays.stream(person.getHobbies().split(","));
    }

    @Test
    public void testFilter() {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(arr).filter(x -> x > 3 && x < 8).forEach(System.out::println);
        Map<Integer, Student> userMap =
            Stream.of(new Student("John", 30), new Student("Julie", 35))
                .filter(distinctByKey(t -> t))
                .collect(Collectors.toMap(Student::getAge, o -> o));
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
     * peek是一个中间操作，接收的是一个consumer，当不想改变流中的元素的类型或改变元素内部状态时，会很有用
     * <p>
     * 如果想对流经的每个元素应用一个函数，从而改变某些状态，那么请用 forEach()；
     * 如果想打印流经的每个元素的状态（日志或 debug），这时应该用 peek()；
     */
    @Test
    public void testPeek() {
        Stream<Student> us = Stream.of(new Student("007", 47), new Student("9527", 42));
        us.peek(u -> u.setName(u.getName() + " -> movie star")).forEach(System.out::println);

        // 根据输出的结果发现，流中的元素是不发生改变的
        Stream.of("a", "b").peek(x -> x.toUpperCase()).forEach(System.out::println);
    }

    /**
     * limit，限制从流中获得前n个数据
     */
    @Test
    public void testLimit() {

        Stream.iterate(1, x -> x + 2)
            // .limit(10)
            .forEach(System.out::println);
    }

    /**
     * skip，跳过前n个数据
     */
    @Test
    public void testSkip() {
        Stream.iterate(1, x -> x + 2).skip(1).limit(5).forEach(System.out::println);
    }

    /**
     * 可以把两个stream合并成一个stream（合并的stream类型必须相同）
     * 只能两两合并
     */
    @Test
    public void testConcat() {
        Stream<String> stream1 = Stream.of(new String[] {"a", "b", "c", "d"});
        Stream<String> stream2 = Stream.of(new String[] {"d", "e", "f", "g"});
        Stream.concat(stream1, stream2).distinct().forEach(System.out::println);
    }

    /**
     * max、min
     * 最大最小值
     */
    @Test
    public void testMaxAndMin() {
        Stream.of(arr1).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Stream.of(arr1).min(Comparator.comparing(String::length)).ifPresent(System.out::println);
    }

    /**
     * count
     * 计算数量
     */
    @Test
    public void testCount() {
        long count = Stream.of(arr1).count();
        System.out.println(count);
    }




}
