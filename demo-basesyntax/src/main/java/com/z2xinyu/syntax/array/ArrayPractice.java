package com.z2xinyu.syntax.array;

/**
 * 练习题
 *
 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 21:17
 */
public class ArrayPractice {

    /**
	 1、古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
	 假如兔子都不死，问每个月的兔子对数为多少？
	 分析：
	 	第一个月：1对		1
	 	第二个月：1对		1
	 	第三个月：1对 + 1对  2
	 	第四个月：1对 + 1对 + 1对	 3
	 	第五个月：1对 + 1对 + 1对 + 1对 + 1对 5
	 	第六个月：5对 + 1 + 1 + 1	8对
	 	第七个月：8对 + 1 + 1 + 1 + 1 + 1 13对
	 */
    public static void getRabbitNumber(int month) {
        int[] arr = new int[month];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < month; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println("第" + month + "月有" + arr[month - 1] + "对兔子");
    }

    /**
     2、判断101-200之间有多少个素数，并输出所有素数
     素数就是只能被１和自身整除的数，除此外没有其他因数
     */
    public static void getSuShuNumber() {
		/*
		int count = 0;
		boolean flag = false;
		for(int i = 101; i <= 200; i++) {
			// 任何一个数都能被１和自身整除，那我就判断这个数能不能被（1-到这个数中间的数整除，一旦有能
			// 整除的，那么这个数就不是素数 反之，亦然。
			for (int j = 2; j < i;j++) {
				if (i % j == 0) {
					flag = true;
				}
			}
			if (!flag) {
				count += 1;
				System.out.println(i);
			}
			flag = false;
		}
		System.out.println(count);
		*/
        int i, j;
        int count = 0;
        for (i = 101; i <= 200; i++) {
            for (j = 2; j < i; j++) {
                // 如果这个数能被其他数整除
                if (i % j == 0) {
                    break; // 这个数不是素数，开始下一个判断
                }
            }
            // 如果这个数，它是素数，那么这个内循环会一直判断到j=101,
            if (j == i) {
                count++;
                System.out.print(i + " ");
                if (count % 5 == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println("\n101-200之间一共有" + count + "个素数");
    }

    /**
     3、打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，
     其各位数字立方和等于该数本身。例如：153是一个"水仙花数"
     */
    public static void getSxhNumber() {
        System.out.println("水仙花数一共有：");
        for (int i = 100; i < 1000; i++) {
            int g = i % 10;
            int s = i / 10 % 10;
            int q = i / 10 / 10 % 10;
            if (g * g * g + s * s * s + q * q * q == i) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     4、将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
     */
    public static String getZys(int num) {
        // 打印出90=2*3*3*5。
        String s = num + "=";
        int i, j = 0;
        while (true) { // 既然不知道分解的次数，就一直执行下去
            for (j = 2; j <= num; j++) { // 5
                if (num % j == 0) { // j=2 j=3 j=3 5
                    if (num == j) {
                        s += j;
                    } else {
                        s = s + j + "*"; // s 2 + 3 +3
                    }
                    num /= j; // num 45 15 5
                    break;
                }
            }
            if (num == 1) { // 执行到最后一个无法分解的数，就是j了
                break;
            }
        }
        return s;
		/*
		for (j = 2; j <= num;j++) {
			while(num % j == 0 && num != j) {
				num /= j;
				s = s + j + "*";
			}
			if(num == j)
				s += j;
		}
		*/

    }
}
