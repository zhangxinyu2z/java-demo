package com.xcoding.multithread.po.communication.case2;

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
            if (num % 2 == 0) {
                bun.set("rou", "circle");
            } else {
                bun.set("su", "shape");
            }
            num++;
        }
    }
}

