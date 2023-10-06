package com.z2xinyu.collection.list;

import com.z2xinyu.collection.po.Girl;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class ArrayListTest extends TestCase {

    {
        System.out.println("测试add方法");
        // 1、 void add(int index,E element) 在指定位置添加元素
        List arrayList = new ArrayList();
        System.out.println(arrayList.size());
        // arrayList.add(0, "hello");
        // arrayList.add(1, "world"); // IndexOutOfBoundsException，集合中没有元素时只能添加index=0,不能超出集合的size
        // arrayList.add(4,"demo");
        // System.out.println(arrayList);

        System.out.println("测试get方法");
        // 2、 E get(int index) 返回此列表中指定位置的元素。
        List aList = new ArrayList();
        aList.add("刘亦菲");
        aList.add("邱淑贞");
        aList.add("王祖贤");
        aList.add("张曼玉");
        aList.add("李若彤");

        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i));
        }
        System.out.println("-------------------------------------------");
        System.out.println("测试indexOf方法");
        // 3、 int indexOf(Object o) 返回此列表中指定元素的第一个出现的索引
        int hello = aList.indexOf("Hello"); // -1
        int lrt = aList.indexOf("李若彤");
        System.out.println(hello);
        System.out.println(lrt); // 4
        System.out.println("-------------------------------------------");

        System.out.println("测试listIteraor方法");
        // 4、 在列表中的元素上返回列表迭代器
        ListIterator listIterator = aList.listIterator();
        while (listIterator.hasNext()) {
            // aList.add("张柏芝");
            String str = (String)(listIterator.next());
            System.out.println(str);
        }
        // listIterator的倒着遍历没有意义，因为只有必须要光标移动到最后一位才能开始
        /*while(listIterator.hasPrevious()) {
        	String str = (String)(listIterator.previous());
        	System.out.println(str);
        }*/
        System.out.println("-------------------------------------------");
        // 5、 E remove(int index) 移除此列表中指定位置的元素
        System.out.println(aList.toString());
        aList.remove(2);
        System.out.println(aList.toString());

        System.out.println("-------------------------------------------");
        System.out.println("测试set方法");
        // 6、E set(int index,E element) 用指定元素替换此列表中指定位置的元素
        System.out.println(aList.toString());
        Object o = aList.set(1, "翁美玲");
        System.out.println(o);
        System.out.println(aList.toString());

        // 7、List<E> subList(int fromIndex,int toIndex) 对子集合的操作会影响原集合
        System.out.println("-------------------------------------------");
        System.out.println("原：" + aList.toString()); // 原：[刘亦菲, 翁美玲, 张曼玉, 李若彤]
        List list = aList.subList(0, 2);
        list.clear();
        System.out.println("list:" + list.toString());
        System.out.println("操作后：" + aList.toString()); // 操作后：[张曼玉, 李若彤]
        System.out.println("-------------------------------------------");
        // 8、实现一个需求 判断里面有没有"关之琳"这个元素，如果没有，我就添加
        System.out.println(aList);
        /*
        for(Iterator i= aList.iterator(); i.hasNext();) {
        	String  e = (String)i.next();
        	if(!e.equals("关之琳")) {		// 无法实现
        		aList.add("关之琳");	// ConcurrentModificationException
        	}
        }
        */
        // 第一种，使用迭代器自己的方法，添加的位置就是指针的位置
        /*
        for(ListIterator i = aList.listIterator(); i.hasNext();) {
        	String  e = (String)i.next();
        	if(!e.equals("关之琳")) {
        		i.add("关之琳");
        		break;
        	}
        }
        */
        // 第二种
        for (int i = 0; i < aList.size(); i++) {
            if (!aList.get(i).equals("关之琳")) {
                aList.add(i, "关之琳");
                break;
            }
        }
        System.out.println(aList);

    }

    public void testRemoveRepeatElement() {
        List<Integer> integerList = Arrays.asList(12, 1341, 12341241, 24);


        List<Girl> girlList = initTestData();
        way_1(girlList);
        System.out.println("repeat girlList >> " + girlList);
        List<Girl> girlList2 = initTestData();
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
     *
     * @param girlList
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

    public List<Girl> initTestData() {
        List<Girl> girlList = new ArrayList<>();
        girlList.add(Girl.builder().name("zhaomin").age(21).build());
        girlList.add(Girl.builder().name("zhaomin").age(22).build());
        girlList.add(Girl.builder().name("xiaozhao").age(21).build());
        girlList.add(Girl.builder().name("zhaomin").age(22).build());
        girlList.add(Girl.builder().name("zhouzhiruo").age(24).build());
        return girlList;
    }
}
