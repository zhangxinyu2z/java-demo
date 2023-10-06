package com.xcoding.multithread.po.deadlock;

/**
 * 死锁的出现是互相暂用对方的资源，无法释放
 * 两个或两个以上的线程在执行过程中，因争夺资源产生的一种相互等待的情况
 */
public class DeadLockTest {
    public static void main(String[] args) {
        MyThread my1 = new MyThread(true);
        MyThread my2 = new MyThread(false);

        my1.start();
        my2.start();

    }
}

class MyThread extends Thread {
    private boolean flag;
    public MyThread(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (LockObj.lockA) {
                System.out.println("if lockA");
                synchronized (LockObj.lockB) {
                    System.out.println("if lockB");
                }
            }
        } else {
            synchronized (LockObj.lockB) {
                System.out.println("else lockA");
                synchronized (LockObj.lockA) {
                    System.out.println("else lockB");
                }
            }
        }
    }
}

class LockObj {
    public static Object lockA = new Object();
    public static Object lockB = new Object();
}
