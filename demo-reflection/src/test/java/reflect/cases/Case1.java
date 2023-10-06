package reflect.cases;

import java.lang.reflect.Field;

public class Case1 {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        Student s = new Student();
        setProperty(s, "name", "yifei");
        System.out.println(s.toString());
    }

    /**
     * 给指定的对象设置属性值
     */
    public static void setProperty(Object obj, String propertyName, Object value) throws IllegalAccessException,
            NoSuchFieldException {
        Class c = obj.getClass();
        Field field = c.getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}

class Student {
    private String name;

    public void love() {
        System.out.println("love");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
