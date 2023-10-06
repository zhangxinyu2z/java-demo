package com.xcoding.multithread.po.communication.case2;

// 消费者
public class Consumer implements Runnable {
    private Bun bun;

    public Consumer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        while (true) {
            bun.get();
        }
    }
}
