package com.z2xinyu.date.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author xinyu.zhang
 * @since 2022/11/13 20:42
 */
public class LocalDateTest {

    /**
     * 给定字符串，获取00:00:00到23:59:59
     */
    @Test
    public void getDateRange() {
        String date = "2021-07-09";
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 具体加不加“T”是根据业务来的，看第三方接口需不需要。
        String start3 = LocalDateTime.of(localDate, LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        String end3 = LocalDateTime.of(localDate, LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println(start3);
        System.out.println(end3);

    }

    /**
     *   Period 计算日期间隔天数  注意 2012-12-02  2013-12-22  间隔 1年20天  取的是天数 而不是365+20
     *
     * https://blog.csdn.net/weixin_65440201/article/details/125483328
     * https://blog.csdn.net/weixin_45817985/article/details/126503257
     * https://blog.csdn.net/weixin_46876034/article/details/127425884
     * */
    @Test
    public void testMeorient() {
        String exhibitionTime= "20230118";

        LocalDate now = LocalDate.now();
        now =  LocalDate.parse("20221115", DateTimeFormatter.ofPattern("yyyyMMdd"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate infoShowTime = LocalDate.parse(exhibitionTime, DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDate noticeBefore = now.plusDays(70);
        LocalDate noticeAfter = now.plusDays(61);


        boolean b = (infoShowTime.isAfter(noticeAfter) && infoShowTime.isBefore(noticeBefore) )|| infoShowTime.isEqual(noticeBefore) || infoShowTime.isEqual(noticeAfter);
        if(b) {
            System.out.println("now : "   + dtf.format(now)  +" infoshowtime : 2023-01-12" + ",after: " + dtf.format(noticeAfter) + ",before: " + dtf.format(noticeBefore));

            int noticeDay = Period.between(now.plusDays(60), infoShowTime).getDays();
            // 还有多少天
            System.out.println("noticeDay : " + noticeDay);
        }

        LocalDate startDate = now.plusDays(7);
        LocalDate endDate = now.plusDays(60);

        if((infoShowTime.isAfter(startDate) && infoShowTime.isBefore(endDate)) || infoShowTime.equals(startDate) || infoShowTime.equals(endDate)) {
            System.out.println("now : "   + dtf.format(now)  +" infoshowtime : 2023-01-12" + ",startDate: " + dtf.format(startDate) + ",endDate: " + dtf.format(endDate));
            System.out.println("已经开始自约邀请");
        }
    }

    @Test
    public void testTimeBetween() {
         LocalDateTime before = LocalDateTime.of(LocalDate.of(2021, 01, 1), LocalTime.MIN);
        LocalDateTime now = LocalDateTime.of(LocalDate.of(2024, 8, 13), LocalTime.MAX);

        System.out.println(Duration.between(before, now).toDays());

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDate endMonth = YearMonth.parse("2022-12", f).atEndOfMonth();

        LocalDate nowYearStart = LocalDate.of(endMonth.getYear(), 1, 1);
        LocalDateTime nowYearEnd = LocalDateTime.of(endMonth.getYear(), endMonth.getMonthValue(), endMonth.getDayOfMonth(), 23, 59, 59);

        YearMonth with = YearMonth.parse("2022-11", f);//.with(TemporalAdjusters.firstDayOfMonth());
        //        YearMonth with = YearMonth.parse("2022-11", f).with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(with.getYear()+ "; " +  with.getMonthValue());
    }

}
