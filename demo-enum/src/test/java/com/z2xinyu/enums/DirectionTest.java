package com.z2xinyu.enums;

import org.junit.Test;

/**
 * JDK1.5 enum
 *
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public class DirectionTest {
    @Test
    public void testMethod () {
        int result = Direction.FRONT.compareTo(Direction.BEHIND);
        // -1 比较枚举的顺序
        assert(result < 0);

        // 枚举的顺序，0开始
        assert(Direction.FRONT.ordinal() == 0);
        assert(Direction.BEHIND.ordinal() == 1);

        // 返回指定名称的指定类型的枚举常量
        assert(Enum.valueOf(Direction.class, "FRONT").equals(Direction.FRONT));
    }

    @Test
    public void testIterator() {
        for (Direction item : Direction.values()) {
            System.out.println(item.getName());
        }
    }
}