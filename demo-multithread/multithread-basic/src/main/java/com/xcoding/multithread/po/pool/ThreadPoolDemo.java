package com.xcoding.multithread.po.pool;

import java.util.concurrent.*;

/**线程池里的每一个线程代码结束后，并不会死亡，而是再次回到线程池中成为空闲状态，等待下一个对象来使用。
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException
            , InterruptedException {
        // 1、创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        /*
        2、提交任务执行，并返回该任务的Future
         ? 是Future的get方法返回的类型
         */
//        Future<?> f1 = pool.submit(new MyRunnable());
//        Future<?> f2 = pool.submit(new MyRunnable());
//        Future<?> f3 = pool.submit(new MyRunnable());
        Future f1= pool.submit(new MyCallable());
        // 3、获取返回的结果
        Object result = f1.get();
        System.out.println(result);

        pool.shutdown(); // 关闭线程池

    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName());
    }
}

class MyCallable implements Callable {
    @Override
    public Object call() throws Exception { // 这里就是get获取的返回值类型
        return "mycallable";
    }
}