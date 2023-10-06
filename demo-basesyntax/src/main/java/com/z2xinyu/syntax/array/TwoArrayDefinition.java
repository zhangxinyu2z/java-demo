package com.z2xinyu.syntax.array;

/**
 二维数组：一个存储多个一唯数组的数组，存储的是这些一唯数组的地址值
 定义：
    数据类型[][] 数组名 = new 数据类型[m][n];  m代表一维数组的个数，n代表一维数组的长度
    数据类型 数组名[][];
    数据类型[] 数组名[];
    指定一维数组的个数和每个一维数组的长度，一维数组系统会默认的给对应的数据类型的默认值
 第二种格式：
     数据类型[][] 数组名 = new 数据类型[m][];
     指定一维数组的有多少个，二维数组存储的是一维数组在内存中的地址值，数组是引用类型，它的默认类型是null
     还没有对一维数组初始化，所以无法访问一维数组中不存在的元素。
 第三种格式：
    数据类型[][] 数组名 = {{1,2,3,4},{4,21,1},{13,12,1},....};

 * @author xinyu
 * @version v1.0
 * @date created in 2022-03-17 14:46
 */
public class TwoArrayDefinition {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3, 4}, {1, 23, 4}, {3, 231, 213, 1, 112}};
        show2();
    }

    /**
     * 二维数组规范定义格式
     */
    public static void show2() {
        // 动态申明
        int[][] arr1 = new int[3][2];
        int arr2[][] = new int[3][2];
        int[] arr3[] = new int[3][2];

        // 举例
        int[][] arr4 = new int[2][];
        // null
        System.out.println(arr4[0]);

//        System.out.println(arr4[0][0]); // NullPointerException
//        System.out.println(arr4[0].length); // NullPointerException

        // 赋值1
        arr1[0] = new int[]{12, 121};
        //2
        arr1[0] = new int[3];
        arr1[0][0] = 12;

        //3 静态的初始化
        int[][] arr5 = {{0}, {1}};
        arr5 = new int[][]{{1, 23, 4}, {14, 23, 42}};

    }

    /**
     * 二维数组遍历
     */
    public static void show(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append("[");
            for (int j = 0; j < arr[i].length; j++) {
                if (j != arr[i].length - 1) {
                    sb.append(arr[i][j]).append(",");
                } else {
                    sb.append(arr[i][j]).append("]").append("\r\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
