 package com.z2xinyu.date.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

 public class DateUtils {
      private DateUtils() {

      }

      public static String dateToString(Date date, String format) {
          return new SimpleDateFormat(format).format(date);
      }

      public static Date stringToDate(String date, String format) throws ParseException {
          return new SimpleDateFormat(format).parse(date);
      }
 }
