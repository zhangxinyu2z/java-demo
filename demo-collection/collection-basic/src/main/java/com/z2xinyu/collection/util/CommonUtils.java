package com.z2xinyu.collection.util;

/**
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class CommonUtils {

    public static <T> String print(T[] i) {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int x = 0; x < i.length; x++) {
            if (x == i.length - 1) {
                s.append(i[i.length - 1]);
            } else {
                s.append(i[x]).append(",");
            }
        }
        s.append("]");
        return s.toString();
    }
}
