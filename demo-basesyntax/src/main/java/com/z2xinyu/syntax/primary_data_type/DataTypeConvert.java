package com.z2xinyu.syntax.primary_data_type;

/**
 * 基本数据类型转换
 *
 * @author zxy
 */
public class DataTypeConvert {
    public static void main(String[] args) {
        // 定义时接受的是一个int型数值，会自己做一个数据检测，在范围内就不会报错
        byte b1 = 20;
        // 强制类型转换
        byte b2 = (byte) 128;
        // -128
        System.out.println(b2);

        byte b3 = 12;
        byte b4 = 14;
//        byte b5 = b3 + b4; // 会转换成int计算
        // 先当作常量运算
        byte b5 = 12 + 14;
    }
}
