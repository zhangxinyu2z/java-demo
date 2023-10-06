package com.xcoding.multithread.po.cases.saleticket;

import java.util.concurrent.TimeUnit;

public class SaleTicketTest {
    public static void main(String[] args) {
        SaleTicket s = new SaleTicket();
        Thread t1 = new Thread(s,"窗口1");
        Thread t2 = new Thread(s,"窗口2");

        t1.start();
        t2.start();
        /**
         * 模拟出现的问题:
         * 1、出现重复的票：窗口1阻塞的时候，窗口2进入，但是还没打印，窗口1抢到了执行权，num--出现同票
         * CPU的一次操作必须是原子性的 num--  相当于num = num -1
         * 2、售票的顺序：票号97还没售，96已经卖掉了  num减掉票数的时候被抢去了执行权
         * 出现了负数票
     *  		随机性和延迟导致的
         */
    }

}

class SaleTicket implements Runnable {
    // 票数
    private int num = 100;

    @Override
    public void run() {
        // 有票就一直卖
        while (num > 0) {
            // 模拟一下出票的等待时间
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在售出第" + num-- + "张票");
        }
    }
}
