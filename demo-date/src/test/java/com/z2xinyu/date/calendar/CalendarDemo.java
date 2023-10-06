package com.z2xinyu.date.calendar;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class CalendarDemo {
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		System.out.println("输入你的出生日期");
		String birthday = sc.nextLine();
		Date d = DateUtils.stringToDate(birthday, "yyyy-MM-dd");
		
		long myTime = d.getTime();
		long nowTime = System.currentTimeMillis();
		long time = nowTime - myTime;
		long day = time / 1000 / 60 / 60/ 24;
		System.out.println(day);
		
	}
}
