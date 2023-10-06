package com.z2xinyu.jdbc.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal可以处理多线程并发问题
 * 原理：通过ThreadLocal存储的数据，它们的key都是当前线程对象，访问的数据是相互独立的，
 * <p>
 * 首先，在jdbc事务中，一个原子性操作应该只有一个connection，这样是为了保证数据不一致的问题，张三给王五转账，李四给赵六转账，这不可能是一个事务
 * <p>
 * 一个线程保证了一个Connection，但是也可能出现并发访问的问题，一种可能：事务被一个线程提交，但是原子性操作还未完成
 *
 * @author zhang xinyu
 * @version v1.0
 * @date created in 2021-05-18 11:09
 */
public class MyThreadLocal<T> {
    /**
     * 使用线程对象作键，来存储数据
     */
    private Map<Thread, T> hashMap = new HashMap<>();

    public T get() {
        return hashMap.get(Thread.currentThread());
    }

    public void set(T t) {
        hashMap.put(Thread.currentThread(), t);
    }

    public void remove() {
        hashMap.remove(Thread.currentThread());
    }
}
