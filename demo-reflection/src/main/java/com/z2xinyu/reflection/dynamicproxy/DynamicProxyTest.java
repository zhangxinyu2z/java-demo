package com.z2xinyu.reflection.dynamicproxy;


import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Set;

public class DynamicProxyTest {

    public static void main(String[] args) throws IOException {
        // 被代理对象
        UserDao ud = new UserDaoImpl();
        MyInvocationHandler myHandler = new MyInvocationHandler(ud);
        // 返回一个代理对象
        UserDao proxy =
            (UserDao)Proxy.newProxyInstance(ud.getClass().getClassLoader(), ud.getClass().getInterfaces(), myHandler);

        proxy.add();

        System.out.println("代理对象的真实类型：" + proxy.getClass().getTypeName());
        /*
            说明了代理类继承了Proxy，实现了UserDao
            也可以使用getSuperClass()和getInterfaces()来判断
        */
        // true
        System.out.println(proxy instanceof Proxy);
        // true
        System.out.println(proxy instanceof UserDao);

        Class<?> c = Proxy.getProxyClass(ud.getClass().getClassLoader(), ud.getClass().getInterfaces());
        System.out.println(c.getSimpleName());
        System.out.println(c.getName());
        System.out.println(c.getTypeName());
        System.out.println(c.getCanonicalName());

        // 把动态代理在内存中生成的代理类的字节码存储到磁盘上，查看结构
        ProxyClassByteCodeGenerator.saveGenerateProxyClass("D:/$Proxy.class",
            new Class[] {UserDao.class});

        // 这种方式是默认生成到classpath:com.sum.proxy下
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    }

    /**
     * 获取所有的系统属性
     */
    @Test
    public void fun() {
        Set<Object> set = System.getProperties().keySet();
        if (!set.isEmpty()) {
            Iterator<Object> iterator = set.iterator();
            Object key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                System.out.println(key + " : " + System.getProperties().get(key));
            }
        }
    }

}