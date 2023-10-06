package com.xcoding.multithread.po.status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep(0)触发操作系统立刻重新进行一次CPU竞争
 * 休眠等于释放cpu控制权，休眠结束后，还需要重新竞争
 */
class MyThreadSleep implements Runnable{
    @Override
    public void run() {
        for (int i = 0 ; i < 1; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + ":" + Thread.currentThread().getName());
        }
    }
}

public class SleepTest {
    public static void main(String[] args) {
        MyThreadSleep s = new MyThreadSleep();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        t1.setName("刘备");
        t2.setName("张飞");

        t1.start();
//        t2.start();

        try {
            Thread.sleep(600);
            t1.interrupt();
//            t1.stop(); // 过时，不会继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
        get(10000);
    }

    // 计时器模拟
     public static void get(long mills) {
         Date d = new Date(System.currentTimeMillis()+mills);
         long endtime = d.getTime();

         while (true) {
             System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
             d = new Date(d.getTime() - 1000);
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

             if (endtime - d.getTime() > mills) {
                 break;
             }
         }
     }
}
