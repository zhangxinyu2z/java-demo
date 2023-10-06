package com.z2xinyu.syntax.primary_data_type;

/**
 * float和double的精度问题
 *
 * @author zxy
 */
public class BigDecimal {
    public static void main(String[] args) {
        // 0.09999999999999999
        System.out.println(0.09 + 0.01);
        // 0.6799999999999999
        System.out.println(1 - 0.32);
        // float类型的数据存储和整数是不一样的，大部分都是带有效数字位
        //	float和double很容易丢失精度，提供了BigDecimal
        java.math.BigDecimal b = new java.math.BigDecimal(0.09);
        java.math.BigDecimal b2 = new java.math.BigDecimal(0.01);
//        0.09999999999999999
        System.out.println(b.add(b2).doubleValue());
//        0.09999999999999999687749774324174723005853593349456787109375
        System.out.println(b.add(b2));

    }
}
