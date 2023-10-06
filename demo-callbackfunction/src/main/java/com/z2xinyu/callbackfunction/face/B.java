package com.z2xinyu.callbackfunction.face;

public class B {
    public void doSomething(CallBackFunction cb) { // B拥有一个参数为CallBack接口类型的方法
        System.out.println("I am processing my affairs… ");
        System.out.println("then, I need invoke callbackMethod…");
        cb.callbackMethod();
    }
}