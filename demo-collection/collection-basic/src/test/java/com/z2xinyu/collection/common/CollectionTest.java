package com.z2xinyu.collection.common;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Collection是集合层次结构的根接口，有如下方法：
 * <ul>
 *     <li>
 * 1.增 {@link #testAddElement() }<br>
 * {@link Collection#add(Object)}<br/>
 * {@link Collection#addAll(Collection)}<br/>
 * </li>
 * <li>
 * 2.删 {@link #testDeleteElement()}<br>
 * {@link Collection#clear()}<br>
 * {@link Collection#removeAll(Collection)}<br>
 * </li>
 * <li>
 * 3.改 {@link #testModifyElement()}<br>
 * {@link Collection#toArray()}<br>
 * {@link Collection#toArray(Object[])}
 * </li>
 * <li>
 * 4.查 {@link #testGetElement()}<br>
 * {@link Collection#size()}<br>
 * 遍历：<br>
 * Iterator<E> iterator()<br>
 * boolean hasNext() next()返回一个元素，返回true <br>
 * E next() 返回下一个元素<br>
 * <br>
 * 取交集 <br>
 * {@link Collection#retainAll(Collection)}
 * </li>
 * <li>
 * 5.判断
 * {@link Collection#contains(Object)}
 * {@link Collection#containsAll(Collection)}
 * {@link Collection#equals(Object)}
 * {@link Collection#isEmpty()}
 * </li>
 *
 *  @author z-xy
 */
public class CollectionTest extends TestCase {

    public void testAddElement() {
        System.out.println("---添加元素");
        System.out.println("1、测试add()方法：");
        // 以ArrayList测试添加
        Collection c1 = new ArrayList();
        c1.add("source");
        c1.add('a');
        c1.add("ff");
        c1.add(20);
        // ArrayList重写了Collection的add方法，返回的永远是true,所以允许重复元素
        c1.add('a');
        c1.add('a');

        // 为什么printIn可以直接打印集合？
        // ArrayList继承类AbstractCollection,重写了toString()方法
        System.out.println("println c1：" + c1);

        System.out.println("2、测试addAll()方法：");
        Collection c2 = new ArrayList();
        c2.add('a');
        c2.add("yy");
        c2.add("hh");
        c2.add("mm");
        c2.add("ss");
        boolean result; // 只要不使用，compile不会报错
        // 把c2作为一个对象保存在了c1中
        result = c1.addAll(c2);
        System.out.println("addAll result：" + result + " \nprintln c1：" + c1);
    }

    public void testDeleteElement() {
        System.out.println("---删除元素");
        Collection c1 = new ArrayList();
        // 添加测试数据 start
        c1.add("a");
        c1.add("b");
        c1.add("c");
        // 添加测试数据 end

        System.out.println("1、clear()方法 > clear()会移除集合中所有的元素");
        System.out.println("before clear() c1: " + c1);
        c1.clear();
        System.out.println("after clear() c1: " + c1);

        System.out.println("2、remove()方法 > 只会移除第一个出现的相同的元素");

        // 添加测试数据 start
        c1.add("a");
        c1.add("b");
        c1.add("c");
        // 添加测试数据 end

        System.out.println("before remove() c1: " + c1);
        boolean removeResult = c1.remove("a");
        System.out.println("remove result:" + removeResult + "\nafter remove 'a', c1: " + c1);

        System.out.println("3、removeAll()方法 >> 只要移除一个相同的元素就会返回true,且会移除所有相同的元素");

        // 添加测试数据 start
        c1.add("a");
        c1.add("b");
        c1.add("c");
        // 添加测试数据 end

        System.out.println("before removeAll c1: " + c1);
        Collection c2 = new ArrayList();
        c2.add("b");

        System.out.println("c2: " + c2);
        boolean removeAllResult = c1.removeAll(c2);
        System.out.println("after remove c2 from c1: " + removeAllResult + "\nc1 removeAll(c2)" + c1);
    }

    public void testModifyElement() {
        System.out.println("---修改元素");
        Collection c1 = new ArrayList(Arrays.asList("b", "b", "b"));
        System.out.println("1、toArray()方法 >> 将集合转为数组");
        System.out.println("调用toArray()方法之前的c1: " + c1);
        Object[] c1Array = c1.toArray();
        System.out.println("输出转换后的元素：" + Arrays.toString(c1Array));

        System.out.println("2、toArray(T)方法 >> 将集合转为数组");
        List<Integer> c2 = Arrays.asList(13,131,4124,12);
        Integer[] numList = new Integer[c2.size()];
        c2.toArray(numList);
        System.out.println("输出转换后的元素：" + Arrays.toString(numList));

    }

    public void testGetElement() {
        System.out.println("--获取元素");
        Collection c1 = new ArrayList(Arrays.asList("a", "b", "c"));
        System.out.println("1、iterator方法 >>使用迭代器来遍历集合");
        // 迭代器：遍历集合的一种方式，依赖集合而存在
        Iterator it = c1.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            System.out.print(obj + " ");
        }

        // 这种的效率会高些
        //        for (Iterator it = c1.iterator(); it.hasNext();) {
        //            Object obj = it.next();
        //            System.out.print(obj + " ");
        //        }

        // way 2
        //        for (; l.hasNext();) {
        //            System.out.print(l.next() + ",");
        //        }

        System.out.println("2、list集合的listIterator方法 >> 提供了previous方法");
        ArrayList li = (ArrayList)c1;
        ListIterator l = li.listIterator();
        try {
            // previous光标是往前移的，光标默认在第一个元素之前，所以再往前没有元素
            // java.util.NoSuchElementException
            System.out.println(l.previous());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        // hasPrevious：光标前是否还有元素
        for (; l.hasPrevious(); ) {
            System.out.print(l.previous() + ",");
        }

        System.out.println(
            "3、retainAll()方法 >> " + "A retain B，如果AB集合有相同的元素，A保留和B集合相同的元素(重复元素不会去重)，且只有A集合的元素发生了改变，才会返回true");
        Collection c3 = new ArrayList(Arrays.asList("b", "b", "b"));
        Collection c4 = new ArrayList(Arrays.asList("b", "b", "b"));
        System.out.println("c3 = " + c3 + ", c4 = " + c4);
        boolean retainResult = c3.retainAll(c4);

        System.out.println("c3 retain c4, result:" + retainResult + ", 元素相同，但是c3的元素未发生改变");
    }

    public void testDecide() {
        System.out.println("---判断");
        System.out.println("1、contains方法 >> 集合是否存在元素");
        Collection c1 = new ArrayList(Arrays.asList("a", "b", "c"));
        System.out.println("c1: " + c1);
        // 底程使用  equals()
        boolean b = c1.contains("飞鸟");
        System.out.println("contains \"飞鸟\", result: " + b);

        System.out.println("2、containsAll方法 >> 集合A是否包含集合B所有元素,包含所有的元素才会返回true");
        Collection c2 = new ArrayList(Arrays.asList("b", "f"));
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        b = c1.containsAll(c2);
        System.out.println("c1 containsAll c2, result :" + b);

        System.out.println("3、equals()方法 >> 比较集合中的元素，是否重写toString方法，元素为对象则用==");
        //         继承AbstractList类，重写的equals()方法，
        //         1、参数不是List类型，返回false
        //         2、参数是String，调用String重写的equals()方法比较字符序列
        //         3、参数是对象，用的是==
        List<String> c3 = new ArrayList<>();
        List<String> c4 = new ArrayList<>();
        boolean eq = c3.equals(c4);
        System.out.println("c3 equals c4, result: " + eq);

        System.out.println("4、isEmpty()方法 >> 判断集合元素是否为空");
        System.out.println("c1 isEmpty: " + c1.isEmpty());
    }

}
