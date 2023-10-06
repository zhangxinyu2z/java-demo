package com.z2xinyu.io.cn.xy.io.processflow._byte.objectflow;

import java.io.*;

/**
 * 序列化流，把对象按照流一样的方式存储到文本文件或者网络中
 * 需要序列化的对象必须实现Serializable接口，这种接口称为标记接口，表示只有实现该接口才具有此功能
 * transient修饰的字段不会被序列化
 *
 * @author z-xy
 */
public class ObjectInputStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filepath = "ObjectStudent.txt";
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filepath));
        Student stu = new Student("刘亦菲", 24);
        // 通过对象输出流把对象数据写到文件中
        oos.writeObject(stu);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filepath));
        // 通过对象输入流把文件读取到内存中
        Object obj = ois.readObject();
        Student st = (Student) obj;
        System.out.println(st.getName() + "/" + st.getAge());

    }
}

class Student implements Serializable {
    /**
     * 如果IDEA没有,设置Editor->Inspections->JAVA->Serializations issues中设置
     */
    private static final long serialVersionUID = -2734928444486639729L;

    private String name;
    // transient 表示该属性无法被序列化
    private transient int age;


    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
