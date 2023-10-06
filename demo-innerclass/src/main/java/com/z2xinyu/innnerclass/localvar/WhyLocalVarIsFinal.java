package com.z2xinyu.innnerclass.localvar;

/**
 * 局部内部类访问局部变量
 * 一定要加final的原因在于，内部类作为一个对象，它的生命周期远远大于方法，这就需要访问的局部变量的对象不应因为没有变量指向而被gc
 * 回收，查询资料解释为：内部类对象会把要访问的所有final类型局部变量都拷贝一份作为它的成员变量，引用的对象始终保持不变
 *
 * @author zxy
 */
public class WhyLocalVarIsFinal {
    public static void main(String[] args) {
        // 编译后的字节码同样优化成了final
        String var = "hello";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(var);
                }
            }
        }).start();

        System.out.println("main thread finished");
        // 按理来说，main方法应该结束了，但是匿名线程还需要访问这个变量
        // 既然还可以访问，说明这个变量的作用域扩大了
    }
}
