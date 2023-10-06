package com.xcoding.multithread.po.cn.xy.thread.create;

public class CreateThreadGroup {
    public static void main(String[] args) {
        Runnable g = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        // 线程t1,起名字
        Thread t1 = new Thread(g, "t1");
        // 线程t2
        Thread t2 = new Thread(g, "t2");

        // 默认和主线程是一个组的，都是main
        System.out.println(t1.getThreadGroup().getName());
        System.out.println(t2.getThreadGroup().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());

        // 创建一个线程组
        ThreadGroup tg = new ThreadGroup("tg");
        // 创建t3,t4线程的同时，添加到tg线程组中
        Thread t3 = new Thread(tg, g, "t3");
        Thread t4 = new Thread(tg, g, "t4");
        System.out.println(t3.getThreadGroup().getName());
    }
}

