package com.z2xinyu.lambda.common;

import com.z2xinyu.lambda.entity.Student;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author zhangxinyu
 * @date 2024/5/31
 **/
public class OptionalTest {

    @Test
    public void testStream1() {
        Optional<Student> studentOptional = Optional.of(new Student("user1",21));
        Optional<String> optionalStr = studentOptional.map(Student::getName);
        System.out.println(optionalStr.get());
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    /**
     * Optional的迭代
     */
    @Test
    public void testStream2() {
        double x = 4d;
        Optional<Double> result1 = inverse(x).flatMap(OptionalTest::squareRoot);
        result1.ifPresent(System.out::println);
        Optional<Double> result2 = Optional.of(4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        result2.ifPresent(System.out::println);
    }

    /**
     * 指定返回值或抛出异常
     */
    @Test
    public void demo03() {
        Integer[] arr = new Integer[]{4,5,6,7,8,9};
        Integer result = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElse(-1);
        System.out.println(result);
        Integer result1 = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElseGet(()->-1);
        System.out.println(result1);
        Integer result2 = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        System.out.println(result2);
    }
}
