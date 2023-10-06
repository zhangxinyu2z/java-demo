package com.xcoding.multithread.concurrent;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhangxinyu
 * @date 2023/5/17
 **/
public class CountdownLatchTest {

    private static final List<String> company = Arrays.asList("东方航空", "南方航空", "海南航空");
    private static final List<String> fightList = new ArrayList<>();


    @Test
    public void testInquiryAirfare() throws InterruptedException {
        String origin = "BJ";
        String dest = "SH";
        Thread[] threads = new Thread[company.size()];
        CountDownLatch latch = new CountDownLatch(company.size());

        for (int i = 0; i < threads.length; i++) {
            String name = company.get(i);
            threads[i] = new Thread(() -> {
                System.out.printf("%s 查询从%s到%s的机票\n", name, origin, dest);
                //随机产生票数
                int val = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    fightList.add(name + "--" + val);
                    System.out.printf("%s公司查询成功！\n", name);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        // 一直阻塞，直到count为0或等待中的线程中断或等待超时
        latch.await();
        System.out.println("==============查询结果如下：================");
        fightList.forEach(System.out::println);

    }


    @Test
    public void testList() {
        List<Integer> list = Arrays.asList(null, 1, 2, 3, 4, 5);

        // 将 List<Integer> 转换为 Map<Integer, String>
        Map<Integer, String> map = list.stream()
            .collect(Collectors.toMap(
                integer -> integer, // key 为原始 Integer 值
                integer -> {
                    if (integer % 2 == 0) {
                        return "偶数";
                    } else {
                        return "奇数";
                    }
                }
            ));
    }

    @Test
    public void testTe() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 任务逻辑，可能抛出异常
            if (0.2< 0.5) {
                throw new RuntimeException("Something went wrong!");
            }
            return 42;
        });

        // 使用 exceptionally 方法处理异常
        CompletableFuture<Integer> result = future.exceptionally(ex -> {
            System.err.println("Exception occurred: " + ex.getMessage());
            return 0; // 提供默认值或处理异常情况
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 任务逻辑，可能抛出异常
            if (0.3 < 0.5) {
                throw new RuntimeException("hjajaajajjajaj");
            }
            return 42;
        });

        CompletableFuture<Integer> result2 = future2.exceptionally(ex -> {
            System.err.println("Exception occurred: " + ex.getMessage());
            return 0; // 提供默认值或处理异常情况
        });

        // 等待所有 CompletableFuture 完成
        CompletableFuture.allOf(result, result2).join();

        // 获取结果（处理异常后的值）
        int value = result.join();
        System.out.println("Result: " + value);
    }

    @Test
    public void testDate() throws ParseException {

        List<String> stringList = Arrays.asList(null, null,"dafasf");

        String s = stringList.stream().filter(Objects::nonNull).findFirst().orElse(null);

        Calendar instance = Calendar.getInstance();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            instance.setTime(dateFormat.parse("20231223"));
            String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
            System.out.println(yyyyMMdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDate localDate = instance.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        String preThirty = now.plusDays(30).format(formatter);
        String preSixty = now.plusDays(60).format(formatter);
        String preNineTy = now.plusDays(90).format(formatter);

        SimpleDateFormat df = new SimpleDateFormat("d MMMM yyyy", Locale.forLanguageTag("en"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = df.format(dateFormat.parse("20231224"));
        System.out.println(format);

        DateTimeFormatter enFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("id_id"));
        String yyyyMMdd = LocalDate.parse("20231224", DateTimeFormatter.ofPattern("yyyyMMdd")).format(enFormatter);
        System.out.println(yyyyMMdd);
    }

    @Test
    public void tesTStream() {
        List<Object> sk = null;

        List<Object> bo = (List<Object>) sk;

        System.out.println(bo);
        

        System.out.println(System.getProperty("java.io.tmpdir") );

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("hang", 13));
        users.add(new User("primary", 15));
        users.add(new User("hang", 13));
        users.add(new User("hang", 13));
        users.add(new User("primary", 15));

        List<User> sortedList = users.stream()
            .sorted(Comparator.comparing(dto -> dto.getName().equals("primary") ? 0 : 1))
            .collect(Collectors.toList());


        System.out.println(sortedList);

    }

    class User {
        private String name;

        private Integer age;

        public User() {
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
