package com.xcoding.multithread.po.cases.saleticket;

import java.util.concurrent.TimeUnit;
/**
 * 判断一个程序是否会有线程安全问题的标准
 * A:是否是多线程环境
 * B:是否有共享数据
 * C:是否有多条语句操作共享数据
 * 如何解决：
 * 把多条语句操作共享数据的代码给包成一个整体，让某个线程在执行的时候，别人不能来执行。
 * 关键就在于对象锁上，所有对象共享一个锁（同步机制）
 */
public class SaleTicketSynTest {
    public static void main(String[] args) {
        SaleTicketSyn s = new SaleTicketSyn();
        Thread t1 = new Thread(s, "窗口1");
        Thread t2 = new Thread(s, "窗口2");
        t1.start();
        t2.start();
    }
}

class SaleTicketSyn implements Runnable {
    // 票数
    private int num = 100; // 共享资源
    private Object obj = new Object();

    // 多线程
    @Override
    public void run() {
        // 有票就一直卖
        while (true) {
            synchronized (obj) { // 对象锁，同一个
                if (num >0) {
                    // 模拟一下出票的等待时间
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 非原子性，操作共享数据多次
                    System.out.println(Thread.currentThread().getName()
                            + "正在售出第" + num-- + "张票");
                }
            }
        }
    }
}
