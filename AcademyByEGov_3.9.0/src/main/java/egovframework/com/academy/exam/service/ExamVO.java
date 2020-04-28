package egovframework.com.academy.exam.service;

import java.io.Serializable;

/**
*
* 공통코드 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.04.00			rainend			시험정보 등록
* </pre>
*/

public class ExamVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색Keyword */
    private String searchKeyword = "";
    /** 검색사용여부 */
    private String searchUseYn = "";
    /** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 10;
    /** 페이지사이즈 */
    private int pageSize = 10;
    /** firstIndex */
    private int firstIndex = 1;
    /** lastIndex */
    private int lastIndex = 1;
    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    
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
	/** 사용자아이디 */
    private String userNm;
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
    /** 과목수 */
    private int sbjCnt;
    /** 문제수 */
    private int itemCnt;

	/**
	 * searchCondition attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * searchCondition attribute 값을 설정한다.
	 * @param searchCondition String
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * searchKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * searchKeyword attribute 값을 설정한다.
	 * @param searchKeyword String
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * searchUseYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * searchUseYn attribute 값을 설정한다.
	 * @param searchUseYn String
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	/**
	 * pageIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * pageIndex attribute 값을 설정한다.
	 * @param pageIndex int
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * pageUnit attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * pageUnit attribute 값을 설정한다.
	 * @param pageUnit int
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	/**
	 * pageSize attribute 를 리턴한다.
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * pageSize attribute 값을 설정한다.
	 * @param pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * firstIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * firstIndex attribute 값을 설정한다.
	 * @param firstIndex int
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	/**
	 * lastIndex attribute 를 리턴한다.
	 * @return int
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * lastIndex attribute 값을 설정한다.
	 * @param lastIndex int
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * recordCountPerPage attribute 를 리턴한다.
	 * @return int
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * recordCountPerPage attribute 값을 설정한다.
	 * @param recordCountPerPage int
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
    
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
	 * userNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
