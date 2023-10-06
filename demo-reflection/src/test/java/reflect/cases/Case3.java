package reflect.cases;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 在文件中存取类的全限定名，生成该类的对象
 *
 * @author z-xy
 */
public class Case3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        Properties p = new Properties();
        FileReader fr = new FileReader("class.txt");
        p.load(fr);
        String className = p.getProperty("ClassName");
        String methodName = p.getProperty("MethodName");

        Class c = Class.forName(className);
        Object obj = c.newInstance();
        Method m = c.getDeclaredMethod(methodName);
        m.setAccessible(true);
        m.invoke(obj);
    }
}
