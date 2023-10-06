package com.z2xinyu.optional;

import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 随机测试
 *
 * @author zhangxinyu
 * @since 2022/5/31 11:32
 */
public class AllDemoTest {

    @Test
    public void tesl() {

        String str = "Booth No. 114234";
        String result = str.substring("Booth No. ".length());
        System.out.println(result);

        Boolean ifAbsent  = null;
        boolean equals = Boolean.FALSE.equals(ifAbsent);


        String email = "gxq13750894985@163.com";
        int index = email.toLowerCase().indexOf("@");
        if(index == -1) {
        }
        String suffix = email.substring(index + 1);
        String whiteEmailSuffix = "163,126,Meorient,meo,qq,sina,souhu,tom,test";
        List<String> lls =
            Arrays.stream(whiteEmailSuffix.split(",")).map(String::toLowerCase).collect(Collectors.toList());

        boolean b = lls.stream().anyMatch(suffix::contains);
        BigDecimal bigDecimal = new BigDecimal("1200.32");


        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String formattedString = numberFormat.format(10032);
        System.out.println(formattedString);
    }

    @Test
    public void testDecimal() {
        double d = 538.8;
        //        System.out.println(d * 100);
        List<Double> doubles = Arrays.asList(12.4, 538.8, 12.7, 20.22);
        double sum = doubles.stream().mapToDouble(x -> x).sum();
        System.out.println(sum);
        BigDecimal reduce = doubles.stream().map(t -> new BigDecimal(Double.toString(t))).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce.doubleValue());
        //        System.out.println(new BigDecimal(Double.toString(d)).add(new BigDecimal("100")));
    }

    @Test
    public void testDecimalFormat() {
        // 格式化百分数
        String format = new DecimalFormat("0.00%").format(BigDecimal.ZERO);
        System.out.println(format);

        // 保留两位小数
        String format1 = String.format("%.2f", Math.random());
        String format3 = new DecimalFormat("#.##").format(333.0 / 417.0);
        String format2 = new DecimalFormat("0.00").format(Math.random() * 10);

        // Math.random()是令系统随机选取大于等于 0.0 且小于 1.0 的伪随机 double值  Math.floor() 返回小于参数x的最大整数,即对浮点数向下取整
        double v1 = (Math.floor(Math.random() * 10) + 1) * 10;
        int i = new Random().nextInt(9900000) + 100000;
        // 格式化 1,123,124
        DecimalFormat df = new DecimalFormat("#,###");
        String format4 = df.format(new Double(i));
        System.out.println(format4);
    }

    @Test
    public void testGetPercent() {
        double testNumber = getPercentage(33, 111, 2);
        System.out.println(testNumber);
    }

    /**
     * 用于求百分比方法，已考虑除数不为0的情况
     *
     * @param number1 除数
     * @param number2 被除数
     * @param formatNumber 保留几位小数
     * @return 百分比数值
     */
    public double getPercentage(double number1, double number2, int formatNumber) {
        double number100;
        if (number1 != 0 && number2 != 0) {
            //到报率求%
            double numberA = number1 / number2 * 100;
            //四舍五入保留2位小数
            number100 = new BigDecimal(numberA).setScale(formatNumber, RoundingMode.HALF_UP).doubleValue();
            return number100;
        } else {
            number100 = 0.0;
            return number100;
        }
    }

    @Test
    public void swapCollectionElementTest() {
        // 交换list中的元素索引
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(24);
        list.add(51);
        // 方式一
        //        Collections.swap(list,0, list.indexOf(51));
        // 方式二
        list.add(0, list.remove(list.indexOf(51)));
        System.out.println(list);
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

    @Test
    public void testMap() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        HashMap<Object, Object> m = Maps.newHashMapWithExpectedSize(9);
        //指定初始容量15来创建一个HashMap
        //        HashMap m = new HashMap(8);
        //获取HashMap整个类
        Class<?> mapType = m.getClass();
        //获取指定属性，也可以调用getDeclaredFields()方法获取属性数组
        Field threshold = mapType.getDeclaredField("threshold");
        //将目标属性设置为可以访问
        threshold.setAccessible(true);
        //获取指定方法，因为HashMap没有容量这个属性，但是capacity方法会返回容量值
        Method capacity = mapType.getDeclaredMethod("capacity");
        //设置目标方法为可访问
        capacity.setAccessible(true);
        //打印刚初始化的HashMap的容量、阈值和元素数量
        System.out.println("容量：" + capacity.invoke(m) + "    阈值：" + threshold.get(m) + "    元素数量：" + m.size());
        for (int i = 0; i < 9; i++) {
            m.put(i, i);
            //动态监测HashMap的容量、阈值和元素数量
            System.out.println("容量：" + capacity.invoke(m) + "    阈值：" + threshold.get(m) + "    元素数量：" + m.size());

        }
    }

    /**
     * 泰州 添加权限
     */
    @Test
    public void testPermission() {
        // 2086869246,298057264,17476 superman
        // 2086869246,298057264,17476 zhangxinyu   -> 16532548
        //        18
        //        19
        //        20
        //        21
        //        22
        //        23
        addPermissions(17476, 18, 19, 20, 21, 22, 23);

    }

    public void addPermissions(int permissions, int... b) {
        int[] ints = Arrays.stream(b).toArray();
        int j = permissions;
        System.out.println(j);
        for (int i = 0; i < ints.length; i++) {
            j = addPermission(j, ints[i]);
            System.out.println(j);
        }

        System.out.println("---------------------------");
    }

    public Integer removePermission(int permissions, int permission) {
        permissions = (permissions | (1 << permission)) - (1 << permission);
        return permissions;
    }

    public boolean hasPermission(int permissions, int permission) {
        return ((1L << permission) & permissions) > 0;
    }

    public Integer addPermission(int permissions, int permission) {
        permissions = permissions | (1 << permission);
        return permissions;
    }

    /**
     * hutool 分页
     */
    @Test
    public void testPage() {
        List<User> users = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            User user = new User(j, 100 + j);
            users.add(user);
        }

        // 返回第0页，每页大小为5条数据
        int pageNo = -2;
        int pageSize = 5;
        List<User> results = ListUtil.page(pageNo, pageSize, users);
        System.out.println(results);

    }

    @Test
    public void tc() {
        Integer s = null;
        System.out.println(s > 10);

        System.out.println("".substring(1));

    }

    @Test
    public void tl() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        System.out.println(dtf.format(LocalDate.now()));
        System.out.println(LocalDate.now().format(dtf));

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);//把日期往后增加一天.整数往后推,负数往前移动
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));

        System.out.println(Period.between(LocalDate.of(2023, 2, 25), LocalDate.of(2023, 3, 1)).getDays());
    }

