package proxy.statical;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-09 9:14
 */
public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }
}
