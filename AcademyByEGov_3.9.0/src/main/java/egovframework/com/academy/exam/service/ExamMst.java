package egovframework.com.academy.exam.service;

import java.io.Serializable;

/**
* 공통코드 모델 클래스
* @author rainend
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.04.00			rainend			시험정보 등록
* </pre>
*/

public class ExamMst implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 시험코드 */
	private int examCd;
	/** 시험명 */
	private String examNm;
	/** 과목코드 */
	private int sbjCd;
    /** 과목명 */
	private String sbjNm;
	/** 문제번호 */
    private int itemNo;
    /** 문제정답 */
    private String passAns;
	/** 사용자아이디 */
    private String userId;
    /** 등록일시 */
    private String regDt;
    /** 등록자ID */
    private String regId;
    /** 과목취득점수 */
    private int sbjPoint;
    /** 제출답안 */
    private String ans;
    /** 정답여부 */
    private String YN;
    /** 사용 여부 */
    private String isUse;
    
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
	 * itemNo attribute 를 리턴한다.
	 * @return int
	 */
	public int getItemNo() {
		return itemNo;
	}
	/**
	 * itemNo attribute 값을 설정한다.
	 * @param itemNo String
	 */
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * regDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegDt() {
		return regDt;
	}
	/**
	 * regDt attribute 값을 설정한다.
	 * @param regDt String
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * regId attribute 를 리턴한다.
	 * @return String
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * regId attribute 값을 설정한다.
	 * @param regId String
	 */
	public void setRegId(String regId) {
		this.regId = regId;
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
		this.examCd = sbjPoint;
	}
	
	/**
	 * ans attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns() {
		return ans;
	}
	/**
	 * ans attribute 값을 설정한다.
	 * @param ans String
	 */
	public void setAns(String ans) {
		this.ans = ans;
	}
	
	/**
	 * YN attribute 를 리턴한다.
	 * @return String
	 */
	public String getYn() {
		return YN;
	}
	/**
	 * YN attribute 값을 설정한다.
	 * @param YN String
	 */
	public void setYn(String YN) {
		this.YN = YN;
	}
	
	/**
	 * isUse attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsUse() {
		return isUse;
	}
	/**
	 * isUSe attribute 값을 설정한다.
	 * @param isUse String
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}
