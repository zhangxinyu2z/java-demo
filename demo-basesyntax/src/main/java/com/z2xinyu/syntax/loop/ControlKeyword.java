package com.z2xinyu.syntax.loop;

/**
 break
 	应用于switch语句和循环结构中，无法单独出现在程序语句中，break会直接跳出当前循环
 continue
 	应用于循环结构中，无法单独出现在程序语句中，continue结束当前次语句，继续执行循环接下来的语句
 return
 	应用于退出整个方法，到方法调用处
 */
public class ControlKeyword {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0 ) {
//				break; // 输出 1  2  over ; break在满足条件时，就结束了循环，程序继续往下执行
//				continue; // 输出 1 2 4 5 7 8 10 over;continue在满足条件时，是结束了当前条件的语句执行，还是会
                // 执行不满足continue条件的循环语句，循环执行完成后，程序往下执行
//				return; // 输出：1 2; 程序直接退出了这个方法，而不是退出循环。
//				System.out.println("退出"); // 程序在return后已经退出，无法执行到。
            }
            System.out.println(i);
        }
        System.out.println("over");

        System.out.println("--------------------------------------");
        // 跳出多层循环 使用标签 格式 标签：语句
        b1:for (int i = 1; i < 10; i++) {
            b2:for (int j = 1; j < 10; j++) {
                if (i == 3) {
//					break b1;
                    continue b2;
                }
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}

