package com.z2xinyu.lambda.common;

import com.z2xinyu.lambda.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API 提供了丰富的中间函数，归并函数和终端函数，这些函数还支持并行化执行。
 * 而归并流的操作的作用是从一个序列的元素重复应用合并操作，最后产生一个单一的结果返回
 * 本用例用来测试1.8新增的收集器：Collectors类的方法
 * <p>
 * Java所有集合的 stream().collect() 可以接受一个收集器实例作为其参数并返回该收集器的计算结果
 * <p>
 * toList
 * toSet
 * toMap    保持顺序不变，使用LinkedHashMap::new
 * toCollection
 * <p>
 * toArray
 * <p>
 * mapping
 * <p>
 * groupingBy
 * <p>
 * partitioningBy
 * <p>
 * summarizing
 * <p>
 * averaging
 * <p>
 * collectingAndThen    保持顺序不变
 *
 * @author zhangxinyu
 * @date 2024/5/31
 **/
public class StreamOperateCollectTest {

    Student[] students;

    @Before
    public void init() {
        students = new Student[100];
        for (int i = 0; i < 30; i++) {
            Student student = new Student("user", i);
            students[i] = student;
        }
        for (int i = 30; i < 60; i++) {
            Student student = new Student("user" + i, i);
            students[i] = student;
        }
        for (int i = 60; i < 100; i++) {
            Student student = new Student("user" + i, i);
            students[i] = student;
        }

    }

    /**
     * toList
     * toSet
     * toMap
     */
    @Test
    public void testToCollection() {
        /*
          生成List
         */
        List<Student> list = Arrays.stream(students).collect(Collectors.toList());
        list.forEach(System.out::println);
        /*
          生成Set
         */
        Set<Student> set = Arrays.stream(students).collect(Collectors.toSet());
        set.forEach(System.out::println);
        /*
          如果包含相同的key，则需要提供第三个参数，否则报错
         */
        Map<String, Double> map =
            Arrays.stream(students).collect(Collectors.toMap(Student::getName, Student::getScore, (s, a) -> s + a));
        map.forEach((x, y) -> System.out.println(x + "->" + y));

        HashSet<Student> s = Arrays.stream(students).collect(Collectors.toCollection(HashSet::new));
        s.forEach(System.out::println);
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
    @Test
    public void testToMap() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("100032001", "Ram", "hangzhou"));
        list.add(new Student("100032002", "Shy", "neimeng"));
        list.add(new Student("100032003", "Mash", "jinan"));

        Map<String, Student> collect =
            list.stream().collect(Collectors.toMap(Student::getIdentityId, Function.identity()));
        System.out.println(collect);

        Map<String, Student> collect1 =
            list.stream().collect(Collectors.toMap(Student::getIdentityId, Function.identity(), (a, b) -> a));
        System.out.println(collect1);
        Map<String, Student> collect2 =
            list.stream().collect(Collectors.toMap(Student::getIdentityId, v -> v, (a, b) -> a));
        System.out.println(collect2);
        Collection<Student> values =
            list.stream().collect(Collectors.toMap(Student::getIdentityId, Function.identity(), (a, b) -> a)).values();
        System.out.println(values);
        long count = list.stream()
            .collect(Collectors.toMap(Student::getIdentityId, Function.identity(), (a, b) -> a)).values()
            .stream().count();
        System.out.println(count);

