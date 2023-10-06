package com.z2xinyu.oop.parameter;

public class Person {

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    private int age;


    public static void testReference(Person p) {
        p.age = 20;
    }

    public static void testReference2(Person p) {
//        p = new Person(200);
    }

    public static void main(String[] args) {
        Person p = new Person();
        testReference(p);
        System.out.println(p.age);
        testReference2(p);
        System.out.println(p.age);
    }
}
