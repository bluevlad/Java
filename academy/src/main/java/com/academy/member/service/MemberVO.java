package com.academy.exam.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 사물함 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.01			rainend			 사물함 정보 등록
* </pre>
*/
public class ExamBankVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 문제은행번호 */
	private int bankId;
	/** 년도 */
	private int examYear;
	/** 회차 */
	private int examRound;
	/** 과목코드 */
	private int sbjCd;
	/** 문제번호 */
	private int queId;
	/** 문제제목 */
	private String queTitle;
	/** 문제지문 */
	private String queDesc;
	/** 출제영역 */
	private int queRange;
	/** 난이도 */
	private int queLevel;
	/** 지문수 */
	private int queCount;
	/** 문제유형(S:단일선택형, M:다지선택형, T:단답형, D:서술형) */
	private String queType;
	/** 정답(번호) */
	private String passAns;
	/** 답안해설 */
	private String ansDesc;
	/** 문제 파일ID */
	private int queFileId;
	/** 답안 파일ID */
	private int ansFileId;
	/** 지문1 */
	private String ansView1;
	/** 지문2 */
	private String ansView2;
    /** 지문3 */
    private String ansView3;
    /** 지문4 */
    private String ansView4;
    /** 지문5 */
    private String ansView5;
    
	/**
	 * bankId attribute 를 리턴한다.
	 * @return int
	 */
	public int getBankId() {
		return bankId;
	}
	/**
	 * bankId attribute 값을 설정한다.
	 * @param bankId int
	 */
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	/**
	 * examYear attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamYear() {
		return examYear;
	}
	/**
	 * examYear attribute 값을 설정한다.
	 * @param examYear int
	 */
	public void setExamYear(int examYear) {
		this.examYear = examYear;
	}
	
	/**
	 * examRound attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamRound() {
		return examRound;
	}
	/**
	 * examRound attribute 값을 설정한다.
	 * @param examRound int
	 */
	public void setExamRound(int examRound) {
		this.examRound = examRound;
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
	 * @param sbjCd int
	 */
	public void setSbjCd(int sbjCd) {
		this.sbjCd = sbjCd;
	}
	
	/**
	 * queId attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueId() {
		return queId;
	}
	/**
	 * queId attribute 값을 설정한다.
	 * @param queId int
	 */
	public void setQueId(int queId) {
		this.queId = queId;
	}
	
	/**
	 * queTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueTitle() {
		return queTitle;
	}
	/**
	 * queTitle attribute 값을 설정한다.
	 * @param queTitle String
	 */
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}
	
	/**
	 * queDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueDesc() {
		return queDesc;
	}
	/**
	 * queDesc attribute 값을 설정한다.
	 * @param queDesc String
	 */
	public void setQueDesc(String queDesc) {
		this.queDesc = queDesc;
	}
	/**
	 * queRange attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueRange() {
		return queRange;
	}
	/**
	 * queRange attribute 값을 설정한다.
	 * @param queRange String
	 */
	public void setQueRange(int queRange) {
		this.queRange = queRange;
	}
	/**
	 * queLevel attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueLevel() {
		return queLevel;
	}
	/**
	 * queLevel attribute 값을 설정한다.
	 * @param queLevel int
	 */
	public void setQueLevel(int queLevel) {
		this.queLevel = queLevel;
	}
	/**
	 * queType attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueType() {
		return queType;
	}
	/**
	 * queType attribute 값을 설정한다.
	 * @param queType String
	 */
	public void setQueType(String queType) {
		this.queType = queType;
	}
	/**
	 * queCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueCount() {
		return queCount;
	}
	/**
	 * queCount attribute 값을 설정한다.
	 * @param queCount int
	 */
	public void setQueCount(int queCount) {
		this.queCount = queCount;
	}
	/**
	 * passAns attribute 를 리턴한다.
	 * @return String
	 */
	public String getPassAns() {
		return passAns;
	}
	/**
	 * passAns attribute 값을 설정한다.
	 * @param passAns String
	 */
	public void setPassAns(String passAns) {
		this.passAns = passAns;
	}
	
	/**
	 * ansDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsDesc() {
		return ansDesc;
	}
	/**
	 * ansDesc attribute 값을 설정한다.
	 * @param ansDesc String
	 */
	public void setAnsDesc(String ansDesc) {
		this.ansDesc = ansDesc;
	}
	/**
	 * queFileId attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueFileId() {
		return queFileId;
	}
	/**
	 * queFileId attribute 값을 설정한다.
	 * @param queFileId int
	 */
	public void setQueFileId(int queFileId) {
		this.queFileId = queFileId;
	}
	/**
	 * ansFileId attribute 를 리턴한다.
	 * @return int
	 */
	public int getAnsFileId() {
		return ansFileId;
	}
	/**
	 * ansFileId attribute 값을 설정한다.
	 * @param ansFileId int
	 */
	public void setAnsFileId(int ansFileId) {
		this.ansFileId = ansFileId;
	}
	/**
	 * ansView1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsView1() {
		return ansView1;
	}
	/**
	 * ansView1 attribute 값을 설정한다.
	 * @param ansView1 String
	 */
	public void setAnsView1(String ansView1) {
		this.ansView1 = ansView1;
	}
	/**
	 * ansView2 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsView2() {
		return ansView2;
	}
	/**
	 * ansView2 attribute 값을 설정한다.
	 * @param ansView2 String
	 */
	public void setAnsView2(String ansView2) {
		this.ansView2 = ansView2;
	}
	/**
	 * ansView3 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsView3() {
		return ansView3;
	}
	/**
	 * ansView3 attribute 값을 설정한다.
	 * @param ansView3 String
	 */
	public void setAnsView3(String ansView3) {
		this.ansView3 = ansView3;
	}
	/**
	 * ansView4 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsView4() {
		return ansView4;
	}
	/**
	 * ansView4 attribute 값을 설정한다.
	 * @param ansView4 String
	 */
	public void setAnsView4(String ansView4) {
		this.ansView4 = ansView4;
	}
	/**
	 * ansView5 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsView5() {
		return ansView5;
	}
	/**
	 * ansView5 attribute 값을 설정한다.
	 * @param ansView5 String
	 */
	public void setAnsView5(String ansView5) {
		this.ansView5 = ansView5;
	}
	
}
