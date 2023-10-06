package com.z2xinyu.lambda.common;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 函数主体规则：
 * <ol>
 *     <li>参数的类型可以省略，多个参数必须同时省略（因为接口方法中的参数类型，lambda可以自动推断类型）</li>
 *     <li>如果参数只有1个，小括号可以省略</li>
 *     <li>如果代码块只有1条语句，可以省略大括号和分号，如果有return，也可以省略</li>
 * </ol>
 *
 * @author zhangxinyu
 * @date 2023/5/4
 **/
public class LambdaBaseTest extends TestCase {

    public void test() {
        primitiveWay();
        optimizeWay();
        stillOptimizeWay();
        latestWay();
    }

    public void primitiveWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort(new Comparator<String>() {
            /*compare方法如何决定是升序还是降序？
                如果a.compreTo(b)大于0，交换元素，小于0则不交换，即码值大的元素放在后面：升序
            反之，b.compareTo(a)>0,码值大的元素被放置到前：降序
            比较的是相邻的元素，索引是，j-1作为this，代表参数a，j作为other，代表参数b
            仅用List集合测试，使用Arrays.sort来排序，跟踪到归并排序算法，排序的前后取决于排序算法*/
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
    }

    public void optimizeWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort((String a, String b) -> {
            return b.compareTo(a);
        });
    }

    public void stillOptimizeWay() {
        Arrays.asList("peter", "anna", "mike", "xenia").sort((a, b) -> a.compareTo(b));
    }

    public void latestWay() {
        // 方法引用
        Arrays.asList("peter", "anna", "mike", "xenia").sort(String::compareTo);
    }
}
