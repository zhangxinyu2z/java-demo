package com.z2xinyu.reflection.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AtmInterceptor implements MethodInterceptor {
	/**
     * obj：cglib生成的代理对象
     * method：被代理对象方法
     * args：方法入参
     * methodProxy: 代理方法
    */	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("事务开始");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println(result);
		System.out.println("事务结束");
		return result;
	}
}

