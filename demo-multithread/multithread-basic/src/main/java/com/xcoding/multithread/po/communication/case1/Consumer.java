package com.xcoding.multithread.po.communication.case1;

// 消费者
public class Consumer implements Runnable{
    private Bun bun;

    public Consumer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bun) {
                if (!bun.flag) {
                    try {
                        bun.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(bun.getStuffing() + ":" + bun.getShape());
                bun.flag = false;
                bun.notify();
            }
        }
    }
}