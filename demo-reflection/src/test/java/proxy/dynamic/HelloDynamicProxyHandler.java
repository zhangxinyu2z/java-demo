package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloDynamicProxyHandler implements InvocationHandler {
    private Object desc;

    /**
     * 得到代理对象
     *
     * @param desc
     * @return
     */
    public  Object bind(Object desc) {
        this.desc = desc;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                this.desc.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("say hello prepare");
        Object result = method.invoke(this.desc, args);
        System.out.println("say hello end");
        return result;
    }
}
