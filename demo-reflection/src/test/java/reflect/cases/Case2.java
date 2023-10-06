package reflect.cases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 给一个泛型List<Integer>类型的集合添加一个String类型的元素
 *
 * @author z-xy
 */
public class Case2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(12);
        al.add(12);
        al.add(12);
//         泛型只是编译前限定类型，在运行期间泛型不再存在，可以通过反射来对字节码文件进行操作。
        Class c = al.getClass();
        Method m = c.getDeclaredMethod("add", Object.class);
        m.setAccessible(true);
        m.invoke(al, "yifei");
        System.out.println(al);
    }
}
