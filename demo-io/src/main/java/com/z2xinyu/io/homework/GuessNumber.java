package com.z2xinyu.io.homework;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**写一个猜数字小游戏，可以试玩5次,超过5次提示需要付费*/
/**用文件来保存玩游戏的次数*/
public class GuessNumber {
    public static void main(String[] args) throws IOException {
        // 第一种，只要玩家愿意，可以无限次玩这个游戏
//        while(true) {
//            System.out.println("游戏开始：");
//            String result = guessNumber();
//            if (result.equals("n")) {
//                System.out.println("游戏结束。");
//                break;
//            }
//        }
        // 第二种，有条件的:只有n次机会，用完就没有了
        Properties p = new Properties();
        p.load(new FileReader("game-counter.txt"));
        int count = 0;
        while(true) {
            // 允许的游戏次数
            if ((count = Integer.valueOf(p.getProperty("count"))) < 3) {
                System.out.println("游戏开始：");
                String result = guessNumber();
                // 对游戏进行的次数进行统计存储更新
                count++;
                p.setProperty("count", String.valueOf(count));
                p.store(new FileWriter("game-counter.txt"), "game counts");
                if (result.equals("n")) {
                    System.out.println("游戏结束，可以下次再来试玩。");
                    break;
                }
            } else {
                System.out.println("已经没有试玩次数了，可以购买游戏再来。");
                System.exit(0);
            }
        }
    }

    public static String guessNumber() {
        // 0~100之间的随机数
        int randomNum = new Random().nextInt(100) + 1;
        // 玩游戏的次数
        int count = 0;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("输入你猜测的数字：");
            /**
             * nextLine()之前不能有nextInt() */
            int guessNum = Integer.valueOf(sc.nextLine());
            if (guessNum == randomNum) {
                System.out.println("恭喜你，猜对了\n你要继续猜游戏吗？y/n");
                String result =  sc.nextLine();
                return result;
            } else if(guessNum < randomNum) {
                System.out.println("猜测的数字小了,请重新输入你的猜测：");
            } else if (guessNum>randomNum) {
                System.out.println("猜测的数字大了，请重新输入你的猜测：");
            }
        }
    }
}
