import org.junit.Test;
import org.xbill.DNS.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 *
 * @link https://github.com/dnsjava/dnsjava/blob/master/EXAMPLES.md
 *
 * @author zhangxinyu
 * @date 2023/7/3
 **/
public class DnsJavaTest {

    @Test
    public void test() throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName("8.8.8.8");
        Arrays.asList(addresses).forEach(add->{
            System.out.println(add.getHostName());
        });
    }

    @Test
    public void testDns() {
        try {
            // 设置要查询的域名
            String domain = "dnsjava.org.";

            // 创建DNS解析器
            Resolver resolver = new SimpleResolver("8.8.8.8");

            Record queryRecord = Record.newRecord(Name.fromString(domain), Type.A, DClass.IN);
            Message queryMessage = Message.newQuery(queryRecord);
            // 执行DNS查询
            Message message = resolver.sendAsync(queryMessage).whenComplete((answer, ex) -> {
                if (ex == null) {
                    System.out.println(answer);
                } else {
                    ex.printStackTrace();
                }
            }).toCompletableFuture().get();

            System.out.println(message);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试假冒搜索引擎user-agent的恶意扫描IP。
     *
     * 举例：通过dns反查ip，验证是否是百度蜘蛛的use-agent
     * visit <a href="https://www.coderbbb.com/articles/42">使用dns java反查ip</a>
     * visit <a href="https://baijiahao.baidu.com/s?id=1590278248202345992&wfr=spider&for=pc">什么是百度蜘蛛</a>
     *
     */
    @Test
    public void testIp() {
        try {
            String hostName = Address.getHostName(InetAddress.getByName("220.181.108.75"));
            String hostName2 = Address.getHostName(InetAddress.getByName("183.188.169.224"));

            System.out.println(hostName);
            System.out.println(hostName2);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
