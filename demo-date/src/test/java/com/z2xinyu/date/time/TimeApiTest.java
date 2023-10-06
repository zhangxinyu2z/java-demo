package com.z2xinyu.date.time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;

/**
 * java.time.* api测试
 *
 * @author zhangxinyu
 * @since 2022/5/30 10:12
 */
public class TimeApiTest {
    private final DateTimeFormatter yyyyMM = DateTimeFormatter.ofPattern("yyyy-MM");
    private final DateTimeFormatter yyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void demo() {
        LocalDate now = LocalDate.now();
        LocalDateTime of = LocalDateTime.of(2022, 5, 30, 10, 22, 10);
    }


    /**
     * 解析年月格式String为LocalDate，报java.time.format.DateTimeParseException
     * 解决方式：
     */
    @Test
    public void convertLocalDate() {
        // 方式一
        YearMonth ym = YearMonth.parse("2020-04", yyyyMM);
        LocalDate dt1 = ym.atEndOfMonth();
        LocalDate localDate = ym.atDay(12);
        System.out.println();

        // 方式二
        DateTimeFormatter yyyyMM = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        LocalDate dt2 = LocalDate.parse("2020-04", yyyyMM);
    }

    @Test
    public void testLocalDate() {
        //        LocalDate localDate = LocalDate.of(1900, 1, 1);
        //        LocalDate localDate1 = localDate.plusDays(Integer.parseInt(recommendedDate) - 2);
        //        LocalDateTime localDateTime = localDate1.atTime(13, 23, 43);
        //        recordDetailDto.setRecommendTime(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));


        boolean after = LocalDate.parse("2022-07-31").isAfter(LocalDate.now());
        System.out.println(after);

    }

    @Test
    public void testCalender() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Calendar cld = Calendar.getInstance();
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        cld.setTimeInMillis(System.currentTimeMillis());//当前时间

        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        System.out.println(df.format(cld.getTime()));

        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
        System.out.println(df.format(cld.getTime()));


        //        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM");
        //        LocalDate endMonth = YearMonth.parse("2022-06", f).atEndOfMonth();
        //        LocalDate nowYearStart = LocalDate.of(endMonth.getYear(), 1, 1);
        //        LocalDateTime nowYearEnd = LocalDateTime.of(endMonth.getYear(), endMonth.getMonthValue(), endMonth.getDayOfMonth(), 23, 59, 59);
        LocalDateTime monday = LocalDateTime.of(LocalDate.now().with(DayOfWeek.MONDAY), LocalTime.MIN);//周一的00:00
        LocalDateTime sunday = LocalDateTime.of(LocalDate.now().with((DayOfWeek.SUNDAY)), LocalTime.MAX);//周五的23:59
    }

    @Test
    public void cc() {}
}
