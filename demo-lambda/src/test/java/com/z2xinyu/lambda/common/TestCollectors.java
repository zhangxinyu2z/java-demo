package com.z2xinyu.lambda.common;

import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 本用例用来测试1.8新增的收集器：Collectors类的方法
 * Java所有集合的 stream().collect() 可以接受一个收集器实例作为其参数并返回该收集器的计算结果
 * <p>
 * java.util.stream.Collectors 实现了 java.util.stream.Collector 接口，提供了大量的方法对流 ( stream )的元素执行 map and reduce 以及统计操作。
 *
 *
 * collectingAndThenExample()
 */
public class TestCollectors extends TestCase {


    public void testGroupingBy() {
        List<Person> personList =
                Arrays.asList(new Person("wangwu", 12), new Person("zhaosi", 24), new Person("xiaopo", 12));
        Map<Integer, List<Person>> map = personList.stream().collect(Collectors.groupingBy(Person::getAge));
        // 根据age分组，value是List, 映射的数据类型是TreeMap  传递給下游收集器后，统计每个List类型的value的size
        TreeMap<Integer, Long> map2 =
                personList.stream().collect(Collectors.groupingBy(Person::getAge, TreeMap::new, Collectors.counting()));
    }

    /**
     * 通过在累加之前对每个输入元素应用映射函数，将接受U类型元素的Collector调整为接受T类型元素。
     */
    public void testMapping() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Ram", "hangzhou"));
        personList.add(new Person(2, "Shy", "beijing"));
        personList.add(new Person(3, "Mash", "jinan"));
        personList.add(new Person(4, "Dali", "hangzhou"));
        // 使用映射函数转换类型，将映射结果提供给下游收集器
        String comNames =
                personList.stream().collect(Collectors.mapping(Person::getName, Collectors.joining(",", "[", "]")));
        System.out.println(comNames);

        Map<String, Set<String>> cityMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.mapping(Person::getName,
                        Collectors.toSet())));
        cityMap.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(name -> {
                System.out.println("\t" + name);
            });
        });
    }

    /*
     * toMap(Function keyMapper, Function valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier);
     *         keyMapper: 定义key的映射函数，Person::getId 使用Person的id作为key
     *         valueMapper: 定义value的映射函数，v->v 使用list中的元素作为作为Map的value       value的值不要为null，会报NullPointerException
     *         mergeFunction: (key1,key2) -> key1)如果key存在冲突，数据以key1数据为准，如果后面的数据要覆盖前面的，映射为key2
     *                          流中的数据重复，会出现IllegalStateException:Duplicate
     *  key
     *         mapSupplier：Map 构造器，在需要返回特定的 Map 时使用
     */
    public void testToMap() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "Ram", "hangzhou"));
        list.add(new Person(2, "Shy", "neimeng"));
        list.add(new Person(3, "Mash", "jinan"));

        Map<Integer, Person> collect = list.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        Map<Integer, Person> collect1 =
                list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
        Map<Integer, Person> collect2 = list.stream().collect(Collectors.toMap(Person::getId, v -> v, (a, b) -> a));
        Collection<Person> values =
                list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a)).values();
        long count =
                list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a)).values().stream()
                        .count();
        System.out.println(collect);
        System.out.println(collect1);
        System.out.println(collect2);
        System.out.println(values);
        System.out.println(count);
    }

    /**
     * 将流中的所有元素视为 int类型，并计算所有元素的总和 ( sum )
     */
    public void testSummingInt() {
        List<Integer> numList = Arrays.asList(1, 14, 32, 41, 4);
        // 方法体只执行一次
        Integer sum = numList.stream().collect(Collectors.summingInt(i -> {
            System.out.println(i);
            return i;
        }));
        System.out.println(sum);
    }

    public void testSummingDouble() {
        List<Double> list = Arrays.asList(1.1, 2.2, 3.3, 4.4);
        // 注意的地方：每个数值会打印两次
        Double sum = list.stream().collect(Collectors.summingDouble(i -> {
            System.out.println("i -> " + i);
            return i;
        }));
        System.out.println(sum);
    }

    /**
     * 应用：去重
     * ref doc : https://blog.csdn.net/qq_35634181/article/details/108867857?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-108867857-blog-120088269.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-108867857-blog-120088269.pc_relevant_aa&utm_relevant_index=2
     */
    public void testCollectingAndThen() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Double result = list.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(v -> {
            // 方法体只执行一次
                    System.out.println("v--" + v + "--> " + v * 2);
                    return v * 2;
                }),
                s -> {
                    System.out.println("s--" + s + "--> " + s * s);
                    return s * s;
                }));
        System.out.println(result);
        // ------------------------------------------
        // 去重
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Ram", "hangzhou"));
        personList.add(new Person(2, "Shy", "neimeng"));
        personList.add(new Person(2, "Mash", "jinan"));
        List<Person> distinctList = personList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Person::getId))
        ), ArrayList::new));

    }

    /**
     * 平均值
     * 该方法使用需要注意
     */
    public void testAveragingDouble() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Double result = list.stream().map(s -> {
            // 方法体执行一次
            System.out.println("map-> " + s);
            return s;
        }).collect(Collectors.averagingDouble(d -> {
            // 方法体执行2次， 等同于对流中的每个元素都会操作2次
            System.out.println(d + "-->" + d * 2);
            return d * 2;
        }));
        System.out.println(result);
    }

    /**
     * 统计数据
     */
    public void testSummarizing() {
        List<Person> personList =
                Arrays.asList(new Person("广东", 23), new Person("广东", 24), new Person("广东", 23), new Person("北京", 22),
                        new Person("北京", 20), new Person("北京", 20), new Person("海南", 25));

        IntSummaryStatistics summaryStatistics = personList.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("平均值：" + summaryStatistics.getAverage());//平均值：22.428571428571427
        System.out.println("人数：" + summaryStatistics.getCount());//人数：7
        System.out.println("最大值：" + summaryStatistics.getMax());//最大值：25
        System.out.println("最小值：" + summaryStatistics.getMin());//最小值：20
        System.out.println("总和：" + summaryStatistics.getSum());//总和：157
    }

    @Data
    @AllArgsConstructor
    public static class Person {
        private Integer id;
        private String name;
        private int age;
        private String city;

        public Person(Integer id, String name, String city) {
            this.id = id;
            this.name = name;
            this.city = city;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
