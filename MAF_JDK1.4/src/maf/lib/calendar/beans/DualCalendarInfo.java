/*
 * Created on 2006. 10. 20
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.lib.calendar.beans;

import java.util.Date;

import maf.base.BaseObject;

public class DualCalendarInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static String[] hdow = {"일","월","화","수","목","금","토"};
	String ymd = null;
	String ymd2 = null;
	Date ymd_date = null;
	String dow = null;
	String bank_work = null;
	String work_day = null;
	String bigo = null;
	
	/**
	 * @return the bank_work
	 */
	public boolean isBankWork() {
		return ("1".equals(this.bank_work))?true:false;
	}
	/**
	 * @return the bigo
	 */
	public String getBank_work() {
		return bank_work;
	}
	/**
	 * @param bank_work the bank_work to set
	 */
	public void setBank_work(String bank_work) {
		this.bank_work = bank_work;
	}
	/**
	 * @return the bigo
	 */
	public String getBigo() {
		return bigo;
	}
	/**
	 * @param bigo the bigo to set
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * @return the dow
	 */
	public String getDow() {
//		return dow;
		try {
			return hdow[Integer.parseInt(this.dow)-1];
		} catch (Throwable e) {
			return null;
		}
	}
	/**
	 * @return the dow
	 */
	public String getHDow() {
		try {
			return hdow[Integer.parseInt(this.dow)-1];
		} catch (Throwable e) {
			return null;
		}
	}
	/**
	 * @param dow the dow to set
	 */
	public void setDow(String dow) {
		this.dow = dow;
	}
	/**
	 * @return the work_day
	 */
	public boolean isWorkDay() {
		return ("1".equals(this.work_day))?true:false;
	}

	/**
	 * @return the bigo
	 */
	public String getWork_day() {
		return work_day;
	}
	/**
	 * @param work_day the work_day to set
	 */
	public void setWork_day(String work_day) {
		this.work_day = work_day;
		
	}
	/**
	 * @return the ymd
	 */
	public String getYmd() {
		return ymd;
	}
	/**
	 * @param ymd the ymd to set
	 */
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	/**
	 * @return the ymd_date
	 */
	public Date getYmd_date() {
		return ymd_date;
	}
	/**
	 * @param ymd_date the ymd_date to set
	 */
	public void setYmd_date(Date ymd_date) {
		this.ymd_date = ymd_date;
	}
	/**
	 * @return the ymd2
	 */
	public String getYmd2() {
		return ymd2;
	}
	/**
	 * @param ymd2 the ymd2 to set
	 */
	public void setYmd2(String ymd2) {
		this.ymd2 = ymd2;
	}
	
}

