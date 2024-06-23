package com.z2xinyu.collection.list;

import com.z2xinyu.collection.po.Girl;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class ArrayListTest extends TestCase {

    public void testAddElement() {
        List<String> al = new ArrayList<>();
        // IndexOutOfBoundsException，集合中没有元素时只能添加index=0,不能超出集合的size
        // al.add(1, "james");
        al.add(0, "lems");
    }

    /**
     * E set(int index,E element) 用指定元素替换此列表中指定位置的元素
     * E remove(int index) 移除此列表中指定位置的元素
     */
    public void testModifyElement() {

    }

    /**
     * int indexOf(Object o) 返回此列表中指定元素的第一个出现的索引, 否则 -1
     * List<E> subList(int fromIndex,int toIndex) 对子集合的操作会影响原集合
     */
    public void testGet() {
        List<String> al = initTestData();
        int lrt = al.indexOf("李若彤");
        System.out.println("List indexof:" + lrt);
        String element = al.get(0);
        System.out.println("List get(0):" + element);

        System.out.println("al=" + al);
        List<String> subList = al.subList(0, 2);
        System.out.println("subList=" + subList);
        subList.set(0, "曼玉");
        System.out.println("subList=" + subList);
        System.out.println("al=" + al);
    }

    public void testListIterator() {
        List<String> stringList = initTestData();
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("element:" + next);
        }
    }

    /**
     * java.util.ConcurrentModificationException
     * <p>
     * for循环中无法使用，remove
     * 说明：集合的增强for底层调用的是迭代器，查看源码发现，ArrayList类的内部类Itr，执行next()和remove()方法，每次都会调用checkForComodification()
     * ，而该方法会判断modCount和expectedModCount（集合发生改变的次数）是否相等，不等会抛出该异常。modCount是AbstractList类中的一个变量：
     * protected transient int modCount = 0;
     * ArrayList的每次add(ensureCapacityInternal)或remove(fastRemove)调用，都会modCount++
     * expectedModCount定义在Itr中，只有调用iterator，生成迭代器对象时，才会对expectedModCount初始化：expectedModCount=modCount;
     * 因此问题发生了，但调用ArrayList中的add或remove方法时，就会导致modCount发生改变，两个变量的值不相等就会抛出并发修改异常。
     */
    public void testConcurrentModificationException() {
        List<String> stringList = initTestData();
        // 错误的案例演示：无法实现
        // for(Iterator<String> iterator = stringList.iterator(); iterator.hasNext();) {
        //     String  e = iterator.next();
        //     if(!e.equals("关之琳")) {
        //         // ConcurrentModificationException
        //         stringList.add("关之琳");
        //     }
        // }
        // 实现方式一：使用迭代器自己的方法，添加的位置就是指针的位置
        for(ListIterator<String> iterator = stringList.listIterator(); iterator.hasNext();) {
            String  e = iterator.next();
            if(!e.equals("关之琳")) {
                iterator.add("关之琳");
                break;
            }
        }
        // 实现方式二：标准for循环
        // for (int i = 0; i < stringList.size(); i++) {
        //     if (!stringList.get(i).equals("关之琳")) {
        //         stringList.add(i, "关之琳");
        //         break;
        //     }
        // }

        // 错误的案例演示：无法实现
        // for (String element : stringList) {
        //     stringList.remove(element);
        // }

        // 解决方案：
        removeElementForLoop(stringList, "王祖贤");
    }

    public void removeElementForLoop(List<String> stringList, String element) {
        /*
            使用Iterator类的remove方法，它在删除元素后会对expectedModCount重新赋值为modCount
            而且当集合还剩一个元素时，由于hasNext()返回false，next()不会执行，所以不会抛出并发修改异常。
            (多线程情况下不安全)
            说明一下为什么iterator.remove()在多线程下会出现异常 虽然我没测出来
            因为iterator的remove()调用的还是ArrayList的remove(),然后再对expectedModCount重新赋值为modCount，
            但是多线程环境下，由于线程抢占的问题，会出现删除了元素，但还没有重新对expectedModCount赋值，就被抢走了cpu执行权
            这时候另一个线程调用next()就会出现count不等的情况，出现异常。
         */
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.equals(element)) {
                iterator.remove();
            }
        }
        // 替代：
        // stringList.removeIf(s -> s.equals(element));

        // 2 定义一个新的集合保存要删除的元素，然后使用removeAll统一删除
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);
            if (value.equals(element)) {
                stringList.remove(i);
                // List集合底层为数组，删除元素，会导致后面的元素索引发生改变，需要控制，防止发生NullPointException
                i--;
            }
        }

        // 3 不用Iterator(线程安全)
        List<String> tempList = new ArrayList<>();
        for (String s : stringList) {
            if (s.equals(element)) {
                tempList.add(s);
            }
        }
        stringList.removeAll(tempList);


        // 4 使用线程安全的CopyOnWriteArrayList
        List<String> al2 = new CopyOnWriteArrayList<>();
        al2.add("hello");
        al2.add("world");
        al2.add("java");
        for (String s : al2) {
            if (s.equals("world")) {
                al2.remove(s);
            }
        }
        System.out.println(al2);
    }

    public void testRemoveRepeatElement() {
        List<Integer> integerList = Arrays.asList(12, 1341, 12341241, 24);

        List<Girl> girlList = initTestObjData();
        way_1(girlList);
        System.out.println("repeat girlList >> " + girlList);
        List<Girl> girlList2 = initTestObjData();
        way_2(girlList2);
        System.out.println("repeat girlList >> " + girlList2);

    }



    public void way_1(List<Girl> girlList) {
        // 定义一个新的集合用来保存不重复的元素，
        List<Girl> newList = new ArrayList<>();
        for (Girl next : girlList) {
            // 集合去重根据元素Girl中定义的equals
            if (!newList.contains(next)) {
                newList.add(next);
            }
        }
        girlList.clear();
        girlList.addAll(newList);
    }

    /**
     * 类似于选择排序，使每个元素分别和其它元素比较，相同的元素移除
     */
    public void way_2(List<Girl> girlList) {
        for (int i = 0; i < girlList.size() - 1; i++) {
            for (int j = i + 1; j < girlList.size(); j++) {
                if (girlList.get(i).equals(girlList.get(j))) {
                    girlList.remove(j);
                    // 元素被移除，索引发生改变
                    j--;
                }
            }
        }

    }

    public List<String> initTestData() {
        List<String> aList = new ArrayList();
        aList.add("刘亦菲");
        aList.add("邱淑贞");
        aList.add("王祖贤");
        aList.add("张曼玉");
        aList.add("李若彤");
        return aList;
    }

    public List<Girl> initTestObjData() {
        List<Girl> girlList = new ArrayList<>();
        girlList.add(Girl.builder().name("zhaomin").age(21).build());
        girlList.add(Girl.builder().name("zhaomin").age(22).build());
        girlList.add(Girl.builder().name("xiaozhao").age(21).build());
        girlList.add(Girl.builder().name("zhaomin").age(22).build());
        girlList.add(Girl.builder().name("zhouzhiruo").age(24).build());
        return girlList;
    }
}
