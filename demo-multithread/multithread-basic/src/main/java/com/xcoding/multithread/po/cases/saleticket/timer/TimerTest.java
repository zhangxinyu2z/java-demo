package com.xcoding.multithread.po.cases.saleticket.timer;

import java.util.Timer;

/**
 * 线程的工具，用于在后台线程中安排将来执行的任务。 可以将任务安排为一次性执行，或者以固定间隔重复执行。
 * schedule() 计划预定、安排
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer t= new Timer();
//        t.schedule(new MyTimerTask(), 5000, 2000); // 延迟5秒后，每隔2秒boom一次

        t.schedule(new MyTimerTask(t), 5000, 2000);
    }
}
