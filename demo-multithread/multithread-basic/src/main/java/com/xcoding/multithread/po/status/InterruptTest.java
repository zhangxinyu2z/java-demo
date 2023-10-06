package com.xcoding.multithread.po.status;

/**
 * 中断线程
 * <p>
 * interrupt（）是给线程设置中断标志；TERMINATED时，会清除掉thread.interrupt()设置的中断状态！
 * interrupted（）是检测中断并清除中断状态,作用于当前所在线程
 * isInterrupted（）只检测中断，方法调用者所在线程
 * 根据中断标志的具体值，来决定如何退出线程
 * <p>
 * stop方法会终止此线程，不会执行当前线程接下来的代码
 * <p>
 * 一个线程在运行状态中，其中断标志被设置为true,一旦线程调用了wait、join、sleep方法中的一种，立马抛出一个InterruptedException，且中断标志被清除，重新设置为false。
 *
 * @author z-xy
 */
public class InterruptTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        // 开启线程t1
        t1.start();
        System.out.println("main thread");
//        Thread.sleep(10);
//        t1.stop();
//        my2.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("i = " + i);
            try {
                sleep(50);
                interrupt();
//                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.isInterrupted()) {
                System.out.println("interrput");
//                break;
            }
        }
        System.out.println(this.isInterrupted() + ":::因为检测到中断，所以跳出循环，线程到这里结束，因为后面没有内容了");
    }
}