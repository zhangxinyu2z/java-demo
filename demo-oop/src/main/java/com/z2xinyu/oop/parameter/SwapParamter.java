package com.z2xinyu.oop.parameter;

import java.lang.reflect.Field;

/**
 * 基本类型：形参的改变不影响实际参数
 * 引用类型：形参的改变直接影响实参数,[如果一个方法中形参是类类型（引用类型），那么这个方法实际传递的是这个类的对象的地址值
 *
 * @author zxy
 */
public class SwapParamter {
    Integer c = 0;

    public static void main(String[] args) {
//        Integer x = new Integer(2);
        // 底层是Integer.valueOf(1),这个方法在-128到127是从缓冲池中拿的数据
        Integer a = 1;
        Integer b = 2;
//        System.out.println("a=" + a + ",b=" + b); // 1 2
        swap(a, b);
        swap2(a, b);
        // 1 2
        System.out.println("a=" + a + ",b=" + b);

//        FormalParameter fp = new FormalParameter();
//        fp.show();
//        System.out.println(fp.c); // 2
    }

    public void show() {
        c++; // 2
    }

    private static void swap(Integer a, Integer b) {
        // 可见这种方法无法改变Integer的值，那么要如何实现？
        /*方法中的变量获得是主方法中地址值的拷贝，这里的变量a,b和主方法中的变量a,b并没有关系
        这里进行交换也只是改变的swap方法中变量指向的地址，方法结束后，这两个变量就释放了
         */
        Integer temp = a;
        a = b;
        b = temp;

        a = a + 3;
        // Integer是一个对象，Integer中的value是private final,Integer没有提供set方法修改，final修饰的value也无法改变值
        // Integer a = Integer.valueOf(a.intValue() + 3);
        a += 3;
    }

    public static void swap2(Integer a, Integer b) {
    /*
    修改Integer内部的value值
    参考：https://www.cnblogs.com/xuxinstyle/p/9672958.html
     */
//        Integer temp = a; // 输出结果为2，2 ,因为temp指向a,这时候a已经为2下·
        int temp = a.intValue();
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            Object o = field.get(a);

            field.set(a, b);

            // 1
            System.out.println(temp);
            // 2
            System.out.println(Integer.valueOf(temp));
            /*
            这里是Integer缓冲池的问题
            开始缓冲的是 cache[-128] = 0 cache[129] = 1 cache[130] = 2
            set(a,b)之后是  cache[-128] = 0 cache[129] = 2 cache[130] = 2
            temp对应的是129,所以b是2
             */
            field.set(b, temp);
//            field.set(b,new Integer(temp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

