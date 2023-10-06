package com.z2xinyu.callbackfunction.face;

public class A implements CallBackFunction {
    B b = new B();

    public void do1() {
        // A运行时调用B中doSomething方法,以自身传入参数，B已取得A，可以随时回调A所实现的CallBack接口中的方法
        b.doSomething(this);
    }

    @Override
    public void callbackMethod() { // 对A来说，该方法就是回调方法
        System.out.println("callbackMethod is executing!");
    }
}