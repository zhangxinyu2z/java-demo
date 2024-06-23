package com.z2xinyu.reflection._proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class MyNewInvocationHandler implements InvocationHandler {
    // 被代理对象
    private Object target;

    public  Object getProxyObj(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
            this.target.getClass().getInterfaces(), this);
    }

    /**
     * @param proxy jvm生成的代理类的实例
     * @param method 被代理对象的方法   add
     * @param args 方法的参数类型
     * @return 被代理的方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("权限校验");
        Object obj = method.invoke(target, args);
        System.out.println("日志记录");
        System.out.println(method);
        return obj;
    }

}