        Map<String, Student> itemMap = list.stream().collect(
            Collectors.toMap(Student::getName, Function.identity(), (o1, o2) -> o2, LinkedHashMap::new));
        itemMap.forEach((k,v)-> {
            System.out.println("k:" + k  + ",v:" + v);
        });
    }

    /**
     * toArray：生成数组
     */
    @Test
    public void testToArray() {
        Student[] s = Arrays.stream(students).toArray(Student[]::new);
        for (Student student : s)
            System.out.println(student);
    }

    @Test
    public void testGroupingBy1() {
        // Case1
        Map<String, List<Student>> map = Arrays.stream(students).collect(Collectors.groupingBy(Student::getName));
        map.forEach((x, y) -> System.out.println(x + "->" + y));
    }

    /**
     * downstream指定类型
     */
    @Test
    public void testGroupingBy2() {
        Map<String, Set<Student>> map =
            Arrays.stream(students).collect(Collectors.groupingBy(Student::getName, Collectors.toSet()));
        map.forEach((x, y) -> System.out.println(x + "->" + y));
    }


    @Test
    public void testGroupingBy3() {
        // Case2 根据age分组，value是List, 映射的数据类型是TreeMap  传递給下游收集器后，统计每个List类型的value的size
        TreeMap<Integer, Long>
            map2 = Arrays.stream(students).collect(Collectors.groupingBy(Student::getAge, TreeMap::new,
            Collectors.counting()));
    }

    /**
     * downstream 聚合操作
     */
    @Test
    public void testGroupingBy4() {
        /*
          counting
         */
        Map<String, Long> map1 =
            Arrays.stream(students).collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        map1.forEach((x, y) -> System.out.println(x + "->" + y));
        /*
          summingInt
         */
        Map<String, Double> map2 = Arrays.stream(students)
            .collect(Collectors.groupingBy(Student::getName, Collectors.summingDouble(Student::getScore)));
        map2.forEach((x, y) -> System.out.println(x + "->" + y));
        /*
          maxBy
         */
        Map<String, Optional<Student>> map3 = Arrays.stream(students).collect(
            Collectors.groupingBy(Student::getName, Collectors.maxBy(Comparator.comparing(Student::getScore))));
        map3.forEach((x, y) -> System.out.println(x + "->" + y));
        /*
          mapping
         */
        Map<String, Set<Double>> map4 = Arrays.stream(students).collect(
            Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getScore, Collectors.toSet())));
        map4.forEach((x, y) -> System.out.println(x + "->" + y));
    }

    @Test
    public void testGroupingBy5() {
        List<Student> students = Arrays.stream(this.students).collect(Collectors.toList());
        for (Student student : students) {
            student.setHobbies("happy,gali");
        }
        Map<String, List<String>> map =
            students.stream().collect(Collectors.groupingBy(Student::getName)).entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey,
                    e -> e.getValue().stream().flatMap(this::trailTypeStream).distinct().collect(Collectors.toList())));
        map.forEach((k,v)-> {
            System.out.println("k:"+ k + ",v:" + v);
        });
    }


    public Stream<String> trailTypeStream(Student student) {
        return Arrays.stream(student.getHobbies().split(","));
    }

    /**
     * 如果只有两类，使用partitioningBy会比groupingBy更有效率
     */
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Student>> map =
            Arrays.stream(students).collect(Collectors.partitioningBy(x -> x.getScore() > 50));
        map.forEach((x, y) -> System.out.println(x + "->" + y));
    }

    /**
     * 通过在累加之前对每个输入元素应用映射函数，将接受U类型元素的Collector调整为接受T类型元素。
     */
    @Test
    public void testMapping() {
        List<Student> personList = new ArrayList<>();
        personList.add(new Student("Ram", "hangzhou"));
        personList.add(new Student("Shy", "beijing"));
        personList.add(new Student("Mash", "jinan"));
        personList.add(new Student("Dali", "hangzhou"));
        // 使用映射函数转换类型，将映射结果提供给下游收集器
        String comNames = personList.stream()
            .collect(Collectors.mapping(Student::getName, Collectors.joining(",", "[", "]")));
        System.out.println(comNames);

        Map<String, Set<String>> cityMap = personList.stream()
            .collect(Collectors.groupingBy(
                Student::getCity, Collectors.mapping(Student::getName, Collectors.toSet())));
        cityMap.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(name -> {
                System.out.println("\t" + name);
            });
        });
    }

    /**
     * 统计
     */
    @Test
    public void testSummarizing() {
        DoubleSummaryStatistics summaryStatistics =
            Arrays.stream(students).collect(Collectors.summarizingDouble(Student::getScore));
        System.out.println("getAverage->" + summaryStatistics.getAverage());
        System.out.println("getMax->" + summaryStatistics.getMax());
        System.out.println("getMin->" + summaryStatistics.getMin());
        System.out.println("getCount->" + summaryStatistics.getCount());
        System.out.println("getSum->" + summaryStatistics.getSum());
    }

    /**
     * 将流中的所有元素视为 int类型，并计算所有元素的总和 ( sum )
     */
    @Test
    public void testSummingInt() {
        List<Integer> numList = Arrays.asList(1, 14, 32, 41, 4);
        // 方法体只执行一次
        Integer sum = numList.stream().collect(Collectors.summingInt(i -> {
            System.out.println(i);
            return i;
        }));
        System.out.println(sum);
    }

    @Test
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
     * ref doc : https://blog.csdn.net/qq_35634181/article/details/108867857?spm=1001.2101.3001.6650
     * .1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-108867857-blog-120088269
     * .pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant
     * .none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-108867857-blog-120088269.pc_relevant_aa&utm_relevant_index=2
     */
    @Test
    public void testCollectingAndThen() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        Double result = list.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(v -> {
            // 方法体只执行一次
            System.out.println("v--" + v + "--> " + v * 2);
            return v * 2;
        }), s -> {
            System.out.println("s--" + s + "--> " + s * s);
            return s * s;
        }));
        System.out.println(result);
        // ------------------------------------------
        // 去重
        List<Student> personList = new ArrayList<>();
        personList.add(new Student("103910001", "Ram", "hangzhou"));
        personList.add(new Student("103910002", "Shy", "neimeng"));
        personList.add(new Student("103910003", "Mash", "jinan"));
        List<Student> distinctList = personList.stream().collect(Collectors.collectingAndThen(
            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getIdentityId))), ArrayList::new));
    }

    /**
     * 获取到的结果保持顺序不变
     */
    @Test
    public void testCollectingAndThen2() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("103910001", "Ram", "hangzhou"));
        students.add(new Student("103910002", "Shy", "neimeng"));
        students.add(new Student("103910003", "Mash", "jinan"));

        Map<String, List<Student>> footerMap = students.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Student::getName, LinkedHashMap::new, Collectors.toList()),
                map -> new LinkedHashMap<>(map)
            ));



    }

    /**
     * 平均值
     * 该方法使用需要注意
     */
    @Test
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

}
