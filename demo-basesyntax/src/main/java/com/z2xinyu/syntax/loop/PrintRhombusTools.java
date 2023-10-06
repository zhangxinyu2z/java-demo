package com.z2xinyu.syntax.loop;

/**
 * 打印任意行的菱形
 * 
 * @author dyjy
 * @version V1.0
 *
 */
public class PrintRhombusTools {

    private PrintRhombusTools() {}

    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 也可以Scanner接收，如果是菱形总行数 ，让只能输入奇数   num = (line + 1)/2
        // 如果输入菱形边长，接收即可
        // print(4);
        // printWay1(2);
        printHollowRhombus(4);
    }

    /**
     * 打印菱形
     * 
     * @param num
     *            菱形的边长
     */
    public static void printWay1(int num) {
        /*
         * 规律：
         *      1、每行空白字符的个数为：num-当前行
         *      2、每行*字符的个数为：当前行*2-1
         */
        // 打印上半行
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // 打印下半行
        for (int i = num - 1; i >= 1; i--) {
            for (int j = 0; j < num - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");

            }
            System.out.println();
        }
    }

    /**
     * 根据四个角的坐标来画出一个菱形 参考：https://blog.csdn.net/sustzc/article/details/79721502
     * 
     * @param num
     *            (要打印的菱形的行数 - 1)/2
     */
    public static void print(int num) {
        int i = 0;
        int j = 0;

        for (i = -num; i <= num; i++) {
            for (j = -num; j <= num; j++) {
                // Sleep(200);

                if (Math.abs(i) + Math.abs(j) <= num) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 打印一个空心菱形
     * 
     * @param num
     *            菱形的边长
     */
    public static void printHollowRhombus(int num) {
        // 打印上半行
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                // 只有第一列和最后一列才打印*
                if (j == 1 || j == 2 * i - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        // 打印下半行
        for (int i = num - 1; i >= 1; i--) {
            for (int j = 1; j <= num - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                if (j == 1 || j == 2 * i - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
