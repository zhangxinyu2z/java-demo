package com.z2xinyu.syntax.array;

public class ArrayMethod {
    public static void main(String[] args) {
        String convert = convert(20, 1, 1);
        System.out.println(convert);

    }

    /**
     * 插入排序
     * 思想：将数组的第一个元素认为是一个有序数组，其他的是一个无序数组，从无序数组中取值去倒着和有序数组来比较，比它
     * 值小，就插入，然后就形成一个新的有序数组，再继续拿值比较
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (array[j] < array[j - 1]); j--) {
                int temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
        }
    }

    /**
     * 选择排序，先确定最小值
     * 数组索引：0,1,2,......n-1, n
     * 思路：
     * 1、索引0的元素依次和其他元素比较，如果比其他元素大，就交换位置，最终，索引0的元素是数组中最小的元素
     * 2、索引1的元素一次和其他元素比较，。。。。。。
     * 3、索引n-1的元素和索引n比较
     */
    public static void selectSort(int[] array) {
        // -1 : 数组的最后一位元素不用比较，因此只需要endIndex-1和endIndex比较即可
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序,相邻元素进行比较大小，把最大值排到最右边
     * 思路：
     * 1、索引0和索引1比较，索引0>索引1，交换，此时索引1比索引0大，然后索引1和索引2比较，按照此思路，
     * 最终endIndex - 1和endIndex比较，将最大值确认到endIndex的索引上
     * 2、索引0和索引1比较，值大的一方交换到右边的索引上，......最后一位已经是最大值，无需比较，-1
     * 3、......
     * 4、索引0和索引1比较.....索引0和1再比较一次，循环结束
     */
    public static void bubbleSort(int[] array) {
        // 每次循环确定出最大值的次数
        for (int i = 0; i < array.length - 1; i++) {
            // -1 ： 防止索引越界，j+1表示了最后一位元素的索引
            // -i ： 已经排到最后位的元素无需再比较
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 获取数组中最大值
     *
     * @param arr int数组
     * @return int数组中的最大值
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int x = 1; x < arr.length; x++) {
            if (arr[x] > max) {
                max = arr[x];
            }
        }
        return max;
    }

    /**
     * 返回数组中第一个出现该值的索引
     *
     * @param arr   int数组
     * @param value 指定的值
     * @return 数组中第一次出现的该值索引，无此数值，返回-1
     */
    public static int getFirstIndex(int[] arr, int value) {
        int index = -1;
        for (int x = 0; x < arr.length; x++) {
            if (arr[x] == value) {
                index = x;
                break;
            }
        }
        return index;
    }

    /**
     * 返回值在数组中的索引
     * 思路：
     * 1、先取出中间值,要查找的数比中间值大，那么在mid+1和end索引的中间值比较
     * 2、要查找的数比中间值小，那么在min和mid - 1索引的中间值比较
     * 3、最终会得出一个值，如果这个值和查找的值匹配，返回它的索引，如果数组中不存在，分为以下几种情况：
     * mid = 0;
     * mid = end;
     * value的数值在数组中元素的值之间，但是不存在，比如查找9，数组中只有8和10，此时再继续下去仍然会致start>end
     *
     * @param array 要查找的有序数组
     * @param value
     * @return -1：数组中不存在该元素
     */
    public static int binarySearch(int[] array, int value) {
        if (array.length == 0) return -1;
        int start = 0;
        int end = array.length - 1;
        int mid = (start + end) >> 1;
        while (array[mid] != value) {
            if (value == array[mid]) {
                return mid;
            } else if (value > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            // 此时数组中元素已经比较完毕了
            if (start > end) {
                return -1;
            }
            mid = (start + end) >> 1;
        }
        return mid;
    }

    /**
     * 返回指定值在数组中的索引
     *
     * @param arr
     * @param num
     * @return
     */
    public static int binarySearch2(int[] arr, int num) {
        if (arr.length == 0) return -1;
        int start = 0;
        int end = arr.length - 1;
        int mid;

        while (start <= end) { // 2分查找的结果最后，是相邻的元素的比较
            mid = (start + end) >> 1;
            if (num > arr[mid]) {
                start = mid + 1;
            } else if (num < arr[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 数组元素首尾交换
     */
    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        // 第二种方式
//        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
//            int temp = start;
//            arr[start] = arr[end];
//            arr[end] = arr[temp];
//        }
    }

    /**
     * 一维数组遍历
     */
    public static void arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i == array.length - 1) {
                sb.append("]");
                break;
            }
            sb.append(",");
        }
        System.out.println(sb.toString());
    }

    /**
     * 10进制转其他进制
     *
     * @param num
     * @param base   进制-1
     * @param offset 要转换的进制一个位占几个二进制位
     * @return
     */
    public static String convert(int num, int base, int offset) {
        // int型数据最多有32位
        char[] c = new char[32];
        int index = 0;
        while (num != 0) {
            // 得到该进制位数
            int wei = num & base;
            if (wei > 9) {
                c[index] = (char) (wei - 10 + 'A'); // 'A' 65  '0' 48
            } else {
                c[index] = (char) (wei + '0');
            }
            num >>>= offset;
            // 每次索引+1，最后也是这个数值对应的进制一共有多少位
            index++;
        }
	/*
	// 方式1 把存进去数据首尾交换
	for (int start  = 0,end = index - 1; start < end; start++,end--) {
		char temp  = c[start];
		c[start] = c[end];
		c[end] = temp;
	}
	String s = "";
	for (int i = 0; i < index;i++) {
		s += c[i];
	}
	*/
        // 方式2 倒着遍历
        String s = "";
        for (int i = index - 1; i >= 0; i--) {
            s += c[i];
        }
        return s;
    }


    /**
     * 使用查表法，10进制转其他进制
     *
     * @param num
     * @param base   二进制&1  八进制&7 16进制&15
     * @param offset 该进制占几个二进制位
     * @return
     */
    public static String convert2(int num, int base, int offset) {
        String s = "";
        if (num == 0) {
            s += 0;
        }
        char[] arr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] c = new char[32];
        int index = c.length;
        while (num != 0) {
            int wei = num & base;
            c[--index] = arr[wei];
		/*
		if (wei > 9) {
			c[--index] = (char) (wei - 10 + 'A'); // 'A' 65 '0' 48
		} else {
			c[--index] = (char) (wei + '0');
		}
		*/
            num >>>= offset;
        }
        for (int i = index; i < c.length; i++) {
            s += c[i];
        }
        return s;
    }

    /**
     * 拷贝数组中的元素到目标数组中
     *
     * @param srcArray   源数组
     * @param destArray  目标数组
     * @param srcOffset  源数组开始索引
     * @param destLength 拷贝元素个数
     * @param destOffset 目标数组开始索引
     * @throws Exception
     */
    public static void copy(int[] srcArray, int[] destArray, int srcOffset, int destLength, int destOffset) throws Exception {
        if (srcOffset < 0 || destOffset < 0 || destLength < 0 || srcOffset + destLength > srcArray.length) {
            throw new Exception("输入数据错误");
        }
        for (int i = destOffset; i < destOffset + destLength; i++) {
            destArray[i] = srcArray[srcOffset++];
        }
    }
}
