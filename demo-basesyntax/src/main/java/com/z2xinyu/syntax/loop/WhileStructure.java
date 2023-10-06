package com.z2xinyu.syntax.loop;

/**
 while循环的结构：
 初始化语句;
 while(条件判断语句) {
    控制流程语句;
    循环语句;
 }
 --------------------------------------
 do while()循环结构的格式：  // 它和while循环的区别就在于，即便不满足条件，也会执行一次循环体的内容。
 初始化语句;
 do {
    循环语句;
    流程控制语句;
 } while(条件判断语句);

 */
public class WhileStructure {
    public static void main(String[] args) {
        demo1();
        demo2();
    }
    /**
     * 需求:我国最高山峰是珠穆朗玛峰：8848m，我现在有一张足够大的纸张，厚度为：0.01m。
     * 请问，我折叠多少次，就可以保证厚度不低于珠穆朗玛峰的高度?
     */
    public static void demo1() {
        double high = 0.01;
        int count = 0;
        while (high <= 8848) {
            count++;
            high*=2;
            System.out.println("第" + count + "次折叠后的高度是：" + high + "米");
        }
        System.out.println(count);
    }

    /**
     * 珠峰的高度使用doWhile
     */
    public static void getZMLMHigh() {
        double high = 8848.86;
        double paper = 0.01;
        int count = 0;
//		do {
//			paper *= 2;
//			count++;
//			System.out.println("第" + count + "次折叠的高度是" + paper);
//		} while(paper <= high);
        do {
            if (paper >= high) {
                System.out.println("第" + count + "次折叠的高度是" + paper);
                break;
            }
            paper *= 2;
            count++;
        } while (true);
    }

    /**
     * 需求：小芳的妈妈每天给她2.5元钱，她都会存起来，但是，
     * 每当这一天是存钱的第5天或者5的倍数的话，她都会花去6元钱，
     * 请问，经过多少天，小芳才可以存到100元钱。
     */
    public static void demo2() {
        double saveMoney = 0;
        int days= 1;
        while (saveMoney <= 100) {
            saveMoney += 2.5;
            if (days % 5 == 0) {
                saveMoney -= 6;
                System.out.println("第" + days  + "天花掉6元" );
            }
            System.out.println("第" + days + "天存了" + saveMoney);
            days++;
        }
        System.out.println("需要" + --days + "天");

        // --------------------------------------------------第二种思考方式
        //每天要存储的钱是2.5元
        double dayMoney = 2.5;

        //存钱的初始化值是0
        double daySum = 0;

        //从第一天开始存储
        int dayCount = 1;

        //最终存储不小于100就不存储了
        int result = 100;

        //因为不知道是多少天，所以我用死循环，
        while(true) {
            //累加钱
            daySum += dayMoney;

            //一旦超过100元我就退出循环。
            if(daySum >= result) {
                System.out.println("共花了"+dayCount+"天存储了100元");
                break;
            }

            if(dayCount%5 == 0) {
                //花去6元钱
                daySum -= 6;
                System.out.println("第"+dayCount+"天花了6元钱");
            }

            //天数变化
            dayCount++;
        }
    }
}
