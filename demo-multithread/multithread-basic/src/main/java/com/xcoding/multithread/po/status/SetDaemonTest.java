package com.xcoding.multithread.po.status;

import java.util.concurrent.TimeUnit;

/**
 * 当 JVM 中不存在任何一个正在运行的非守护线程时，则 JVM 进程即会退出。
 * 守护线程拥有自动结束自己生命周期的特性，而非守护线程不具备这个特点。
 * 应用场景：守护线程经常被用来执行一些后台任务，但是呢，你又希望在程序退出时，
 * 或者说 JVM 退出时，线程能够自动关闭
 */
// https://blog.csdn.net/weiwosuoai/article/details/89387280
public class SetDaemonTest {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("the hook thread is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the hook thread will exit");
        }));

        Thread t = new Thread() {
            @Override
            public void run() {
                // 模拟非守护线程不退出的情况。
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("i'm running");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t.setDaemon(true);// 这时设置守护线程
        t.start(); // jvm无法退出

        System.out.println("the main thread will exit");
    }
}

class MyThreadDaemon extends Thread {
    public MyThreadDaemon(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            System.out.println(this.getName() + ":" + i);
        }
    }
}


