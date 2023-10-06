package com.z2xinyu.collection.set;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * LinkedHashSet由哈希表和链表组成
 * 哈希表保证元素唯一性,链表保证元素有序（存储和取出一致）：hashCode()和equals() > put(K key, V value)
 *
 * @author zhangxinyu
 * @date 2023/7/10
 **/
public class LinkedHashSetTest{

    @Test
    public void add() {
        HashSet<Object> hs = new LinkedHashSet();
        hs.add("helo");
        hs.add(null);
        hs.add("12");
        hs.add(33);
        hs.add('a');

        System.out.println(hs);
    }

}
