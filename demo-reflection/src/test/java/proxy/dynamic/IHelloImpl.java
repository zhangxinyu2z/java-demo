package proxy.dynamic;

/**
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-09 9:22
 */
public class IHelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("您好");
    }
}
