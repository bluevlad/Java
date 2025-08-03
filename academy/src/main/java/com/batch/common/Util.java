package com.batch.common;

import java.util.Date;

public class Util {

	public Util() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	* 문자열을 Date 객체로 변환
	* @param toStr
	* @param pattern : 형식이 지정되지 않았을 경우 Default "yyyy/MM/dd"
	* @return
	*/
	public static Date parseDate(String toStr, String pattern) {
		Date date = null;
		
		try {
			if("".equals(pattern))
				pattern = "yyyy/MM/dd";
			
			String[] parsePattern = {pattern};
			date = org.apache.commons.lang.time.DateUtils.parseDate(toStr, parsePattern);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
