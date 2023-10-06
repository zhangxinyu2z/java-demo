package com.z2xinyu.oop.inherit;

/**
 *
 * @author zxy
 */
public class Child extends Super {
    public void printObjects() {
        // 下面的句子是不能编译通过的,为什么？ 因为object是private，在super中printAll是自己内部的成员，自然可以访问
        /*for(int i=0;i<objects.size();i++){
            System.out.println("序号="+i+"\t元素="+objects.get(i));
        }*/
    }

    public static void main(String[] args) {
        Child childClass = new Child();

        childClass.addStr2Obs("Hello");
        childClass.addStr2Obs("World");
        childClass.addStr2Obs("China");
        childClass.addStr2Obs("sitinspring");

        childClass.printAll();
    }
}
