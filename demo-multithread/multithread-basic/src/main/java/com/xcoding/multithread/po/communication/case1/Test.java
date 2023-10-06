package com.xcoding.multithread.po.communication.case1;

// 线程通信：不同种类的线程对同一资源的操作。
// 想要实现的是生产一个，消费一个
// 那么加一个条件判断，如果没有包子，就让其等待，但是会释放锁;进行生产
// true 代表已经有了，需要消耗掉  然后false
public class Test {
    public static void main(String[] args) {
        Bun b = new Bun();

        Producer p = new Producer(b);
        Consumer c = new Consumer(b);
/**
 * wait用于线程之间的通信（注意是线程之间的通信，也就是说他是个中间人），
 * 该方法会使当前拥有该对象锁的线程等待，直到其他线程调用了notify、notifyAll或者wait超时才会“苏醒”
 */
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}
