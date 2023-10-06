package com.z2xinyu.string.common;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author zhangxinyu
 * @date 2023/8/14
 **/
public class StringOperateTest {

    /**
     * 拼接字符串的几种方式：
     * <ol>
     * <li>jdk1.8String类新增的api：java.lang.String#join(java.lang.CharSequence, java.lang.CharSequence...)</li>
     * <li>jdk1.8新增的收集器的api：java.util.stream.Collectors#joining(java.lang.CharSequence)</li>
     * <li>guava工具包中的joiner的api</li>
     * </ol>
     */
    @Test
    public void joinTest() {
        String result = String.join(",", "a", "b", "c");
        assertThat(result).isEqualTo("a,b,c");
        // ==============================================================================================
        String result2 = Stream.of("a", "b", "c").collect(Collectors.joining(",", "[", "]"));
        assertThat(result2).isEqualTo("[a,b,c]");
        // ==============================================================================================
        String join1 = Joiner.on(",").join(Arrays.asList("a", "b", "c").toArray());
        assertThat(join1).isEqualTo("a,b,c");
        String join2 = Joiner.on(",").join("a", "b", "e", "f", "g");
        assertThat(join2).isEqualTo("a,b,e,f,g");
        // joiner会把null作为字符串
        Joiner joiner = Joiner.on(",").skipNulls();
        String join3 = joiner.join("a", "b", null, "d");
        assertThat(join3).isEqualTo("a,b,d");
        String join4 = Joiner.on(",").useForNull("instead").join("a", "b", null, "d");
        assertThat(join4).isEqualTo("a,b,instead,d");
    }
}
