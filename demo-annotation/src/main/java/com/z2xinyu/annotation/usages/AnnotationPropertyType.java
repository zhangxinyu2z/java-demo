package com.z2xinyu.annotation.usages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的元素类型
 * <p>
 * `@Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
 * 限定作用目标，比如该注解只能用在方法上
 * `@Retention(RetentionPolicy.RUNTIME) SOURCE 、 CLASS
 * 注解在源代码中生效、字节码中生效、运行内存中生效
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 18:58
 */

@MyAnno1(
        a = 100,
        b = "hello",
        c = MyEnum1.A,
        d = String.class,
        e = @MyAnno2(aa = 200, bb = "world"),
        f = {100} // 只有1个元素时可以省略大括号
)
public class AnnotationPropertyType {
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno1 {
    int a();     // 8种基本数据类型

    String b();

    MyEnum1 c();

    Class d();

    MyAnno2 e();

    int[] f();
}

@interface MyAnno2 {
    int aa();

    String bb();
}

enum MyEnum1 {
    A, B, C
}

@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@interface MyAnno3 {
    String s();
}
