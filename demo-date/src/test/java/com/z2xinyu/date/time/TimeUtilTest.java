package com.z2xinyu.date.time;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtilTest extends TestCase {

    /**
     * 时间格式转换工具
     *
     * @return 2022-09-04 08:56:35
     */
    public  void testTimeFormat() {
        try {
            String beforeTime = "2022-09-04T08:56:35.000+0000";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf1.parse(beforeTime);
            String format = sdf2.format(date);
            System.out.println(format);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public  void testTimeFormat2() {
        String oldDateStr = "2020-02-14T14:11:51.00+08:00";
        oldDateStr = "2020-12-14T14:11:51+08:00";
        try{
            if(!oldDateStr.contains(".")){
                oldDateStr = oldDateStr.replace("+",".00+");
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.UK);
            Date  date = df.parse(oldDateStr);
            Date date1 =  df1.parse(date.toString());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df2.format(date1);
            System.out.println(format);
        }catch(Exception e){

        }

    }

    /**
     * 获取某个月的第一天和最后一天
     */
    public void testGetFirstAndLastDay() {
        get1();
//        get2();
//        get3();
    }

    public void get1() {
        LocalDate localDate = LocalDate.now();
        //今天
        Date day = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("day now: " + day);
        //这个月的第一天
        Date monthStart =
                Date.from(localDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("month start: " + monthStart);
        //这个月的最后一天 下个月的第一天
        Date monthEnd =
                Date.from(localDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的第一天
        Date yearStart =
                Date.from(localDate.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的最后一天 下一年的第一天
        Date yearEnd =
                Date.from(localDate.plusYears(1).with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());


        System.out.println("----------------------------------------------------------------------------------");

        // LocalDateTime也可以使用此方法
        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth()); // 获取当前月的第一天
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
        System.out.println(date);
        System.out.println(firstDay);
        System.out.println(lastDay);
    }

    public void get2() {
        LocalDate date = LocalDate.of(2021, 8, 8);
        // 日期date减去这个月已有的天数，得到上个月的最后一天
        LocalDate lastMonthEnd = date.minusDays(date.getDayOfMonth());
        // 通过上一步得到的上个月的最后一天，得到上个月的第一天
        LocalDate lastMonthBegin = LocalDate.of(lastMonthEnd.getYear(), lastMonthEnd.getMonthValue(), 1);
        System.out.println(date);
        System.out.println(lastMonthBegin);
        System.out.println(lastMonthEnd);

    }

    public void get3() {
        String dateStr = "2022-12-12";
        String format = "yyyy-MM-dd";
        String firstDayOfGivenMonth = getFirstDayOfGivenMonth(dateStr, format);
        String firstDayOfNextMonth = getFirstDayOfNextMonth(dateStr, format);
        System.out.println(dateStr);
        System.out.println(firstDayOfGivenMonth);
        System.out.println(firstDayOfNextMonth);
    }

    /**
     * 获取指定日期当月的第一天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String getFirstDayOfGivenMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 0);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期下个月的第一天
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static String getFirstDayOfNextMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