/*    @Test
    public void testCompleteFurtuer() {
        String s1= null;
        String s2= null;
        String s3  = null;
        List<String> collect = Stream.of(s1, s2, s3).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        String join = String.join(",", collect);

        //        List<User> users = new ArrayList<User>(Arrays.asList(new User(110, 10), new User(120, 23), new User(139, 22)));
        //        CompletableFuture.allOf((CompletableFuture<?>)users).thenApply(b-> {
        //
        //        })
//
//        String[] split = "".split(",");
//        String[] a = {"a", "b"};
//        List<String> convert = Convert.convert(new TypeReference<List<String>>() {
//        }, split);
//        System.out.println(convert.size());

//        List<String> ss = Stream.of("12", null, "", "234").filter(StringUtils::isNotBlank).collect(Collectors.toList());
//        System.out.println(String.join(",", ss));

        List<User> users = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            User user = new User(j, 100 + j);
            users.add(user);
        }

        users = null;


        Map<Integer, List<User>> integerListMap = EntityUtils.groupBy(CollectionUtil.isNotEmpty(users) ? users : Collections.emptyList(), User::getId);
        List<User> users1 = integerListMap.get(null);
        System.out.println("helo");

    }*/


    @Test
    public void tcl() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0 ; i< 10; i++) {
            User user = new User(i, 200);
            users.add(user);
        }
        users.add(new User(null, 123));
        users.add(new User(null, 123));

        List<Integer> collect = users.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(collect);
    }
}
