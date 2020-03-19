/*
 * Created on 2006. 6. 2.
 *
 * �� �ҽ��� Naver�� �޷� Javascript�� �̿��Ͽ� �ۼ� �Ǿ����ϴ�.
 * 
 */
package maf.lib.calendar;

import maf.MafUtil;

public class LunarCalendar {
	
	private LunarCalendar() {
		
	}
	/**
	 * ���� ������ (��� - ������ :1,  ū��:2 )
	 * (������ �ִ� �� - ����� �۰� ���޵� ������ :3 , ����� �۰� ������ ũ�� : 4)
	 * (������ �ִ� �� - ����� ũ�� ������ ������ :5,  ��ް� ������ ��� ũ�� : 6)
	 */
	private static final int[][] lunarMonthTable = {
			{2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 5, 2, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1},   /* 1901 */
			{2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2},
			{1, 2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2},
			{2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1},
			{2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2},
			{1, 2, 2, 4, 1, 2, 1, 2, 1, 2, 1, 2},
			{1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1},
			{2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2},
			{1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1},
			{2, 1, 2, 1, 1, 5, 1, 2, 2, 1, 2, 2},   /* 1911 */
			{2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2},
			{2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2},
			{2, 2, 1, 2, 5, 1, 2, 1, 2, 1, 1, 2},
			{2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2},
			{1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1},
			{2, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1},
			{2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 5, 2, 2, 1, 2, 2},
			{1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2},
			{2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2},   /* 1921 */
			{2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2},
			{1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2},
			{2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1},
			{2, 1, 2, 5, 2, 1, 2, 2, 1, 2, 1, 2},
			{1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2},
			{1, 5, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2},
			{1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2},
			{1, 2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1},
			{2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1},   /* 1931 */
			{2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2},
			{1, 2, 2, 1, 6, 1, 2, 1, 2, 1, 1, 2},
			{1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2},
			{1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1},
			{2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1},
			{2, 2, 1, 1, 2, 1, 4, 1, 2, 2, 1, 2},
			{2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2},
			{2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1},
			{2, 2, 1, 2, 2, 4, 1, 1, 2, 1, 2, 1},   /* 1941 */
			{2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2},
			{1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2},
			{1, 1, 2, 4, 1, 2, 1, 2, 2, 1, 2, 2},
			{1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2},
			{2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2},
			{2, 5, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2},
			{2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
			{2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2},
			{2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1},
			{2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2},   /* 1951 */
			{1, 2, 1, 2, 4, 2, 1, 2, 1, 2, 1, 2},
			{1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2},
			{1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2},
			{2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2},
			{1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2},
			{2, 1, 2, 1, 2, 1, 1, 5, 2, 1, 2, 2},
			{1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
			{1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
			{2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1},
			{2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2},   /* 1961 */
			{1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2, 1},
			{2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2},
			{1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2},
			{1, 2, 5, 2, 1, 1, 2, 1, 1, 2, 2, 1},
			{2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2},
			{1, 2, 2, 1, 2, 1, 5, 2, 1, 2, 1, 2},
			{1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1},
			{2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2},
			{1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1, 2},   /* 1971 */
			{1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1},
			{2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1},
			{2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1, 2},
			{2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2},
			{2, 2, 1, 2, 1, 2, 1, 5, 2, 1, 1, 2},
			{2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1},
			{2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1},
			{2, 1, 1, 2, 1, 6, 1, 2, 2, 1, 2, 1},
			{2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2},
			{1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2},   /* 1981 */
			{2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2, 2},
			{2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2},
			{2, 1, 2, 2, 1, 1, 2, 1, 1, 5, 2, 2},
			{1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2},
			{1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1},
			{2, 1, 2, 2, 1, 5, 2, 2, 1, 2, 1, 2},
			{1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2},
			{1, 2, 1, 1, 5, 1, 2, 1, 2, 2, 2, 2},
			{1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2},   /* 1991 */
			{1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2},
			{1, 2, 5, 2, 1, 2, 1, 1, 2, 1, 2, 1},
			{2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2},
			{1, 2, 2, 1, 2, 2, 1, 5, 2, 1, 1, 2},
			{1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2},
			{1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2, 1},
			{2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1},
			{2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1},
			{2, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2},   /* 2001 */
			{2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1},
			{2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2},
			{1, 5, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2},
			{1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 1},
			{2, 1, 2, 1, 2, 1, 5, 2, 2, 1, 2, 2},
			{1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2},
			{2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2},
			{2, 2, 1, 1, 5, 1, 2, 1, 2, 1, 2, 2},
			{2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
			{2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1},   /* 2011 */
			{2, 1, 6, 2, 1, 2, 1, 1, 2, 1, 2, 1},
			{2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2},
			{1, 2, 1, 2, 1, 2, 1, 2, 5, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1},
			{2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2},
			{2, 1, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2},
			{1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2},
			{2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2},
			{2, 1, 2, 5, 2, 1, 1, 2, 1, 2, 1, 2},
			{1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1},   /* 2021 */
			{2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2},
			{1, 5, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1},
			{2, 1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1},
			{2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2},
			{1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2},
			{1, 2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1},
			{2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2},
			{1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1},
			{2, 1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1},   /* 2031 */
			{2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 5, 2},
			{1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1},
			{2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2},
			{2, 2, 1, 2, 1, 4, 1, 1, 2, 2, 1, 2},
			{2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2},
			{2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1},
			{2, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 1},
			{2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1},
			{2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2},   /* 2041 */
			{1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2},
			{1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2}};
	private static final int[] solMonthDay = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/**
	 * ��� <-> ���� ��ȯ �Լ�
	 * @param year
	 * @param month
	 * @param day
	 * type : 1 - ��� -> ����
	 *        2 - ���� -> ���
	 * leapmonth : 0 - ���
	 *             1 - ���� (type = 2 �϶��� ��ȿ)
	 * @return
	 */
	private static MyDate lunarCalc(int year, int month, int day, int type, int leapmonth) {
		
		int solYear, solMonth, solDay;
		int lunYear, lunMonth, lunDay;
		int lunLeapMonth, lunMonthDay;	
		int lunIndex;
		
		
		/* range check */
		if (year < 1900 || year > 2040)
		{
//			alert('1900����� 2040������� �����մϴ�');
			return null;
		}
		
		/* �ӵ� ������ ���� ���� ���ڸ� �������� �Ѵ� */
		if (year >= 2000)
		{
			/* �������� ��� 2000�� 1�� 1�� (���� 1999�� 11�� 25��) */
			solYear = 2000;
			solMonth = 1;
			solDay = 1;
			lunYear = 1999;
			lunMonth = 11;
			lunDay = 25;
			lunLeapMonth = 0;

			solMonthDay[1] = 29;	/* 2000 �� 2�� 28�� */
			lunMonthDay = 30;	/* 1999�� 11�� */
		}
		else if (year >= 1970)
		{
			/* �������� ��� 1970�� 1�� 1�� (���� 1969�� 11�� 24��) */
			solYear = 1970;
			solMonth = 1;
			solDay = 1;
			lunYear = 1969;
			lunMonth = 11;
			lunDay = 24;
			lunLeapMonth = 0;

			solMonthDay[1] = 28;	/* 1970 �� 2�� 28�� */
			lunMonthDay = 30;	/* 1969�� 11�� */
		}
		else if (year >= 1940)
		{
			/* �������� ��� 1940�� 1�� 1�� (���� 1939�� 11�� 22��) */
			solYear = 1940;
			solMonth = 1;
			solDay = 1;
			lunYear = 1939;
			lunMonth = 11;
			lunDay = 22;
			lunLeapMonth = 0;

			solMonthDay[1] = 29;	/* 1940 �� 2�� 28�� */
			lunMonthDay = 29;	/* 1939�� 11�� */
		}
		else
		{
			/* �������� ��� 1900�� 1�� 1�� (���� 1899�� 12�� 1��) */
			solYear = 1900;
			solMonth = 1;
			solDay = 1;
			lunYear = 1899;
			lunMonth = 12;
			lunDay = 1;
			lunLeapMonth = 0;

			solMonthDay[1] = 28;	/* 1900 �� 2�� 28�� */
			lunMonthDay = 30;	/* 1899�� 12�� */
		}
		
		lunIndex = lunYear - 1899;
		while (true)
		{
//			document.write(solYear + "-" + solMonth + "-" + solDay + "<->");
//			document.write(lunYear + "-" + lunMonth + "-" + lunDay + " " + lunLeapMonth + " " + lunMonthDay + "<br>");

			if (type == 1 &&
				year == solYear &&
				month == solMonth &&
				day == solDay)
			{
				return new MyDate(lunYear, lunMonth, lunDay, lunLeapMonth);
			}	
			else if (type == 2 &&
					year == lunYear &&
					month == lunMonth &&
					day == lunDay && 
					leapmonth == lunLeapMonth)
			{
				return new MyDate(solYear, solMonth, solDay, 0);
			}

			/* add a day of solar calendar */
			if (solMonth == 12 && solDay == 31)
			{
				solYear++;
				solMonth = 1;
				solDay = 1;

				/* set monthDay of Feb */
				if (solYear % 400 == 0)
					solMonthDay[1] = 29;
				else if (solYear % 100 == 0)
					solMonthDay[1] = 28;
				else if (solYear % 4 == 0)
					solMonthDay[1] = 29;
				else
					solMonthDay[1] = 28;

			}
			else if (solMonthDay[solMonth - 1] == solDay)
			{
				solMonth++;
				solDay = 1;	
			}
			else
				solDay++;

			/* add a day of lunar calendar */
			if (lunMonth == 12 &&
				((lunarMonthTable[lunIndex][lunMonth - 1] == 1 && lunDay == 29) ||
				(lunarMonthTable[lunIndex][lunMonth - 1] == 2 && lunDay == 30)))
			{
				lunYear++;
				lunMonth = 1;
				lunDay = 1;

				if (lunYear > 2043) {
//					alert("�Է��Ͻ� ���� �����ϴ�.");
					break;
				}

				lunIndex = lunYear - 1899;

				if (lunarMonthTable[lunIndex][lunMonth - 1] == 1)
					lunMonthDay = 29;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 2)
					lunMonthDay = 30;
			}
			else if (lunDay == lunMonthDay)
			{
				if (lunarMonthTable[lunIndex][lunMonth - 1] >= 3
					&& lunLeapMonth == 0)
				{
					lunDay = 1;
					lunLeapMonth = 1;
				}
				else
				{
					lunMonth++;
					lunDay = 1;
					lunLeapMonth = 0;
				}

				if (lunarMonthTable[lunIndex][lunMonth - 1] == 1)
					lunMonthDay = 29;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 2)
					lunMonthDay = 30;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 3)
					lunMonthDay = 29;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 4 &&
						lunLeapMonth == 0)
					lunMonthDay = 29;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 4 &&
						lunLeapMonth == 1)
					lunMonthDay = 30;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 5 &&
						lunLeapMonth == 0)
					lunMonthDay = 30;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 5 &&
						lunLeapMonth == 1)
						lunMonthDay = 29;
				else if (lunarMonthTable[lunIndex][lunMonth - 1] == 6)
					lunMonthDay = 30;
			}
			else
				lunDay++;
		}
		return null;
	}
	
	/**
	 * ����� �������� 
	 * @param year
	 * @param month
	 * @param day
	 * @return yyyyMMdd
	 */
	public static String toLunar(int year, int month, int day) {
		MyDate d = LunarCalendar.lunarCalc(year,month,day,1,0);
		return d.toString();
	}
	
	/**
	 * ������ ������� 
	 * @param dateString
	 * @return
	 */
	public static String fromLunar(String dateString) {
		int year, month, day;
		year = MafUtil.parseInt(dateString.substring(0,4));
		month = MafUtil.parseInt(dateString.substring(5,2));
		day = MafUtil.parseInt(dateString.substring(7,2));
		MyDate d = LunarCalendar.lunarCalc(year,month,day,2,0);
		return d.toString();
	}
}

