package com.z2xinyu.collection.map;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * HashMap通过hashCode和equals来保证键值的唯一性
 * <p>
 * HashMap允许键值为null,但是key不能重复
 *
 *
 * @author zhangxinyu
 * @date 2023/8/15
 **/
public class HashMapTest {

    @Test
    public void testInitCapacity() {
        String product = "Booth No. dadfasf";
        int startIndex = "Booth No. ".length(); // 获取子字符串的起始索引
        String substring = product.substring(startIndex);

        // 元素数量
        int numElements = 2;
        // 负载因子
        float loadFactor = 0.75f;
        int initialCapacity = (int) (numElements / loadFactor) + 1;

        System.out.println("初始容量为：" + initialCapacity); // 3
    }

    public static void main(String[] args) {
        HashMap hm = new HashMap();
        hm.entrySet();
        Object objx = hm.put("k", "value");
        hm.put("k", "value2");
        hm.entrySet();
        hm.put(null, null);
        hm.put(null, null);
        hm.put("k1", null);
        hm.put(null, "hello");
        System.out.println(hm);

        HashMap hm2 = new HashMap(16);
        hm2.put("k", "value3");
        hm2.put("k2", "value3");
        hm.putAll(hm2);
        System.out.println("hm:" + hm);

        // 获取元素
        Object value = hm.get("k");
        System.out.println(value);
        System.out.println(hm.size());

        // 遍历的两种方式
        // entrySet返回一个键值对映射
        Set set = hm.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ",");
        }
        System.out.println();
        // 2、
        Set keySet = hm.keySet();
        for (Object o : keySet) {
            System.out.print("key:" + o + ";value:" + hm.get(o) + ",\n");
        }
        // values
        Collection col = hm.values();
        System.out.println(col);

        // 删除元素
        //        hm.clear();
        //        System.out.println("hm:"+ hm);

        // 移除键值
        Object value1 = hm.remove("k");
        System.out.println(value1);
        System.out.println("hm:" + hm);

        // 当且仅当键值相同才能移除
        boolean result = hm.remove(null, "value");
        System.out.println(result);
        System.out.println("hm:" + hm);

        // 修改元素
        //        Object rv = hm.replace("k1","what");
        Object rv = hm.replace("k3", "what");
        System.out.println("rv:" + rv);
        System.out.println("hm" + hm);

        // 修改
        rv = hm.replace(null, "world", "world");
        System.out.println("rv:" + rv);
        System.out.println("hm" + hm);

        // 判断
        boolean c = hm.containsKey("k1");
        System.out.println("containskey:" + c);
        c = hm.containsValue("value3");
        System.out.println("containsValue:" + c);

    }
}
