package com.xcoding.multithread.po.cases.saleticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 相比synchronized,Lock可以更清晰的表达如何释放锁和加锁
 */
public class SaleTicketLockTest {
    public static void main(String[] args) {
        SaleTicketLock sl = new SaleTicketLock();
        new Thread(sl, "窗口1").start();
        new Thread(sl, "窗口2").start();
    }
}

class SaleTicketLock implements Runnable {
    private Lock lock = new ReentrantLock();
    // 票数
    private int num = 100; // 共享资源

    // 多线程
    @Override
    public void run() {
        // 有票就一直卖
        while (true) {
            try {
                lock.lock(); // 防止异常无法释放锁
                if (num > 0) {
                    // 模拟一下出票的等待时间
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 非原子性，操作共享数据多次
                    System.out.println(Thread.currentThread().getName()
                            + "正在售出第" + (num--) + "张票");
                }
            } finally {
                lock.unlock(); // 释放锁
            }
        }
    }
}



