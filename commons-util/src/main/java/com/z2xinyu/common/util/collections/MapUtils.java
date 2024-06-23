package com.z2xinyu.common.util.collections;

/**
 * @author zhangxinyu
 * @date 2024/6/13
 **/
public class MapUtils {

    /**
     * 计算合适的容量
     * @param size
     * @return
     */
    public static int calculateCapacity(int size) {
        int capacity = (int) Math.ceil(size / 0.75);
        return (int) Math.pow(2, Math.ceil(Math.log(capacity) / Math.log(2)));
    }
}
