package proxy.statical;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-09 9:16
 */
public class SimpleProxyDemo {
    public static void consumer(Interface proxied) {
        proxied.doSomething();
    }

    public static void main(String[] args) {
        // 消费者并不清楚是哪个对象在执行任务
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
