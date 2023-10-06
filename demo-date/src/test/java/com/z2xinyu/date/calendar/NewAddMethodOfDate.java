package com.z2xinyu.date.calendar;

import java.time.LocalDateTime;
import java.util.Date;

public class NewAddMethodOfDate {
	public static void main(String[] args) {
		Date d = new Date();
		 LocalDateTime ldt = LocalDateTime.parse("2019-02-18T23:15:30");
		 ldt.toLocalDate();
	}
}
