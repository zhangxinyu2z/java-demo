package com.z2xinyu.collection.list;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class VectorTest {
    {
        Vector v = new Vector();

        // 获取vector集合的初始容量  10
        System.out.println(v.capacity());

        v.addElement("hello");
        v.add(12);
        // v:[hello, 12]
        System.out.println("v:" + v);
        // capacity:10
        System.out.println("capacity:" + v.capacity());
        // size:2
        System.out.println("size:" + v.size());

        // 获取元素
        Object element = v.elementAt(0);
        // hello
        System.out.println(element);

        // 遍历
        for (Enumeration elements = v.elements(); elements.hasMoreElements(); ) {
            System.out.println(elements.nextElement());
        }

        //        Object[] obj = new Object[12];
        // 数组元素类型不一致
        String[] obj = new String[12];
        // System.arraycopy()方法
        v.copyInto(obj);
        for (Object o : obj) {
            // ArrayStoreException
            System.out.println(o);
        }
    }
}
