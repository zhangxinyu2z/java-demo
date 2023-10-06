package com.z2xinyu.syntax.loop;

import java.util.Scanner;

/**
for循环结构的格式：
    for (初始化语句;条件判断语句;流程控制语句:循环何时结束) {
        循环语句;
    }
    for循环中的语句并不必全部给出，比如for(;;)就是一个死循环
    for循环中的初始化变量，生命周期只在for循环中有效，而且往往for循环中的条件判断语句的数值范围是很明确的。
 */
public class ForStructure {
    public static void main(String[] args) {
//        wc:
//        for (int x = 0; x < 3; x++) {
//            nc:
//            for (int y = 0; y < 4; y++) {
//                if (y == 2) {
//                    //break nc;
//                    break wc;
//                }
//                System.out.print("*");
//            }
//            System.out.println();
//        }
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
    }

    /**
     * 需求：计算水仙花数，并统计有多少个.
     * 水仙花数是指一个三位数，其各位数字的立方和等于该数本身。
     * 分析：
     * 1、范围(100~999)
     * 2、得到每位上的数值
     * 3、立方和和该数本身进行比较
     */
    public static void demo1() {
        int count = 0;
        for (int i = 100; i < 1000; i++) {
            int ge = i % 10;
            int ten = i / 10 % 10;
            int hundred = i / 10 / 10 % 10;
            if ((ge * ge * ge + ten * ten * ten + hundred * hundred * hundred) == i) {
                count++;
                System.out.println("水仙花数是：" + i);
            }
        }
        System.out.println("水仙花数有" + count + "个");
    }

    /**
     * 需求：请在控制台输出满足如下条件的五位数:
     * 个位等于万位;
     * 十位等于千位;
     * 个位+十位+千位+万位=百位.
     */
    public static void demo2() {
        for (int i = 10000; i < 10000; i++) {
            int ge = i % 10;
            int ten = i / 10 % 10;
            int hundred = i / 100 % 10;
            int thousand = i / 1000 % 10;
            int tenThousand = i / 10000 % 10;
            if ((ge == tenThousand) && (ten == thousand)
                    && (ge + ten + thousand + tenThousand == hundred)) {
                System.out.println(i);
            }
        }
    }

    /**
     * 需求：请统计1-1000之间同时满足如下条件的数据有多少个：
     * 对3整除余2;
     * 对5整除余3;
     * 对7整除余2.
     */
    public static void demo3() {
        int count = 0;
        for (int i = 1; i < 1000; i++) {
            if ((i % 3 == 2) && (i % 5 == 3) && (i % 7 == 2)) {
                count++;
            }
        }
        System.out.println("一共有" + count + "个");
    }

    /**
     * 需求：循环嵌套：打印：
     * *
     * **
     * ***
     * ****
     */
    public static void demo4() {
        // 控制打印行数
        for (int i = 0; i< 4; i++) {
            // 控制每行打印的字符个数
            for (int j = 0; j < i +1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 需求：打印99乘法表
     */
    public static void demo5() {
        for (int i = 1; i<= 9;i++){
            for (int j = 1; j < i +1; j++) {
                System.out.print(j + "*" + i + "=" +j * i + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 求阶乘
     */
    public static void demo6(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        System.out.println("1到9的阶乘是" + result);
    }

    //  3、* 打印直角(right)、等腰三角形(isos)、菱形(rhombus)
    public static void printGraph() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("right")) {
            // 使用两个for来实现，外for控制输出几行，内行控制每行的*数目(列)
            for (int i = 0; i <= 4; i++) {
                for (int j = 0; j < i + 1; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else if (input.equals("isos")) { // 等腰三角形
            // 外层来控制3行
            for (int i = 1; i <= 3; i++) {
//                for (int m = 2; m >= i; m--) {
//                for (int m = i; m < 3; m++) {
                for (int m = 1; m <= 3 - i; m++) { // 左上角是一个倒立的直角三角，使用空格替代
                    System.out.print(" ");
                }
                for (int j = 1; j <= 2 * i - 1; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }

        } else if (input.equals("rhombus")) { // 菱形
            int size = 4;
            // 打印的是5行
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size - i; j++) {
                    System.out.print(" ");
                }
                for (int x = 1; x <= 2 * i - 1; x++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            for (int i = 1; i < size; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(" ");
                }
//                for (int z = 0; z < 2 * (size - i) - 1; z++ ) {
                for (int z = 2 * size - 3; z >= 2 * i - 1; z--) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else {
            System.out.println("input error");
        }
    }

    public static void test() {
        // 练习5 打印等腰三角形
        System.out.println("打印等腰三角形:");
        /*
         * 分析： 首先从等腰三角形列数是奇数形，它的左上角是一个直角三角形，作为空格把它顶出来
         ***
         *****
         *******
         */
        for (int i = 0; i < 4; i++) { // 打印4行
            for (int j = 1; j < 4 - i; j++) {// 打印一个倒直角三角形，3行3列
                System.out.print(" ");
            }
            // 打印一个奇数列
            for (int x = 0; x < 2 * i + 1; x++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // 练习6 打印菱形
        /*
         *
         ***
         *****
         ***
         *
         */
        // 用两个for for 来实现
        System.out.println("打印一个菱形1：");
        int n = 5; // 几边菱形
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            for (int x = 0; x < 2 * i + 1; x++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int x = 2 * i + 1; x <= 2 * (n - 1) - 1; x++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 打印一个菱形修改
        System.out.println("打印一个菱形2:");
        // 用规律来写：1、上半行没行的空个数：是上半行的行数-当前行
        // 			   2、上半行每行的*输：是当前行*2-1
        for (int i = 1; i <= n; i++) {
            for(int j = 0; j < n - i;j++) {
                System.out.print(" ");
            }
            for (int x = 0; x < 2 * i -1; x++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // 写下半部分
        for (int i = n -1; i >= 1; i--) {
            for(int j = 0; j < n - i;j++) {
                System.out.print(" ");
            }
            for(int y = 0; y < 2*i -1;y++) {
                System.out.print("*");
            }
            System.out.println();
        }


        /*
         *
         * *
         *   *
         * *
         *
         */
        System.out.println("打印一个空心菱形：");
        int num = 3;
        // 先打印上部分，这个循环控制的是行数，一行一行打印
        for (int i = 0; i < num; i++) {
            // 这个循环打印上半部分的空格占位
            for (int j = i + 1; j < num; j++) {
                System.out.print(" ");
            }
            // 打印上半部分左边的*
            System.out.print("*");
            // 这个循环用来打印上半部分中间的空格占位
            for (int m = 0; m < 2 * i - 1; m++) {
                System.out.print(" ");
            }
            // 右边的*，第一行是没有*的，做一个限定
            if (i != 0) {
                System.out.print("*");
            }
            // 进行换行
            System.out.println();
        }
        // 下半部分同理
        for (int i = 0; i < num - 1; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int m = 2 * i + 1; m <= 2 * num - 5; m++) {
                System.out.print(" ");
            }
            if (i != num - 2)
                System.out.print("*");
            System.out.println();
        }
    }
}
