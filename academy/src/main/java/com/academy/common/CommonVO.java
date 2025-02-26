package com.academy.common;

import java.io.Serializable;

/**
*
* 기본 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.10			rainend			 기본 정보 등록
* </pre>
*/

public class CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 검색조건 */
    private String searchCondition = "";
    /** 검색Keyword */
    private String searchKeyword = "";
    /** 검색사용여부 */
    private String searchUseYn = "";
    /** 전제갯수 */
    private int totalCnt = 0;
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
    /** 등록일시 */
    private String regDt;
    /** 등록자ID */
    private String regId;
    /** 수정일시 */
    private String updDt;
    /** 수정자ID */
    private String updId;
    /** 사용 여부 */
    private String isUse;
    
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
   	 * searchUse attribute 를 리턴한다.
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
   	 * totalCnt attribute 를 리턴한다.
   	 * @return int
   	 */
   	public int getTotalCnt() {
   		return totalCnt;
   	}
   	/**
   	 * totalCnt attribute 값을 설정한다.
   	 * @param totalCnt int
   	 */
   	public void setTotalCnt(int totalCnt) {
   		this.totalCnt = totalCnt;
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
   	 * uptDt attribute 를 리턴한다.
   	 * @return String
   	 */
   	public String getUpdDt() {
   		return updDt;
   	}
   	/**
   	 * uptDt attribute 값을 설정한다.
   	 * @param uptDt String
   	 */
   	public void setUpdDt(String updDt) {
   		this.updDt = updDt;
   	}

   	/**
   	 * uptId attribute 를 리턴한다.
   	 * @return String
   	 */
   	public String getUpdId() {
   		return updId;
   	}
   	/**
   	 * uptId attribute 값을 설정한다.
   	 * @param uptId String
   	 */
   	public void setUpdId(String updId) {
   		this.updId = updId;
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
