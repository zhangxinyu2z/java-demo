package com.xcoding.multithread.po.cn.xy.thread.create;

/**
 * 创建线程的第二种方式：实现runnable接口
 *
 * @author z-xy
 */
public class CreateThread2 implements Runnable {
    @Override
    public void run() {
        // 获取当前线程的名称
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new Thread(new CreateThread2()).start();
        System.out.println(Thread.currentThread().getName());
    }
}