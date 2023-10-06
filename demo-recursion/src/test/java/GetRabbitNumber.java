/**
 * 一对兔子，从出生三个月开始每个月生一对兔子，小兔子3个月后又生一对兔子，兔子都不死，请问每个月的兔子对数是多少？
 * 
 * 1 1 2 3 5 8 出口就是第一个月的兔子对数：n= 1 方法是每个月的兔子对数：参数是月
 * 
 * @author zhang xinyu
 *
 */
public class GetRabbitNumber {
    public static void main(String[] args) throws Exception {
        // int num = getRabbitNum(0);
//        int num = getRabbitNum(6);
        int num = getRabbitNum3(6);
        System.out.println("兔子对数为：" + num);
    }

    /**
     * 递归实现
     * 
     * @param month
     * @return
     * @throws Exception
     */
    public static int getRabbitNum(int month) throws Exception {
        if (month <= 0) {
            throw new Exception("长点心吧，少年");
        }
        if (month == 1 || month == 2) {
            return 1;
        } else {
            return getRabbitNum(month - 1) + getRabbitNum(month - 2);
        }

    }

    /**
     * 数组实现
     * 
     * @param num
     * @return
     */
    public static int getRabbitNum2(int num) {
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || i == 1) {
                arr[i] = 1;
            } else {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        return arr[num - 1];
    }

    /**
     * 相邻月的兔子对数为：a，b 第一个相邻：a= 1, b=1 第二个相邻：a=1,b=2 第三个响铃：a= 2,b = 3
     * 
     */
    public static int getRabbitNum3(int num) {
        int a = 1, b = 1;
        for (int i = 0; i < num - 2; i++) {
            int temp = a;
            a = b;
            b = a + temp;
        }
        return b;
    }
}
