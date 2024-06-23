package com.z2xinyu.lambda.common;

import com.z2xinyu.lambda.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhangxinyu
 * @date 2024/5/27
 **/
public class LambdaInductTest {

    public void test() {
        primitiveWay();
        optimizeWay();
        stillOptimizeWay();
        latestWay();
    }

    public void test1(){
        List<Student> studentList = new ArrayList<Student>(){
            {
                add(new Student("stu1",100.0));
                add(new Student("stu2",97.0));
                add(new Student("stu3",96.0));
                add(new Student("stu4",95.0));
            }
        };
        //参数2：匿名对象写法
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getScore(),o2.getScore());
            }
        });
        System.out.println(studentList);
    }

    public void primitiveWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort(new Comparator<String>() {
            /*compare方法如何决定是升序还是降序？
                如果a.compreTo(b)大于0，交换元素，小于0则不交换，即码值大的元素放在后面：升序
            反之，b.compareTo(a)>0,码值大的元素被放置到前：降序
            比较的是相邻的元素，索引是，j-1作为this，代表参数a，j作为other，代表参数b
            仅用List集合测试，使用Arrays.sort来排序，跟踪到归并排序算法，排序的前后取决于排序算法*/
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
    }

    public void optimizeWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort((String a, String b) -> {
            return b.compareTo(a);
        });
    }

    public void stillOptimizeWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort((a, b) -> a.compareTo(b));
    }

    public void latestWay() {
        // 方法引用
        Arrays.asList("peter", "anna", "mike", "xenia").sort(String::compareTo);
    }
}
