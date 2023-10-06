package com.z2xinyu.syntax.array;

/**
 * 数组的申明和初始化
 *
 数组是什么？
 在对多个数据进行操作时，需要多个变量来存储，操作很繁琐
 Java提供了数组，可以用来保存同一种数据类型的多个元素（变量），它可以理解为一个集合，或者容器

 格式：
    数据类型[] 数组名称 = new 数据类型[元素个数]；
    或者
    数据类型[] 标识符 = new 数据类型[]{元素....}; 可简写为 数据类型[] 标识符 = {元素.....};

    数据类型 数组名称[];
    int[] x;	定义了一个int类型的数组，x变量
    int x[];  	定义了一个int类型的 x数组变量

 初始化：
    初始化就是对数组的内存空间进行分配，给每个数组元素赋值，初始化完成后，数组变量就有值了，它保在的是开辟出来的空间的地址值
    静态初始化：指定数组的元素的初始值，由系统分配长度
        int[] arr = new int[] {1,23,2};
        int[] arr = {1,3,2};  效果等同
    动态初始化：指定数组的元素个数，由系统给出初始化值，byte short int long 默认是0；double float 默认是0.0；布尔类型默认是false；
                                                    char默认是空字符，对应的ascii码值是0，unicode表示为 \u0000
        int[] arr = new int[3];
        注意：静态初始化和动态初始化不能同时使用
        int[] arr = new int[3]{1,13,2}; // wrong
    初始化的格式：
        数据类型[] 数组名 = new 数据类型[元素个数];
    解释：
    new:就是在内存中开辟空间
    内存分配：Java中对不同的数据，分配了不同的区域，每种区域都有自己定义数据的方式和管理
    栈：保存所有的局部变量，方法结束就从内存中释放了
    堆：所有new出来的都在堆，都有地址值，每一个变量都有默认值，使用完毕后，不会立即消失，java中有一个回收机制，会在空闲时间处理
    方法区：
    本地方法区：
    寄存器：与cpu有关
 访问方式：
    数组名[数组的索引]; // 索引从0开始
    访问不存在的元素，编译不会报错，因为语法是正确的， 运行时，会出现ArrayIndexOfBoundsException
@author zxy
 */
public class ArrayDefinition {
    public static void main(String[] args) {
        show();
    }

    /**
     * 一维数组申明格式
     */
    public static void show() {
        // 先申明，再赋值
        int[] array1 = new int[10];
        int array2[] = new int[2];
        // 直接申明赋值
        int[] arry3 = new int[]{1, 2, 3,};
        int[] array5 = {1, 2, 3, 4};

        // 这样定义编译不会出错
        int[] array4 = new int[]{};
        // 访问：ArrayIndexOutOfBoundsException
//        array4[0] = 1;


        int[] array6;
//        array6 = {1,2,24};  // 这样先声明，再赋值{}，编译无法通过

    }

}
