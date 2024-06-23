package com.z2xinyu.reflection._proxy;

import com.z2xinyu.reflection.util.ProxyClassByteCodeGenerator;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author zhangxinyu
 * @date 2023/10/6
 **/
public class WandaCinemaProxyTest {

    @Test
    public void play() {
        // 静态代理：直接去电影院看电影，电影院会放映广告
        // 装饰器：自己在家可以看电影，但是我想看广告，所以就去电影院
        Cinema movie = new WandaCinemaProxy();
        movie.play();
    }

    @Test
    public void dynamicProxy() {
        // 被代理对象
        Cinema cinema = new WandaCinema();
        MyInvocationHandler myHandler = new MyInvocationHandler(cinema);
        // 返回一个代理对象
        Cinema proxy =
            (Cinema)Proxy.newProxyInstance(cinema.getClass().getClassLoader(), cinema.getClass().getInterfaces(),
                myHandler);
        proxy.play();
        // 代理类继承了Proxy，实现了UserDao
        System.out.println("代理对象的真实类型：" + proxy.getClass().getTypeName());
        // 可以使用getSuperClass()和getInterfaces()来判断
        // true
        System.out.println(proxy instanceof Proxy);
        // true
        System.out.println(proxy instanceof Cinema);

        Class<?> c = Proxy.getProxyClass(cinema.getClass().getClassLoader(), cinema.getClass().getInterfaces());
        System.out.println(c.getSimpleName());
        System.out.println(c.getName());
        System.out.println(c.getTypeName());
        System.out.println(c.getCanonicalName());

        // 把动态代理在内存中生成的代理类的字节码存储到磁盘上，查看结构
        ProxyClassByteCodeGenerator.saveGenerateProxyClass("D:/$Proxy.class", new Class[] {Cinema.class});

        // 这种方式是默认生成到classpath:com.sum.proxy下
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }


    @Test
    public void dynamicNewProxy() {
        Cinema proxyObj = (Cinema)new MyNewInvocationHandler().getProxyObj(new WandaCinema());
        proxyObj.play();
    }
}