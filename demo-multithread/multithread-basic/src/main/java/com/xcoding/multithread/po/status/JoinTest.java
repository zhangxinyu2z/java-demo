package com.xcoding.multithread.po.status;

/**
 * join: 指定线程死亡后，才会执行下一个线程,会导致主线程也随着死亡
 */
class MyThreadJoin extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        MyThreadJoin m1 = new MyThreadJoin();
        MyThreadJoin m2 = new MyThreadJoin();
        MyThreadJoin m3 = new MyThreadJoin();
        m1.setName("刘备");
        m2.setName("关羽");
        m3.setName("张飞");

        m1.start();
        try {
            m1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        m2.start();

        m3.start();
/**
 * 测试发现，主线程随着m1.join的结束后，主线程随着结束
 * 注意:线程之间是独立互不影响的
 * 主线程不会在执行完它的所有代码后死亡，而是会等join线程死亡后死亡,其他线程不变
 */
        for (int i = 0; i < 300; i++) {
            System.out.print(i);
        }
        System.out.println();
        //
        m3.join();

        System.out.println("main end");
    }
}