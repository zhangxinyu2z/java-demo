package com.z2xinyu.collection.api;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import junit.framework.TestCase;
import org.apache.commons.collections4.IteratorUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangxinyu
 * @date 2023/7/3
 **/
public class StructureConvertTest extends TestCase {

    /**
     * Iterator<Object>转换成Set<String>{@link #testIteratorConvertToSet()}
     */
    public void testIteratorConvertToSet() {
        List<Object> testSample = new ArrayList<>();
        testSample.add("fruit tea");
        testSample.add("juice tea");
        testSample.add(100);

        // testSample.add(100);
        Iterator<Object> iterator = testSample.iterator();
        Set<String> result = new HashSet<>();
        while (iterator.hasNext()) {
            Object a = iterator.next();
            if (a instanceof String) {
                result.add((String)a);
            }
        }
        System.out.println(result);

        // guava
        HashSet<String> strings = Sets.newHashSet(Iterators.filter(testSample.iterator(), String.class));
        System.out.println(strings);

        // collection4  只能转换结构，不能改变内部元素的类型
        HashSet<Object> objects = new HashSet<>(IteratorUtils.toList(testSample.iterator()));
        // 另一种方式
        Set<String> stringSets =
            IteratorUtils.toList(testSample.iterator()).stream().map(Object::toString).collect(Collectors.toSet());
        System.out.println(stringSets);
    }
}