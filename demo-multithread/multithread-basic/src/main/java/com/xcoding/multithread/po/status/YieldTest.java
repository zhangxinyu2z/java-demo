package com.xcoding.multithread.po.status;

/**
 * Yield:让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会,但不一定保证
 * 从未导致线程转到等待/睡眠/阻塞状态
 * > yield意味着放手，放弃，投降。一个调用yield()方法的线程告诉虚拟机它乐意让其他线程占用自己的位置。
 * > 这表明该线程没有在做一些紧急的事情。注意，这仅是一个暗示，并不能保证不会产生任何影响。
 */
public class YieldTest {
    public static void main(String[] args) {
        MyThreadYield y = new MyThreadYield("1");
        MyThreadYield y2 = new MyThreadYield("2");
        y.start();
        y2.start();
    }
}

class MyThreadYield extends Thread {
    public MyThreadYield(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.getName().equals("2")) {
                this.yield();
            }
            System.out.println(this.getName() + ":" + i);
        }
    }
}
