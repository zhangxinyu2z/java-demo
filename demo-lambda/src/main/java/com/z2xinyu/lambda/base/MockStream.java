package com.z2xinyu.lambda.base;

public class MockStream {

    public  void main(String[] args) {

        Person bravo = new Person("bravo", 18);

        // 1.具体实现类，调用它的test()方法
        Predicate<Person> predicate1 = new PredicateImpl();
        // test()内部代码是：bravo.getAge() > 18
        myPrint(bravo, predicate1); // false

        // 2.匿名类，调用它的test()方法
        Predicate<Person> predicate2 = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() < 18;
            }
        };
        myPrint(bravo, predicate2); // false

        // 3.Lambda表达式，调用它的test()方法。
        // 按照Lambda表达式的标准，只要你是个函数式接口，那么就可以接收Lambda表达式
        Predicate<Person> predicate3 = person -> person.getAge() == 18;
        myPrint(bravo, predicate3); // true

        myPrint(bravo, MockStream::test);
    }

    public  boolean test(Person p) {
        return p.getAge() == 18;
    }

    public static void myPrint(Person person, Predicate<Person> filter) {
        if (filter.test(person)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}