import junit.framework.TestCase;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhangxinyu
 * @since 2022/9/22 18:21
 */
public class NumberUtilTest extends TestCase {

    public void testRandom() {
        // 生成100个[0,100)间的随机数
        List<Integer> nums = new Random().ints(0, 100).limit(100).boxed().collect(Collectors.toList());
        System.out.println(nums);
    }
}
