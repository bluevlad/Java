/*
 * Created on 2005. 5. 27.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package test;

import java.util.Date;

/**
 * @author goindole
 * 
 */

public class Dual_calBean {

	private Date v_date = null;

	private int v_day = 0;

	private String v_holiday = null;

	/**
	 * ����
	 */
	private String v_lunar = null;

	/**
	 * T:������, F:�����
	 */
	private String v_dayoff = null;

	/**
	 * ������ ��� ��������
	 */
	private Date v_rdate = null;

	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Get v_date : DB TYPE : DATE
	 */
	public Date getV_date() {
		return this.v_date;
	}

	/**
	 * Set v_date : DB TYPE : DATE
	 */
	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}

	/**
	 * Get v_day : DB TYPE : NUMBER
	 */
	public int getV_day() {
		return this.v_day;
	}

	/**
	 * Set v_day : DB TYPE : NUMBER
	 */
	public void setV_day(int v_day) {
		this.v_day = v_day;
	}

	/**
	 * Get v_holiday : DB TYPE : VARCHAR2
	 */
	public String getV_holiday() {
		return this.v_holiday;
	}

	/**
	 * Set v_holiday : DB TYPE : VARCHAR2
	 */
	public void setV_holiday(String v_holiday) {
		this.v_holiday = v_holiday;
	}

	/**
	 * Get v_lunar : ���� DB TYPE : VARCHAR2
	 */
	public String getV_lunar() {
		return this.v_lunar;
	}

	/**
	 * Set v_lunar : ���� DB TYPE : VARCHAR2
	 */
	public void setV_lunar(String v_lunar) {
		this.v_lunar = v_lunar;
	}

	/**
	 * Get v_dayoff : T:������, F:����� DB TYPE : CHAR
	 */
	public String getV_dayoff() {
		return this.v_dayoff;
	}

	/**
	 * Set v_dayoff : T:������, F:����� DB TYPE : CHAR
	 */
	public void setV_dayoff(String v_dayoff) {
		this.v_dayoff = v_dayoff;
	}

	/**
	 * Get v_rdate : ������ ��� �������� DB TYPE : DATE
	 */
	public Date getV_rdate() {
		return this.v_rdate;
	}

	/**
	 * Set v_rdate : ������ ��� �������� DB TYPE : DATE
	 */
	public void setV_rdate(Date v_rdate) {
		this.v_rdate = v_rdate;
	}

}
