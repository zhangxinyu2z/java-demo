package com.xcoding.multithread.po.communication.case1;

// 根据顺序，奇数为圆，偶数为方
public class Producer implements Runnable {
    private Bun bun;
    private int num = 0; // 产生包子的顺序

    public Producer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bun) {
                if (bun.flag) {
                    try {
                        bun.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (num % 2 == 0) {
                    bun.setStuffing("rou");
                    bun.setShape("circle");
                } else {
                    bun.setStuffing("su");
                    bun.setShape("shape");
                }
                num++;
                bun.flag = true;
                bun.notify();
            }
        }
    }
}
