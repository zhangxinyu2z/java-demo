import com.sun.deploy.util.StringUtils;
import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangxinyu
 * @since 2022/9/22 18:37
 */
public class StringUtilTest extends TestCase {

    public static String[] init() {
       return new String[] {"I ", "want to ", "splicing ", "some ", "characters ", null, ""};
    }

    /**
     * 字符串拼接
     * <p>
     * 1. +  String是不可变对象，+号就会在内存中创建新的String，但是拼接变量，编译器会优化为StringBuilder
     * 2. concat  形参不能为null
     * 3. StringBuilder
     * 4. StringJoiner                  1.8
     * 5. Stream流的collect : Collectors.joining      1.8  如果不拼接null的元素，需要filter过滤
     * 6. String.join()  数组或者Iterable的子类        1.8
     * 7. commons.lang3.StringUtils.join()      不会拼接为null的元素
     */
    public void testStringSplicing() {
        String[] strings = StringUtilTest.init();

        // StringJoiner
        StringJoiner stringJoiner = new StringJoiner(""); // 可以指定连接符、前缀、后缀
//         stringJoiner = new StringJoiner(",", "start ", "end");
        for (String value : strings) {
            stringJoiner.add(nullToString(value));
        }
        System.out.println("test StringJoiner：" + stringJoiner);

        //Collectors.joining
        String result = Arrays.stream(strings).filter(Objects::nonNull).collect(Collectors.joining());
        System.out.println("test Collectors.joining：" + result);

        //String.join
        String join = String.join(",", strings); // String添加分隔符的API,内部逻辑也使用了StringJoiner
        System.out.println("test String.join：" + join);


        // StringUtils.join
        String join1 = StringUtils.join(strings, ","); // 对于null元素不会拼接，底层是使用StringBuilder
        System.out.println("test commons-lang3, StringUtils.join：" + join1);

        String join2 = StringUtils.join(strings, '1', 2, 5);     // 拼接，使用separator, 索引从startIndex -> endIndex
        System.out.println("test commons-lang3, StringUtils.join：" + join2);

    }

    public String nullToString(String value) {
        return value == null ? "" : value;
    }
}
