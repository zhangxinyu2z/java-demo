import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.*;

/**
 * @author zhangxinyu
 * @since 2022/9/22 17:50
 */
public class GoogleGuavaTest extends TestCase {

    public void testLists() {
        List<List<String>> headList = new ArrayList<>();
        // 代替创建List对象
        headList.add(Lists.newArrayList("姓名"));
        headList.add(Lists.newArrayList("性别"));
        headList.add(Lists.newArrayList("年龄"));
        headList.add(Lists.newArrayList("月份", "1月"));
        headList.add(Lists.newArrayList("月份", "2月"));
    }

    public void testConvertUtils() {
        String s = "92,113,31,31";

        Optional<Integer[]> optional =
            Optional.of(s).map(s1 -> (Integer[])ConvertUtils.convert(s1.split(","), Integer.class));
        optional.ifPresent(integers -> System.out.println(Arrays.toString(integers)));

        Integer[] intArray = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(intArray));

    }
}
