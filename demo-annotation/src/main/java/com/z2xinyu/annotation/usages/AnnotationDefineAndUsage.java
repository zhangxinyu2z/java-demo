package com.z2xinyu.annotation.usages;

/**
 * 注解的定义方式，所有注解都是Annotation的子类
 * 注解的作用目标：类、成员、方法...
 * <p>
 * 只有value属性时，可以省略xx=，直接写值
 *
 * @author zhang xinyu
 * @version v1.0
 * @date Created in 2021-05-16 18:43
 */
@Annotation01
@Annotation02(name = "zs", age = 12)
@Annotation03(12)
public class AnnotationDefineAndUsage {
    @Annotation01
    private String name;

    @Annotation01
    public AnnotationDefineAndUsage() {
    }

    @Annotation01
    public void fun1(@Annotation01 String s) {
        @Annotation01
        String s1 = "ssss";
    }

}

/**
 * 定义一个注解
 *
 * @author
 */
@interface Annotation01 {
}

/**
 * @author
 */
@interface Annotation02 {
    // 如果有默认值，使用时值可以不写，写了就是替换
    int age() default 100;

    String name();
}

/**
 * @author
 */
@interface Annotation03 {
    // 只有value属性需要指定时，可以直接写值（value独有）
    int value();

    String name() default "heloo";
}
