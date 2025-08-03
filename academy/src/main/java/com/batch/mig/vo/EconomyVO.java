package com.batch.mig.vo;

import java.sql.Timestamp;

/**
* @Class Name  : EconomyVO.java
* @Description : 경제지표 수집 vo  
* @author      : 개발환경 개발팀 송희순
* @since       : 2022. 03. 07
* @version     : 1.0
* @see
*
* @Modification Information
* <pre>
* 수정일 수정자 수정내용
* ------- -------- ---------------------------
* 2022.03.07 송희순 최초 생성
* </pre>
*
*/
public class EconomyVO {
	Timestamp   stdDate;    // 기준일자

	String symbol;			// 지표명
	String areaCode;		// 권역코드
	String nationalCode;	// 지표 관리 국가코드
	String indType;      	// 지표유형 (환율 : cur, 지수 : ind, 금리 : int, CPI : cpi, GDP : gdp, 인구수 : pop, 실업률 : unemp, OIL : oil)
	Double indValue;    	// 수치값
	String unit;       		// 단위
	String frequency;    	// 주기
	Timestamp   batch_date; // batch time msec
	String batch_flag;   	// batch 처리 완료 여부 y/n
   
	/**
	 * @return the stdDate
	 */
	public Timestamp getStdDate() {
		return stdDate;
	}
	/**
	 * @param stdDate the stdDate to set
	 */
	public void setStdDate(Timestamp stdDate) {
		this.stdDate = stdDate;
	}
	
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**	  
	 * @return the nationalCode
	 */
	public String getNationalCode() {
		return nationalCode;
	}
	/**
	 * @param nationalCode the nationalCode to set
	 */
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
	/**
	 * @return the indType
	 */
	public String getIndType() {
		return indType;
	}
	/**
	 * @param indType the indType to set
	 */
	public void setIndType(String indType) {
		this.indType = indType;
	}
	/**
	 * @return the indValue
	 */
	public Double getIndValue() {
		return indValue;
	}
	/**
	 * @param indValue the indValue to set
	 */
	public void setIndValue(Double indValue) {
		this.indValue = indValue;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the batch_date
	 */
	public Timestamp getBatch_date() {
		return batch_date;
	}
	/**
	 * @param batch_date the batch_date to set
	 */
	public void setBatch_date(Timestamp batch_date) {
		this.batch_date = batch_date;
	}
	/**
	 * @return the batch_flag
	 */
	public String getBatch_flag() {
		return batch_flag;
	}
	/**
	 * @param batch_flag the batch_flag to set
	 */
	public void setBatch_flag(String batch_flag) {
		this.batch_flag = batch_flag;
	}   
}
