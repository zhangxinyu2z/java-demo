package com.xcoding.multithread.po.communication.case2;

public class Bun {
    private String stuffing; // 馅
    private String shape; // 形状
    boolean flag; // false

    public Bun() {}

    public synchronized void set(String stuffing, String shape) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stuffing = stuffing;
        this.shape= shape;
        this.flag = true;
        this.notify();
    }

    public synchronized void get() {
        if(!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.stuffing + ":" + this.shape);
        this.flag = false;
        this.notify();
    }


}
