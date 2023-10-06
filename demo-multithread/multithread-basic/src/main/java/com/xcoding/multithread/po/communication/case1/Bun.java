package com.xcoding.multithread.po.communication.case1;

public class Bun {
    private String stuffing; // 馅
    private String shape; // 形状
    boolean flag; // false

    public Bun() {}

    public Bun(String stuffing, String shape) {
        this.stuffing = stuffing;
        this.shape = shape;
    }

    public String getStuffing() {
        return stuffing;
    }

    public void setStuffing(String stuffing) {
        this.stuffing = stuffing;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
