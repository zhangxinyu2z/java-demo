package com.z2xinyu.optional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityUtils {
    private EntityUtils() {
    }

    /**
     * <p>对集合中元素按照指定列进行分组</p>
     * <p>返回值是{@code Map}，其中Key为分组列，Value为当前元素</p>
     *
     * @param list    集合实例
     * @param gColumn 分组列（方法引用表示）
     * @param <E>     集合元素泛型
     * @param <G>     分组列数据类型泛型
     * @return {@code Map}实例
     * @since 1.6.0
     */
    public static <E, G> Map<G, List<E>> groupBy(final Collection<E> list, final Function<E, G> gColumn) {
        Objects.requireNonNull(gColumn);
        if (Objects.nonNull(list)) {
            return list.stream().collect(Collectors.groupingBy(gColumn));
        }
        return Collections.emptyMap();
    }

    /**
     * <p>对集合中元素按照指定列进行分组</p>
     * <p>返回值是{@code Map}，其中Key为分组列</p>
     *
     * @param list    集合实例
     * @param gColumn 分组列（方法引用表示）
     * @param action  转换行为
     * @param <U>     Value集合元素类型泛型
     * @param <E>     集合元素泛型
     * @param <G>     分组列数据类型泛型
     * @return {@code Map}实例
     * @since 1.6.0
     */
    public static <E, G, U> Map<G, List<U>> groupBy(final Collection<E> list, final Function<E, G> gColumn, final Function<E, U> action) {
        Objects.requireNonNull(gColumn);
        if (Objects.nonNull(list)) {
            return list.stream().collect(Collectors.groupingBy(gColumn, Collectors.mapping(action, Collectors.toList())));
        }
        return Collections.emptyMap();
    }
}
