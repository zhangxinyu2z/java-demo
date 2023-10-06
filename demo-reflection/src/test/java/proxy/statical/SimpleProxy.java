package proxy.statical;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-09 9:14
 */
public class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("----before----");
        proxied.doSomething();
        System.out.println("-----after----");
    }
}
