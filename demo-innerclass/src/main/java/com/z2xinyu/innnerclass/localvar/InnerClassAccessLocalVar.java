package com.z2xinyu.innnerclass.localvar;

/**
 * 局部内部类访问局部变量
 *      方法参数作为局部变量：基本数据类型必须指定为final，如果是引用类型参数，可以修改其成员属性，但是不允许修改引用类型的地址
 *      方法内部的成员变量：jdk1.8编译后会优化为final修饰，所以不加也没关系
 *
 * @author zxy
 */
public class InnerClassAccessLocalVar {
    class InnerA {
        int num = 0;
    }

    public void show(InnerA innerA) { // 这里传入一个InnerA，实际上是InnerA对象的地址值
        class InnerB {
            private InnerA a = innerA;
            // 但是修改对象内部的成员数据是可以的
            int num = innerA.num++;

            public void show() {
                // 不允许指向其它对象
//                innerA = new InnerA();
            }
        }
    }

    public void show(final int num) {
        // JDK1.8编译成字节码后，优化为final
        int a = 10;
        class Inner {
            // private int c= a++; // final无法自增
            // 访问方法局部变量必须申明为final
//            int b = num++;

            public void inner() {
                System.out.println(num);
            }
        }
        Inner i = new Inner();
        i.inner();
    }

    public static void main(String[] args) {
        new InnerClassAccessLocalVar().show(12);
        new InnerClassAccessLocalVar().show(new InnerClassAccessLocalVar().new InnerA());
    }
}
