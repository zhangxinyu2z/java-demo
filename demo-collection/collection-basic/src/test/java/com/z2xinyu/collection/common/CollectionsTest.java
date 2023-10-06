package com.z2xinyu.collection.common;

import com.z2xinyu.collection.po.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhangxinyu
 * @date 2023/8/15
 **/
public class CollectionsTest {

    {
        List<String> al = new ArrayList<String>();
        al.add("wang zuxian");
        al.add("liu yifei");
        al.add("qiu shuzhen");
        al.add("lin qingxia");

        /*
         * 第一种：使用Collections
         * sort方法比较的元素对象必须是一个实现compareable接口的对象
         */
        System.out.println("排序前：" + al);
        Collections.sort(al);
        System.out.println("排序后：" + al);

        System.out.println("--------------------------");
        /*
         * 第二种：public static <T> void sort(List<T> list,
                            Comparator<? super T> c)
            查看api：List接口1.8也新增了一个方法：default void sort(Comparator<? super E> c)
         */
        // 传入一个自定义对象试试
        List<Student> al2 = new ArrayList<Student>();
        Student s1 = new Student("liu yifei", 24);
        Student s2 = new Student("wang zuxian", 25);
        Student s3 = new Student("lin qingxia", 27);
        Student s4 = new Student("zhang manyu", 24);
        Student s5 = new Student("weng meiling", 25);
        Student s6 = new Student("liu yifei", 24);
        al2.add(s1);
        al2.add(s2);
        al2.add(s3);
        al2.add(s4);
        al2.add(s5);
        al2.add(s6);
        System.out.println("排序前：");
        for (Student s : al2) {
            System.out.println("\t" + s.getName() + "---" + s.getAge());
        }

        // Collections.sort(al2); // Student没有实现compareable接口，fails to compile
        Collections.sort(al2);
        // 用比较器的方法实现
        /*
        Collections.sort(al2, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int num = o2.getAge() - o1.getAge();
                return num == 0 ? o1.getName().compareTo(o2.getName()) : num;
            }
        });
        */
        // 事实上Collections的sort方法，底层调用的就是List的sort方法
        al2.sort(new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                int num = o2.getAge() - o1.getAge();
                return num == 0 ? o1.getName().compareTo(o2.getName()) : num;
            }
        });
        System.out.println("排序后：");
        System.out.println("再去除重复的元素：");
        //        keepElementUnique(al2);
        if (al2 != null) {
            for (Student s : al2) {
                System.out.println("\t" + s.getName() + "---" + s.getAge());
            }
        }
    }

    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add("happy");

        // 1
        Collections.addAll(hs, "hello", "world", 12);
        System.out.println(hs);

        ArrayList al = new ArrayList();
        al.add("12");
        al.add("24");
        al.add("a");
        al.add(new String("sd"));
        // 2
        Object obj = Collections.binarySearch(al, "a");
        System.out.println(obj);

        List<Student> l = new ArrayList<>();
        l.add(new Student("yifei", 12));
        l.add(new Student("yifei", 12));
        l.add(new Student("wangle", 12));
        l.add(new Student("wangle", 12));
        l.add(new Student("yifei", 13));

        int index = Collections.binarySearch(l, new Student("yifei", 12));
        System.out.println(index);

        // 3
        // static <T> void	copy​(List<? super T> dest, List<? extends T> src)
        List<String> sl = new ArrayList<>();
        sl.add("happ");
        sl.add("app");
        sl.add("capp");
        // dest 集合的容量必须比src的容量要大
        // 4
        //        List<Object> ol = new ArrayList<>(Arrays.asList(new String[sl.size()]));
        List<Object> ol = new ArrayList<>();
        Collections.addAll(ol, new String[sl.size()]);
        Collections.copy(ol, sl);
        System.out.println("ol: " + ol);

        //
        String rs = Collections.max(sl);
        System.out.println(rs);

        // 5
        // static void	reverse​(List<?> list)
        Collections.reverse(ol);
        System.out.println(ol);

        // 6
        // public static void shuffle(List<?> list):随机置换  可以用来洗牌

        // 7 线程安全
        Collections.synchronizedList(new ArrayList<String>());

        //8 sort
        List<String> stringList = new ArrayList<String>();
        stringList.add("wang zuxian");
        stringList.add("liu yifei");
        stringList.add("qiu shuzhen");
        stringList.add("lin qingxia");

        /*
         * sort方法比较的元素对象必须是一个实现compareable接口的对象
         */
        Collections.sort(stringList);

        // 8 demo
        List<Student> al2 = new ArrayList<Student>();
        Student s1 = new Student("liu yifei", 24);
        Student s2 = new Student("wang zuxian", 25);
        Student s3 = new Student("lin qingxia", 27);
        Student s4 = new Student("zhang manyu", 24);
        Student s5 = new Student("weng meiling", 25);
        Student s6 = new Student("liu yifei", 24);
        al2.add(s1);
        al2.add(s2);
        al2.add(s3);
        al2.add(s4);
        al2.add(s5);
        al2.add(s6);
        Collections.sort(al2);

        // 方式2
        al2.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int num = o2.getAge() - o1.getAge();
                return num == 0 ? o1.getName().compareTo(o2.getName()) : num;
            }
        });

        //去重
        keepElementUnique(al2);
        if (al2 != null) {
            for (Student s : al2) {
                System.out.println("\t" + s.getName() + "---" + s.getAge());
            }
        }

    }

    public static void keepElementUnique(List<Student> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1 + i; j < list.size(); j++) {
                Student s1 = list.get(i);
                Student s2 = list.get(j);
                if (s1.getName().equals(s2.getName())) {
                    list.remove(j);
                    j--;
                }
            }
        }
    }
}
