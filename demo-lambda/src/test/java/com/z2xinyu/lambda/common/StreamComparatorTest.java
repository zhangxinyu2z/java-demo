package com.z2xinyu.lambda.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

/**
 * 排序:
 * <p>
 * static <T,U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T,? extends U> keyExtractor)
 * 将从一个类型T中提取一个可比较的排序键，并返回一个通过该排序键进行比较的比较器
 * static <T,U> Comparator<T> comparing(Function<? super T,? extends U> keyExtractor, Comparator<? super U>
 * keyComparator)
 * 从一个类型T中提取一个排序键，并返回一个比较器，使用指定的比较器对该排序键进行比较
 * <p>
 * reverseOrder:倒序
 *
 * @author zhangxinyu
 * @since 2022/9/20 15:32
 */
public class StreamComparatorTest {

    private final String[] arr1 = {"abc", "a", "bc", "abcd", "abcde"};

    @Test
    public void testSorted1() {
        Arrays.stream(arr1).sorted((x, y) -> {
            if (x.length() > y.length())
                return 1;
            else if (x.length() < y.length())
                return -1;
            else
                return 0;
        }).forEach(System.out::println);
    }

    /**
     * reversed(),java8泛型推导的问题，所以如果comparing里面是非方法引用的lambda表达式就没办法直接使用reversed()
     */
    @Test
    public void testReversed() {
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
    }

    /**
     * Comparator.reverseOrder():也是用于翻转顺序，用于比较对象（Stream里面的类型必须是可比较的）
     */
    @Test
    public void testReverseOrder() {
        Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    /**
     * Comparator. naturalOrder()：返回一个自然排序比较器，用于比较对象（Stream里面的类型必须是可比较的）
     */
    @Test
    public void testNaturalOrder() {
        Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
    }

    /**
     * thenComparing
     * 先按照首字母排序
     * 之后按照String的长度排序
     */
    @Test
    public void testSorted3() {
        Arrays.stream(arr1).sorted(Comparator.comparing(this::com1).thenComparing(String::length))
            .forEach(System.out::println);
    }

    public char com1(String x) {
        return x.charAt(0);
    }


    /**
     * 找到由nullsLast方法返回的比较器的工作原理。
     * 1. 空元素被认为是大于非空元素的。
     * 2. 当两个元素都是空的时候，那么它们被认为是相等的。
     * 3. 当两个元素都是非空的时候，指定的比较器决定了顺序。
     * 4. 如果指定的比较器是空的，那么返回的比较器认为所有非空的元素是相等的。
     */
    @Test
    public void testNullsLast() {
        Student s1 = new Student("Ram", 18);
        Student s2 = new Student("Shyam", 22);
        Student s3 = new Student("Mohan", 17);

        System.out.println("-------Case1: One null----------");
        // Case-1: 我们的集合中有一个空元素。在排序中，由于nullsLast方法的存在，空元素将排在最后。非空元素的顺序将由传递给nullsLast方法的比较器决定

        List<Student> list = Arrays.asList(s1, s2, null, s3);
        sort(list, Comparator.nullsLast(Comparator.comparing(Student::getName)));
        list.forEach(System.out::println);

        System.out.println("--------Case2: More than one null---------");
        //        我们有一个以上的空元素。我们知道，当两个元素都是空的时候，那么它们被认为是相等的。所以，所有的空元素将被排在最后。非空元素的顺序将由传递给nullsLast方法的比较器决定。

        list = Arrays.asList(s1, null, s2, null, s3);
        sort(list, Comparator.nullsLast(Comparator.comparing(Student::getName)));
        list.forEach(System.out::println);

        System.out.println("--------Case3: Reverse specified Comparator to nullsLast---------");
        //        Case-3: 在这种情况下，我们将指定的比较器反转到nullsLast方法。这将只影响非空元素的顺序。所有的空元素将被排在最后。

        list = Arrays.asList(s1, null, s2, null, s3);
        sort(list, Comparator.nullsLast(Comparator.comparing(Student::getName).reversed()));
        list.forEach(System.out::println);

        System.out.println("--------Case4: Reverse Comparator returned by nullsLast---------");
        //        Case-4: 在这种情况下，我们将nullsLast方法返回的比较器反转。现在，所有的空元素将被排在第一位。

        list = Arrays.asList(s1, null, s2, null, s3);
        sort(list, Comparator.nullsLast(Comparator.comparing(Student::getName)).reversed());
        list.forEach(System.out::println);

        System.out.println("--------Case5: Specify natural order Comparator to nullsLast---------");
        //        Case-5: 在这种情况下，我们通过比较器来使用元素的自然顺序。对于自然顺序，元素类需要实现Comparable和覆盖compareTo方法。所有的空元素将被排在最后，非空元素将被排在其自然顺序上。

        list = Arrays.asList(s1, null, s2, null, s3);
        sort(list, Comparator.nullsLast(Comparator.naturalOrder()));
        list.forEach(System.out::println);

        System.out.println("--------Case6: Specify null to nullsLast---------");
        //        Case-6: 在这种情况下，我们将null传递给nullsLast方法。我们知道，如果指定给nullsLast的比较器是null，那么返回的比较器认为所有非null
        //        元素都是相等的。所有的空元素将被排在最后，对非空元素的顺序没有影响。

        list = Arrays.asList(s1, null, s2, null, s3);
        sort(list, Comparator.nullsLast(null));
        list.forEach(System.out::println);

        // Case-7
        List<Student> collect =
            list.stream().sorted(Comparator.comparing(it -> it, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    static class Student implements Comparable<Student> {
        private String name;
        private int age;

        public static List<Student> getStudentList() {
            Student s1 = new Student("huimin", 24);
            Student s2 = new Student("yihei", 30);
            Student s3 = new Student("menlu", 28);
            Student s4 = new Student("suzhen", 25);
            Student s5 = new Student("yifei", 30);
            Student s6 = new Student("menlu", 27);
            List<Student> students = Arrays.asList(s1, s2, s3, s4, s5, s6);
            return students;
        }

        /**
         * 如果返回值等于零: o1 = o2
         * 返回值大于零则 o1 > o2
         * 返回值小于于零则 o1 < o2
         */
        @Override
        public int compareTo(Student o) {
            return name.compareTo(o.getName());
        }

        @Override
        public String toString() {
            return name + "-" + age;
        }
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private Integer age;
    }

    /**
     * 多字段排序
     */
    @Test
    public void testComparing() {
        ArrayList<Person> list = new ArrayList<>();
        Person a = new Person("a", 50);
        Person b = new Person("b", 60);
        Person c = new Person("c", 70);
        Person d = new Person("d", 70);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        System.out.println("排序前：" + list);
        List<Person> asc = list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName))
            .collect(Collectors.toList());
        /*
         * 先按年龄排序，再按姓名排序（升序）
         */
        System.out.println("升序排序:" + asc);
        List<Person> desc =
            list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getName).reversed())
                .collect(Collectors.toList());
        /*
         * 先按年龄排序，再按姓名排序（降序）
         */
        System.out.println("降序排序：" + desc);
    }

