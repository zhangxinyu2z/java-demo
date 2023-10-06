package com.xcoding.multithread.po.cases.saleticket;

/**
 * 出现问题，因为同步方法的锁对象是调用对象本身:this
 * 如果是静态同步方法，对象锁是：Class文件对象
 */
public class SaleTicketSynMethodTest {
    public static void main(String[] args) {
        SaleTicketSynMethod s = new SaleTicketSynMethod();
        new Thread(s, "窗口1").start();
        new Thread(s, "窗口2").start();
    }
}

class SaleTicketSynMethod implements Runnable {
    // 票数
    private static int num = 100; // 共享资源
    private Object obj = new Object();
    int flag = 0;
    // 多线程
    @Override
    public void run() {
        // 有票就一直卖
        while (true) {
            if (flag % 2 == 0) {
                if (num > 0) {
                    synchronized (SaleTicketSynMethod.class) { // 对象锁，同一个
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
                }
            } else  {
                saleTicket();
            }
            flag++;
        }
    }

    private static synchronized void saleTicket() {
        if (num > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 非原子性，操作共享数据多次
            System.out.println(Thread.currentThread().getName()
                    + "正在售出第" + (num--) + "张票");
        }
    }
}

