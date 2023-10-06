package com.z2xinyu.io.cn.xy.io.file;

/**
 * 递归：方法调用方法本身,方法的嵌套并不是递归
 * 递归的注意事项：
 * 1、递归一定要有出口，死递归
 * 2、递归的次数不能太多，会内存溢出
 * 3、构造方法不能递归
 */
public class DiGuiCase {
    public static void main(String[] args) {
        // 循环实现
        int sum = 1;
        for (int i = 2; i < 6; i++) {
            sum *= i;
        }

        System.out.println(getNum(20));
        getNum2();
    }

    /**
     * 算法题：
     * 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问第二十个月的兔子对数为多少？
     * 1
     * 1
     * 3个月 1 + 1    2
     * 4个月 (1 + 1) + 1   3
     * 5个月 ((1 + 1) + 1) + (1 + 1)   5
     * 6个月 1+1 +1 + 1 + 1 + 1+1 +1   8
     * 规律就是第3个月开始，每个月的兔子对数是上个月的兔子对数之和
     */
    public static int getNum(int days) {
        if (days == 1 || days == 2) {
            return 1;
        } else {
            return getNum(days - 2) + getNum(days - 1);
        }
    }

    public static void getNum2() {
        // 用数组来做
/*        int[] num = new int[20];
        num[0] =1;
        num[1] = 1;
        for (int i = 2; i < num.length; i++) {
            num[i]= num[i-1] + num[i-2];
        }
        System.out.println(num[19]);*/

        /**
         * 第二种方式：变量的变化实现
         *         相邻的两个月的兔子对数是a,b
         *         第一个相邻：a =1 ,b =1
         *         第二个相邻：a = 1,b =2
         *         第二个相邻：a = 2,b =3
         *         第二个相邻：a = 3,b =5
         *         规律是：下次的a是之前的b,下一次的b是之前的a+b
         */
        int a = 1;
        int b = 1;
        for (int i = 0; i < 18; i++) {
            int temp = a;
            a = b;
            b = b + temp;
        }
        System.out.println(b);
    }


    /*
    分解和合并：求5的阶乘
    分解：5！-> 5 * 4! -> 4 * 3! -> 3 * 2! -> 2 * 1! -> 1!
                                             反过来       <—— ：合并
     */
    public static int jc(int num) {
        if (num == 1) {
            return 1;
        }
        return num * jc(num - 1);

    }
}
