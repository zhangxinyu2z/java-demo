package com.z2xinyu.lambda.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * > https://www.ripjava.com/article/1334372345053216
 * 流具有三个部分：数据源，零个或多个中间操作以及零个或一个终端操作。
 * <p>
 * 数据源将元素提供给管道。
 * <p>
 * 中间操作会逐一获取元素并进行处理。
 * <p>
 * 所有中间操作都是惰性的，因此，在管道开始工作之前，所有操作都不会起作用。
 * <p>
 * 终端操作意味着流生命周期的结束。最重要的是，它们启动了管道中的工作。
 * <p>
 * 出于性能考虑，stream 被设计为“元素只有在最终操作需要时才会被处理”。如果没有最终操作的“拉动”，那么 stream 中就没有操作会真正执行。
 * <p>
 * 在上面的例子中，.collect(Collectors.toList()) 就是一个最终操作，而且这个操作会“拉动”所有元素。这样一来，每个元素都一定会被应用 peek() 方法，所以放在这里没有问题。
 * <p>
 * 但在使用 peek() 时仍然需要注意，因为它只保证作用于流经管道的元素，但并不保证全部元素都会流经管道。
 *
 * @author zhangxinyu
 * @since 2022/8/18 15:15
 */
public class TestStream extends TestCase {

    /**
     * 流中的元素只要有一个满足Predicate表达式就会返回true,根据peek的日志输出，可见anyMatch是一个短路操作
     */
    public void testAnyMatch() {
        boolean boo = Stream.of(2, 5, 8, 9, 4, 20, 11, 43, 55).peek(System.out::println).anyMatch(a -> a > 5);

        System.out.println("\n" + boo);
    }

    public void testApache() {

        String[] attr1 = new String[] {"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[] {"1", "2", "3", "A"};
        List<String> list1 = Arrays.asList(attr1);
        List<String> list2 = Arrays.asList(attr2);
        Collection<String> intersection = CollectionUtils.intersection(list1, list2);

        System.out.println("交集：" + CollectionUtils.intersection(list1, list2)); // 交集
        System.out.println("补集：" + CollectionUtils.disjunction(list1, list2)); // 补集
        System.out.println("并集：" + CollectionUtils.union(list1, list2)); // 并集
        System.out.println("list1的差集：" + CollectionUtils.subtract(list1, list2)); // list1的差集
        System.out.println("list2的差集：" + CollectionUtils.subtract(list2, list1)); // list2的差集



    }

    @Data
    public  class User {
        private Integer id;

        private Integer score;

        public User() {}

        public User(Integer id, Integer score) {
            this.id = id;
            this.score = score;
        }
    }


    public void testGroupBy() {

        ArrayList<User> users = new ArrayList<>();

        for(int i =1 ;i <=100;i++) {
            User user = new User();
            user.setId(i);
            user.setScore(100);
            users.add(user);
        }

        users.add(new User(12,100));
        users.add(new User(13, 1000));
        users.add(new User(12, 1000));

        Map<Integer, Long> collect = users.stream().collect(Collectors.groupingBy(user -> user.getId(), Collectors.counting()));

        System.out.println(collect);

    }

    @Data
    public static class PurchaserRes {
        private String purchaseId;

        private String companyId;
    }


    public void testDD() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        String s4 = stringStringHashMap.get(null);

        String x = "{\n" + "  \"data\": [\n" + "    {\n" + "      \"company_id\": \"CO00018A5EFE3C419B83A8CB8632949ADC\",\n" + "      \"purchase_id\": \"100402828469\"\n"
            + "    }\n" + "  ],\n" + "  \"errCode\": 0,\n" + "  \"requestId\": \"BC940541-CA46-4235-A71C-59399BE34B8D\",\n" + "  \"errMsg\": \"success\",\n"
            + "  \"apiLog\": null\n" + "}";

        JSONObject jsonObject = JSON.parseObject(x);

        String data = jsonObject.getString("data");
//        List<PurchaserRes> purchaserRes = JSON.parseArray(Collections.emptyList(), PurchaserRes.class);

        List<HashMap> maps = JSONObject.parseArray(data, HashMap.class);

//        Map<String , List<HashMap<String, String>>> purchaser_id = maps.stream().collect(Collectors.groupingBy(o -> o.get("purchaser_id")));
//


        HashMap<String, List<String>> ll = new HashMap<>();
        List<String> strings = Arrays.asList("1341", "313411", "1q4124");
        ll.put("purchaserIds", strings);
        String s3 = JSON.toJSONString(ll);

        LocalDateTime now = LocalDateTime.now();

        int dayOfMonth = now.getDayOfMonth();

        LocalDateTime localDateTime = now.minusDays(15);



        HashMap<String, CustomerBuyerDto> mp = new HashMap<>();
        CustomerBuyerDto customerBuyerDto = mp.get("");

        String s1 = null;
        String s2 = null;

        List<String> socialUrl = Stream.of(s1, s2).filter(StringUtils::isNotBlank).collect(Collectors.toList());


        List<String> validUrls =
            Stream.of(String.join(",", socialUrl)).filter(item -> item.contains("facebook") || item.contains("linkedin") || item.contains("twitter"))
                .collect(Collectors.toList());

        //        ArrayList<User> users = new ArrayList<>();
//        for (int i = 0 ; i< 10; i++) {
//            User user = new User(i, 200);
//            users.add(user);
//        }
//        users.add(new User(null, 123));
//        users.add(new User(null, 123));
//
//        List<Integer> collect = users.stream().map(User::getId).collect(Collectors.toList());
//        System.out.println(collect);

        String s= null;
//        List<String> socialUrl = Optional.ofNullable(s).map(x -> Arrays.asList(x.split(","))).orElse(Collections.emptyList());

        List<String> facebook = socialUrl.stream().filter("facebook"::equals).collect(Collectors.toList());
        List<String> linkedin = socialUrl.stream().filter("linkedin"::equals).collect(Collectors.toList());
        List<String> twitter = socialUrl.stream().filter("twitter"::equals).collect(Collectors.toList());

        CustomerBuyerDto buyerDto = new CustomerBuyerDto();
        buyerDto.setSocialUrl("https://www.faceb.com/MiddleEastTechnicalUniversity,http://www.linkedin.com/company/institute-of-marine-sciences-metu/,http://twitter.com/METUFLE");

        buyerDto.setSoureName("");

//        CustomerBuyerDto finalBuyerDto = buyerDto;
//        Optional.ofNullable(buyerDto.getSocialUrl()).map(item -> Arrays.asList(item.split(","))).ifPresent(urls->{
//            List<String> validUrls = urls.stream().filter(item -> item.contains("facebook") || item.contains("linkedin") || item.contains("twitter")).collect(Collectors.toList());
//            finalBuyerDto.setSocialUrl(String.join(",", validUrls));
//        });
//
//        System.out.println(buyerDto);


    }

    public void testLL() throws ParseException {
//         2022-07-15 09:30:44,2022-07-15 09:32:41
//         2022-07-15 04:30:44,2022-07-15 04:32:41
//
//        SimpleDateFormat df=new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
//        Date date=df.parse("Fri Jul 15 04:32:41 CST 2022");
//        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("==========="+sf2.format(date));
        LocalDateTime date1 = LocalDateTime.parse("2020-07-19 02:30:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime date2 = LocalDateTime.parse("2020-07-18 13:30:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Duration between = Duration.between(date1, date2);
        long l = between.toHours();
        System.out.println(l);



    }
}
