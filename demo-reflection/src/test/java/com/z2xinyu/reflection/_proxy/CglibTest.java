package com.z2xinyu.reflection._proxy;

import com.z2xinyu.reflection.cglib.Atm;
import com.z2xinyu.reflection.cglib.AtmInterceptor;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class CglibTest {

    @Test
    public void cglib() {
        //class 文件缓存目录，如果不研究动态类的源码可以不设置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\cglib_classes");
        //用于创建代理对象的增强器，可以对目标对象进行扩展
        Enhancer enhancer = new Enhancer();
        //将目标对象设置为父类
        enhancer.setSuperclass(Atm.class);
        //设置目标拦截器
        enhancer.setCallback(new AtmInterceptor());
        // 创建代理对象
        Atm atm = (Atm)enhancer.create();
        // 通过代理对象调用目标方法
        Object result = atm.withdraw(100);
        atm.checkBalance();
    }

    @Test
    public void cglibb() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Atm.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        Atm atm = (Atm)enhancer.create();
        atm.checkBalance();
    }
}
