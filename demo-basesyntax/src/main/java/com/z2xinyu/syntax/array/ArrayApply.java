package com.z2xinyu.syntax.array;

/**
 * 二维数组的案例
 *
 * @author zxy
 */
public class ArrayApply {
    public static void main(String[] args) {
        yangHui(5);
    }

    /**
     * 打印任意行杨辉三角形  规律：1、首列值和尾列相同  2、每列的值是上一行的本列和本列的值之和
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     */
    public static void yangHui(int line) {
        // 规律1：行数和列数相同
        int[][] arr = new int[line][line];
        // 规律2：每行的第一位和最后一位都是1
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = 1;
            arr[i][i] = 1;
        }
        // 规律3：其它行每位的数值都等于当前列的上一行和上一行的同列的前一行的数值相加的和
        for (int i = 2; i < arr.length; i++) {
            for (int j = 1; j < i; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
            }
        }
        // 遍历数组
        for (int i = 0; i < line; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 某个公司采用公用电话传递数据信息，数据是小于8位的整数，为了确保安全，
     * 在传递过程中需要加密，加密规则如下：首先将数据倒序，然后将每位数字都加上5，
     * 再用和除以10的余数代替该数字，最后将第一位和最后一位数字交换。
     * 请任意给定一个小于8位的整数，然后，把加密后的结果在控制台打印出来。
     */
    public static void secrectNumber(int num) {
        // 1、定义一个数组，用来保存数位值
        int[] arr = new int[7];
        int index = 0; // 数组的索引
        while (num != 0) {
            arr[index] = num % 10;
            num /= 10;
            index++; // index在最后一次赋值完成后会再++,正好统计这个数有多少位
        }
        // 2、每位数字加5，%10

        for (int x = 0; x < index; x++) {
            arr[x] += 5;
            arr[x] %= 10;
        }

        // 3、交换数字
        int temp = arr[0];
        arr[0] = arr[index - 1];
        arr[index - 1] = temp;

        for (int x = 0; x < index; x++) {
            System.out.print(arr[x]);
        }
        System.out.println();

    }
}
