package com.z2xinyu.optional;

import java.util.Optional;

/**
 * java.util.Optional
 *
 * document reference：https://blog.csdn.net/lijingronghcit/article/details/106601934
 *
 * @author zhangxinyu
 * @since 2022/6/6 20:09
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Integer i = new Integer(2);

        // 在方法内部，是值传递，不能改变原始的数据
        Optional.ofNullable(i).ifPresent(t -> t = 3);

        System.out.println("i = " + i);

        User user = new User(1);
        // 同样的，在方法内部，只是让方法内的参数指向了另一个对象引用，但是原来的变量还是指向开始的对象。
        Optional.ofNullable(user).ifPresent(t -> t = new User(2));
        System.out.println(user.getAge());



    }
}
