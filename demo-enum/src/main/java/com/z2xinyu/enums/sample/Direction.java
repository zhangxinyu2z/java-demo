package com.z2xinyu.enums.sample;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 枚举类的实例是有限的个数
 *
 * @author zhangxinyu
 * @date 2023/8/27
 **/
@Data
@AllArgsConstructor
public class Direction {

    /** 不允许修改固定的实例 */
    public static final Direction FRONT = new Direction("前");
    public static final Direction BEHIND = new Direction("后");
    public static final Direction LEFT = new Direction("左");
    public static final Direction RIGHT = new Direction("右");

    /** 私有构造,就无法再创建该类的实例,有限个数 */
    // private Direction01() {} // 这种是空参数构造

    /** 提供成员变量 */
    private String name;
}
