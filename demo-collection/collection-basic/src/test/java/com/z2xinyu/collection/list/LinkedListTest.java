package com.z2xinyu.collection.list;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * 队列LinkedList,对以下方法进行验证
 * <ul>
 *     <li>
 *         1. 增 {@link #testAdd()}<br>
 *         添加元素到末尾<br>
 *         {@link LinkedList#add(Object)}<br>
 *         {@link LinkedList#addLast(Object)}<br>
 *         {@link LinkedList#offer(Object)}<br>
 *         {@link LinkedList#offerLast(Object)}<br>
 *         添加元素到首<br>
 *         {@link LinkedList#addFirst(Object)}<br>
 *         {@link LinkedList#push(Object)}<br>
 *         {@link LinkedList#offerFirst(Object)}<br>
 *         添加到指定节点<br>
 *         {@link LinkedList#add(int, Object)}<br>
 *     </li>
 *     <li>
 *         2. 删 {@link #testRemove()}<br>
 *         移除并返回指定索引元素<br>
 *         {@link LinkedList#remove(int)}<br>
 *         移除并返回第一个元素<br>
 *         {@link LinkedList#poll()}<br>
 *         {@link LinkedList#pollFirst()}<br>
 *         移除并返回第一个元素,无元素抛出{@code NoSuchElementException}<br>
 *         {@link LinkedList#removeFirst()}<br>
 *         {@link LinkedList#remove()}<br>
 *         {@link LinkedList#pop()}<br>
 *         移除第一个匹配的元素，返回操作结果<br>
 *         {@link LinkedList#remove(Object)}<br>
 *         {@link LinkedList#removeFirstOccurrence(Object)}<br>
 *         <br>
 *         {@link LinkedList#removeIf(Predicate)}<br>
 *         {@link LinkedList#removeAll(Collection)}<br>
 *         {@link LinkedList#clear()}<br>
 *     </li>
 *     <li>
 *
 *     </li>
 *     <li>
 *         4. 查<br>
 *         获取指定索引元素<br>
 *         {@link LinkedList#get(int)}<br>
 *         获取列表第一个元素<br>
 *         {@link LinkedList#element()}<br>
 *         {@link LinkedList#getFirst()}<br>
 *         获取列表第一个元素，否则抛出{@code NoSuchElementException}<br>
 *         {@link LinkedList#peek()}<br>
 *         {@link LinkedList#peekFirst()}<br>
 *         获取列表最后一个元素<br>
 *         {@link LinkedList#getLast()}<br>
 *         {@link LinkedList#peekLast()}<br>
 *     </li>
 * </ul>
 *
 *
 *
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class LinkedListTest extends TestCase {

    public void testAdd() {
        LinkedList<String> ll = new LinkedList<>();
        // 添加元素到末尾
        ll.add("a");
        ll.add("a");
        ll.add("b");
        ll.add(null);
        System.out.println("ll >> " + ll);

        ll.add(2, "f");

        // 添加元素到首部
        ll.addFirst("apple");
        System.out.println("ll addFirst apple >> " + ll);

        // push调用addFirst
        ll.push("orange");
        System.out.println("ll push orange >> " + ll);

        // 添加元素到尾部，调用add
        ll.offer("banana");
        System.out.println("ll offer banana >> " + ll);
        // 调用addFirst
        boolean beer = ll.offerFirst("beer");
        System.out.println("ll offerFirst beer >> " + ll);
    }

    public void testRemove() {
        LinkedList<String> initData = initTestData();

        // 删除容器中第一个元素并返回，没有返回null
        System.out.println("invoke poll before >>" + initData);
        Object poll = initData.poll();
        System.out.println("=======invoke poll end >> " + initData + ", remove first: " + poll);
        // 同poll
        System.out.println("invoke pollFirst before >>" + initData);
        Object pollFirst = initData.pollFirst();
        System.out.println("=======invoke pollFirst end >> " + initData + ", remove first: " + pollFirst);

        // 删除容器中第一个元素并返回，没有抛出NoSuchElementException
        try {
            System.out.println("invoke removeFirst before >>" + initData);
            String first = initData.removeFirst();
            System.out.println("=======invoke removeFirst end >> " + initData + ", remove first: " + first);

            // 同removeFirst，列表没有元素抛异常
            String remove = initData.remove();
            Object pop = initData.pop();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        // remove(Object obj)

        // 删除列表中第一次出现的指定元素，注：重复的只会删除一个
        System.out.println("invoke removeFirstOccurrence before >>" + initData);
        boolean orange = initData.removeFirstOccurrence("orange");
        System.out.println("=======invoke removeFirstOccurrence end >> " + initData + ", remove element: " + orange);

        // 如果列表存在该元素，删除
        System.out.println("invoke removeIf before >>" + initData);
        boolean b = initData.removeIf("apple"::equals);
        System.out.println("=======invoke removeIf end >> " + initData + ", remove result: " + b);


        // removeAll

        // 清空容器中的元素
        LinkedList<String> strings = initTestData();
        strings.clear();
        System.out.println("======initData: " + initData);
    }

    public void testGet() {
        LinkedList<String> linkedList = initTestData();
        System.out.println(linkedList);

        // 同getFirst()，获取列表第一个元素，这两个方法fist为null，抛出异常NoSuchElementException
        Object e = linkedList.element();
        Object getFirst = linkedList.getFirst();
        System.out.println("element: " + e + ", getFirst:" + getFirst);

        // 同getFirst，区别：fist为null，返回null
        Object e1 = linkedList.peek();
        Object eFirst = linkedList.peekFirst();
        System.out.println("peek: " + e1 + ", peekFirst: " + eFirst);

        // 获取列表最后一个元素，last为null,抛出NoSuchElementException
        Object getLast = linkedList.getLast();
        System.out.println("getLast:" + getLast);
        // 同getLast，但last=null,返回null
        Object peekLast = linkedList.peekLast();
        System.out.println("peekLast:" + peekLast);
    }

    public LinkedList<String> initTestData() {
        LinkedList<String> data = new LinkedList<>();
        data.addFirst("apple");
        data.push("orange");
        data.push("orange");
        data.push("orange");
        data.offer("banana");
        data.offerFirst("beer");
        data.offerFirst("beer");
        data.offerFirst("beer");
        data.offerFirst("beer");
        return data;
    }

    {
        java.util.LinkedList ll = new java.util.LinkedList();
        ll.add("a");
        ll.add("a");
        ll.add("b");
        ll.add(null);




    }
}
