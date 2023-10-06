package com.z2xinyu.innnerclass.definition;

public class BodyTest {
    public static void main(String[] args) {
        // wrong,在同一包中，private可以访问，但是在其他类中,不行
        //new Body().new Heart();

        // 可以调用其public方法间接访问
        Body body = new Body();
        body.method();
    }
}
