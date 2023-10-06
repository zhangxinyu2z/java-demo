package reflect.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射：通过class文件对象获取类的信息
 *
 * @author z-xy
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 获取Class对象的3种方式
        // way one
        Student s = new Student();
        Class c1 = s.getClass();

        // way two
        Class c2 = Student.class;

        // way three
        // forName()执行时，进行了类的静态初始化
        Class c3 = Class.forName("com.xinyu.reflect.demo.Student");

        System.out.println(c3.getName());
        System.out.println(c3.getTypeName());
        System.out.println(c3.getSimpleName());


        /**
         * 获取Class
         */
        // 获取所有public修饰的内部类，包括父类
        Class c4 = Class.forName("com.xinyu.reflect.demo.Human");
//        Class[] classes = c4.getClasses();
        // 获取所有修饰的类，接口，限本类
        Class[] classes = c4.getDeclaredClasses();
        for (Class c : classes) {
            System.out.println(c);
        }
        System.out.println("-------");

        // 获取实现类的所有接口
        Class[] interfaces = c3.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
    }

    public static void testField() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {
        Class<Student> c3 = Student.class;
        Object obj1 = c3.getConstructor().newInstance();
        // 获取所有public修饰的字段，包括父类
        Field[] fields = c3.getFields();
        // 获取所有权限字段，仅限本类
        Field[] fields2 = c3.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);
        }

        // 获取指定字段名，可以获取继承来的字段
        Field field = c3.getField("weight");
        // static 需要用declared
        Field field2 = c3.getDeclaredField("country");
//        System.out.println(field);
        // 为字段赋值
        field.set(obj1, 40);
        // 获取设定的字段值
        Object r1 = field.get(obj1);
        System.out.println(r1);

        field2.set(null, "china");
        Object r2 = field2.get(null);
        System.out.println(r2);
    }

    public static void testMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class c3 = Student.class;
        Object obj1 = c3.getConstructor().newInstance();
        // 获取所有public修饰的方法，包括父类
        Method[] methods = c3.getMethods();
        // 获取所有权限的方法，但不包括父类
        methods = c3.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        // 获取指定方法
        // static
        Method method1 = c3.getMethod("study");
        // private
        Method method2 = c3.getDeclaredMethod("run", double.class);
        // 调用方法
        // static可以不用传对象，result是方法调用返回的结果，没有为null
        Object result = method1.invoke(null);
        System.out.println(result);
        // 使私有方法可以访问
        method2.setAccessible(true);
        Object result2 = method2.invoke(obj1, 4.2);
        System.out.println(result2);

        System.out.println("------");
    }

    public static void testContructor() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        Class c3 = Student.class;
        // 获取所有public修饰的构造方法
        Constructor[] cons = c3.getConstructors();
        // 获取所有权限修饰符的构造方法
        cons = c3.getDeclaredConstructors();
        for (Constructor con : cons) {
            System.out.println(con);
        }
        // ------获取指定参数构造方法------
        // 获取空参数构造方法
        Constructor con1 = c3.getConstructor();
        // 使可以获取非public权限的构造器
        Constructor con2 = c3.getDeclaredConstructor(String.class);
        // 构建一个对象
        Object obj1 = con1.newInstance();
        // 破坏访问权限
        con2.setAccessible(true);
        // 构造一个对象
        Object obj2 = con2.newInstance("hello");
        System.out.println(obj1);
        System.out.println(obj2);
    }
}
