package com.z2xinyu.syntax.operator;

/**
 * 赋值运算符：= += -= /= %=
 *
 * @author zxy
 */
public class AssignmentOperator {
    public static void main(String[] args) {
        short s1 = 2;
//        s1 = s1 + 2;// s1会自动类型提升为int，参与运算后还是int,无法赋值给short类型
        short s2 = 3;
        // 等价于：s2 = (s2的数据类型)(s2 + 3)
        s2 += 3;
    }
}