    /**
     * 排序的几种方式：
     * Collections.sort
     * list.sort
     * stream().sort
     */
    @Test
    public void testComparingBasic() {
        // 1、实现compare方法
        List<Student> studentList = Student.getStudentList();
        //        sort(studentList);

        // 使用Comparator指定的排序键比较，comparable无法影响排序状态
        Comparator<Student> comparator = Comparator.comparing(Student::getAge);
        //        Collections.sort(studentList,comparator);

        Comparator<Student> comparator2 =
            Comparator.comparing(Student::getAge, Comparator.reverseOrder()); // (s1,s2) -> s2.compareTo(s1)
        Collections.sort(studentList, comparator2);
        //        studentList.sort(comparator2);
        System.out.println(studentList);

        // 演示：
        Integer[] x = new Integer[] {2, 82, 171, 31, 321};
        Arrays.sort(x, Integer::compareTo);
        System.out.println(Arrays.toString(x));

    }

    /**
     * ComparingDouble、ComparingLong 同理
     */
    @Test
    public void testComparingInt() {
        List<Student> studentList = Student.getStudentList();
        studentList.sort(Comparator.comparingInt(Student::getAge));
        System.out.println(studentList);
    }


    /**
     *https://zhuanlan.zhihu.com/p/447595722
     */
    @Test
    public void testTls() {
        ArrayList<Person> list = new ArrayList<>();
        Person a = new Person("100.00", 50);
        Person c = new Person("24.13", 70);
        Person b = new Person("34.134", 60);
        Person d = new Person("2341.12", 70);
        Person e = new Person("0.00", null);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

//        list.stream().sorted(Comparator.comparing(Person::getAge, Comparator.nullsLast(Integer::compareTo)));

        List<Person> collect = list.stream()
            .sorted(Comparator.comparing(Person::getName, Comparator.comparingDouble(Double::parseDouble))
//            .thenComparing()
                        )
            .collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}
