/*
 * Created on 2006. 6. 2.
 *
 * Copyright (c) 2004 (¡÷)πÃ∑°≥› All rights reserved.
 */
package maf.lib.calendar;

import maf.util.StringUtils;

public class MyDate {
	private int year, month, day;

	int leapMonth = 0;

	public MyDate(int year, int month, int day, int leapMonth) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.leapMonth = leapMonth;
	}

	/**
	 * @return Returns the day.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * 0 - ∆Ú¥ﬁ, 1 - ¿±¥ﬁ
	 * 
	 * @return Returns the leapMonth.
	 */
	public int getLeapMonth() {
		return leapMonth;
	}

	/**
	 * @return Returns the month.
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @return Returns the year.
	 */
	public int getYear() {
		return year;
	}

	public String toString() {
		StringBuffer rv = new StringBuffer(10);
		rv.append(StringUtils.lpad(year+"", 4, "0"));
		rv.append(StringUtils.lpad(month+"", 2, "0"));
		rv.append(StringUtils.lpad(day+"", 2, "0"));
		if(this.leapMonth == 1) {
			rv.append("(¿±¥ﬁ)");
		}
		return rv.toString();
	}

}
