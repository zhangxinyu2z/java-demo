package proxy.dynamic;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-09 9:24
 */
public class HelloDynamicProxyDemo {
    public static void consumer(IHello hello) {
        hello.sayHello();
    }

    public static void main(String[] args) {
        consumer(new IHelloImpl());
        consumer((IHello) new HelloDynamicProxyHandler().bind(new IHelloImpl()));
    }
}
