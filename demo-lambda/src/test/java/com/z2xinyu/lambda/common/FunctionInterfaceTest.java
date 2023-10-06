package com.z2xinyu.lambda.common;

import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangxinyu
 * @date 2023/5/4
 **/
public class FunctionInterfaceTest extends TestCase {

    public void test() {
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

        myPrint(bravo, FunctionInterfaceTest::equals);
    }

    public static boolean equals(Person p) {
        return p.getAge() == 18;
    }


    public void myPrint(Person person, Predicate<Person> filter) {
        if (filter.test(person)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}

@FunctionalInterface
interface Predicate<T> {
    /**
     * 定义了一个test()方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
     *
     * @param t
     * @return
     */
    boolean test(T t);
}

/**
 * Predicate接口的实现类，泛型规定只处理Person
 */
class PredicateImpl implements Predicate<Person> {

    /**
     * 判断逻辑是：传入的person是否age>18，是就返回true
     *
     * @param person
     * @return
     */
    @Override
    public boolean test(Person person) {
        return person.getAge() > 18;
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}
