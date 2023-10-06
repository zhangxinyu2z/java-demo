package com.xcoding.multithread.po.cn.xy.thread.create;

/**
 * 创建线程的第一种方式
 * <p>
 * start是开启一个线程的方法
 *
 * @author z-xy
 */
public class CreateThread1 extends Thread {
    @Override
    public void run() {
        // run方法是线程运行的代码
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // 主线程
        System.out.println(Thread.currentThread().getName());
        // 新建一个线程
        CreateThread1 my = new CreateThread1();
        // 开启线程，处于可运行状态，cpu执行权抢占后，开始执行
        my.start();
    }
}

