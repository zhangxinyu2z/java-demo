package com.xcoding.multithread.po.cases.saleticket.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 可由Timer一次性或重复执行的任务
 */
public class MyTimerTask extends TimerTask {
    private Timer t;
    public MyTimerTask() {}
    public MyTimerTask(Timer t) {
        this.t = t;
    }
    @Override
    public void run() { // 计时器任务要执行的操作
            System.out.println("boom");
            t.cancel(); // 终止计时器
    }
}
