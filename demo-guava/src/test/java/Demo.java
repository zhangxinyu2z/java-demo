import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author Arnoer
 * @since 2022/9/29 17:19
 */
public class Demo {
    @Test
    public void tt() {

        double  comprehensiveIndRatio = 0.217;
        BigDecimal two = new BigDecimal(comprehensiveIndRatio * 100.0);
        int i = two.setScale(0, RoundingMode.DOWN).intValue();
        System.out.println(i);
    }

    @Test
    public void tls() {

        Arrays.stream(new String[]{"a", "b", "c", "d","a"}).distinct().forEach(i-> {
            System.out.println(i);
        });

        ImmutableSet<String> separatorSet = ImmutableSet.of(",", "，", "：", ":", "、", "。","\\|");
        String join = String.join("|", separatorSet);
        String[] split = "dafa@qq.com,d2".split(String.join("|", separatorSet));
        System.out.println(split);
    }
}
