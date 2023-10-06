package com.z2xinyu.reflection.ReflectionAccessClassObject;

/**
 * 获取class对象的方式
 * 
 * @author zhang xinyu
 * @date 2021-04-16 19:58:31
 * @version v1.0
 */
public class Demo01ReflectionAccessClassObject {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
          1 常用于方法中指定参数类型
         */
        Class<Student> c = Student.class;
        System.out.println(c.getName());

        /*
         Api的描述：基本数据类型也被表示为Class对象，但是它们没有对应的.class字节码文件来描述，而是jvm创建的内置Class对象（或许是让反射机制更完善？）
         <The primitive Java types (boolean, byte, char, short, int, long, float, and double), and the keyword void 
         are also represented as Class objects.> 
            基本类型.class是包装类.TYPE的语法糖（更精炼的语言让代码更易懂，比如汉字的成语）
            查看Integer的源码
                public static final Class<Integer>  TYPE = (Class<Integer>) Class.getPrimitiveClass("int");
         */
        Class<Integer> intC = int.class; // 拆装箱
        Class<Integer> type = Integer.TYPE; // 反编译被优化成了 Class<int> type = int.class;
        System.out.println("intC = " + intC + "\r" + "type = " + type); // intC = int type=int

        Class<Integer> integer = Integer.class; // 没有优化：Class<Integer> integer = Integer.class;
        System.out.println(integer == type); // false Integer是有.class文件的，看来描述的 不是同一个对象

        // 2 获取对象真正的类型(想想多态多中运行中实际的对象类型)
        Student stu1 = new Student();
        Student stu2 = new Student("刘亦菲", 24);
        Class<? extends Student> c1 = stu1.getClass();
        Class<? extends Student> c2 = stu2.getClass();
        System.out.println(c1 == c2); // true

        // 3 常用于读取配置文件
        Class<?> c3 = Class.forName("com.dhjy.demo01.ReflectionAccessClassObject.Student");
        System.out.println(c3.getName());

        // 4 类加载器的方式
        ClassLoader classLoader = c.getClassLoader();
        Class<?> loadClass = classLoader.loadClass("com.dhjy.demo01.ReflectionAccessClassObject.Student");
        System.out.println(loadClass.getName());

    }
}
