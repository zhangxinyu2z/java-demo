package com.z2xinyu.syntax.loop;

import java.util.Scanner;

/**
 switch选择结构的语法是：
 	switch(表达式) {  // 表达式的数据类型是:byte short char int,Jdk1.5后可以是enum,Jdk1.7之后可以用String
 		case 值:   	  // case的值不能重复的，必须是常量表达式，不可以用变量
 			语句;
 			break;	// break是可以省略的，但是如果在中间，会发生case穿透，向下执行，不建议省略
 		case 值:     // 同于同一属于结果的可以写在一起
        case 值:
        case 值:
 			语句;
 			break;
 		……
 		default:	// default可以省略，它是对不确定情况的补充，可以在任何位置，但是不建议，它后面的break可以省略
 			语句;
 			break;
 	}
 switch内部的执行顺序：
    变量表达式的值先去和case的值比较，如果满足，并且遇到break，程序会直接结束，没有break，会继续向下执行，直到遇到break或到程序的末尾
    如果没有满足的值，会执行default，有break,程序直接结束，没有就会向下执行，遇到break或者执行到程序末尾
*/
public class SwitchStructure {
    public static void main(String[] args) {
        testCaseDefault();
    }

    /**
     * 键盘录入数字，在dos上输出星期几
     */
    public static void printWhatDayTheWeek() {
        // 需要键盘录入，所以获取一个Scanner对象
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        // 定义一个变量，保存键盘输入的数值
        int num = sc.nextInt();
        // 使用switch来判断输出获取的数字对应星期几
        switch (num) {
            case 1:
                System.out.println("输入的是星期一");
                break;
            case 2:
                System.out.println("输入的是星期二");
                break;
            case 3:
                System.out.println("输入的是星期三");
                break;
            case 4:
                System.out.println("输入的是星期四");
                break;
            case 5:
                System.out.println("输入的是星期五");
                break;
            case 6:
                System.out.println("输入的是星期六");
                break;
            case 7:
                System.out.println("输入的是星期日");
                break;
            default:
                System.out.println("输入的数字有误");
                break;
        }
    }

    public static void chooseTheStar() {
        // 输出一道单选题，对其进行选择，输出结果
        Scanner sc = new Scanner(System.in);
        System.out.println("以下明星你最喜歡那個？");
        System.out.println("65 劉亦菲");
        System.out.println("66 邱淑貞");
        System.out.println("67 王祖賢");
        System.out.println("68 宋轶");
        // 测试一下char类型，因为还没有学怎么接收char类型数据，使用字母对应的Ascill表对应的码值，
        // 强转成char类型
        int choice = sc.nextInt();
        char c = (char) choice;
        switch (c) {
            case 'A':
            case 'C':
            case 'D':
                System.out.println("恭喜你，选错了！");
                break;
            case 'B':
                System.out.println("恭喜你，选对了！");
                break;
            default:
                System.out.println("你输错了，没有这个选项");
                break;
        }
    }

    /**
     * 判断月份季节
     */
    public static void monthInSeason() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想知道的月份：");
        // 定义接受录入月份的变量
        int month = sc.nextInt();
        switch (month) {
            case 12: // 在这里应用了case穿透的功能，但不建议这样做
            case 1: // 从这个例子，可以看出switch的应用不适合值过多的场景
            case 2:
                System.out.println(month + "月" + "是冬季");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println(month + "月" + "是春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println(month + "月" + "是夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println(month + "月" + "是秋季");
                break;
            default:
                System.out.println("输入的月份有错误");
                break;
        }
    }

    /**
     * 测试case穿透
     */
    public static void testCaseDefault() {
        int a = 12;
        a = 11;
        switch (a) {
            case 4:
                System.out.println("邱淑贞");
//			break;
            default:
                System.out.println("刘亦菲");
                // break;
            case 12:
                System.out.println("王祖贤");
//			break; //  case=12：在这里把break注释掉， 输出的结果是:  王祖贤 林青霞
                // case=11：如果把default置于上方，也注释掉break,且case没有匹配的值，注释掉5的break
                // 输出了刘亦菲 、王祖贤、林青霞
                // 经过这个测试，可以得出结论，switch中的语句是这样执行的：
                // 变量表达式的值先去和case的值比较，如果满足，并且遇到break，程序会直接结束，没有break，会继续向下执行，直到遇到break或到程序的末尾
                // 如果没有满足的值，会执行default，有break,程序直接结束，没有就会向下执行，遇到break或者执行到程序末尾
            case 5:
                System.out.println("林青霞");
//			break;
        }
    }

    public static void switchPratice1() {
        int x = 2;
        int y = 3;
        switch(x){
            default:
                y++;
                break;
            case 3:
                y++;
            case 4:
                y++;
        }
        System.out.println("x=" + x  + " y="+y);
    }

    public static void switchPratice2() {
        int a = 2;
        int b = 3;
        switch(a){
            default:
                b++; // 4
            case 7:
                b++;
            case 8:
                b++;
        }
        System.out.println("b="+b);
    }

}
