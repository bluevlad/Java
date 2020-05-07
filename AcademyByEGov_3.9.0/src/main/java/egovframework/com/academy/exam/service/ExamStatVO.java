package egovframework.com.academy.exam.service;

import java.io.Serializable;

/**
*
* 시험통계 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.05.07			rainend			시험통계정보
* </pre>
*/

public class ExamStatVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 시험코드 */
	private int examCd;
	/** 시험명 */
	private String examNm;
	/** 과목코드 */
	private int sbjCd;
    /** 과목명 */
	private String sbjNm;
    /** 과목취득점수 */
    private int sbjPoint;
    /** 평균점수 */
    private int avgPoint;
    /** 합계점수 */
    private int sumPoint;
    /** 과목수 */
    private int sbjCnt;
    /** 문제수 */
    private int itemCnt;

	/**
	 * examCd attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamCd() {
		return examCd;
	}
	/**
	 * examCd attribute 값을 설정한다.
	 * @param examCd String
	 */
	public void setExamCd(int examCd) {
		this.examCd = examCd;
	}
	
	/**
	 * examNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamNm() {
		return examNm;
	}
	/**
	 * examNm attribute 값을 설정한다.
	 * @param examNm String
	 */
	public void setExamNm(String examNm) {
		this.examNm = examNm;
	}

	/**
	 * sbjCd attribute 를 리턴한다.
	 * @return int
	 */
	public int getSbjCd() {
		return sbjCd;
	}
	/**
	 * sbjCd attribute 값을 설정한다.
	 * @param sbjCd String
	 */
	public void setSbjCd(int sbjCd) {
		this.sbjCd = sbjCd;
	}
	/**
	 * sbjNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSbjNm() {
		return sbjNm;
	}
	/**
	 * sbjNm attribute 값을 설정한다.
	 * @param sbjNm String
	 */
	public void setSbjNm(String sbjNm) {
		this.sbjNm = sbjNm;
	}
	
	/**
	 * sbjPoint attribute 를 리턴한다.
	 * @return String
	 */
	public int getSbjPoint() {
		return sbjPoint;
	}
	/**
	 * sbjPoint attribute 값을 설정한다.
	 * @param sbjPoint int
	 */
	public void setSbjPoint(int sbjPoint) {
		this.sbjPoint = sbjPoint;
	}
	
	/**
	 * avgPoint attribute 를 리턴한다.
	 * @return String
	 */
	public int getAvgPoint() {
		return avgPoint;
	}
	/**
	 * sbjPoint attribute 값을 설정한다.
	 * @param avgPoint int
	 */
	public void setAvgPoint(int avgPoint) {
		this.avgPoint = avgPoint;
	}
	
	/**
	 * sbjPoint attribute 를 리턴한다.
	 * @return String
	 */
	public int getSumPoint() {
		return sumPoint;
	}
	/**
	 * sumPoint attribute 값을 설정한다.
	 * @param sumPoint int
	 */
	public void setSumPoint(int sumPoint) {
		this.sumPoint = sumPoint;
	}
	
	/**
	 * sbjCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getSbjCnt() {
		return sbjCnt;
	}
	/**
	 * sbjCnt attribute 값을 설정한다.
	 * @param sbjCnt String
	 */
	public void setSbjCnt(int sbjCnt) {
		this.sbjCnt = sbjCnt;
	}
	
	/**
	 * itemCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getItemCnt() {
		return itemCnt;
	}
	/**
	 * sbjCnt attribute 값을 설정한다.
	 * @param itemCnt String
	 */
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}

}
