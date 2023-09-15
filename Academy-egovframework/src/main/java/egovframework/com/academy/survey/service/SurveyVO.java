package egovframework.com.academy.survey.service;

import java.io.Serializable;

/**
*
* 시험정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.04.21			rainend			시험정보 등록
* </pre>
*/

public class SurveyVO implements Serializable {

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
	/** 설문참여자 */
	private String userId;

	/** 설문코드 */
	private int surveyId;
	/** 설문제목 */
	private String surveyTitle;
	/** 설문설명 */
	private String surveyDesc;
	/** 설문시작일 */
	private String surveySdat;
	/** 설문종료일 */
	private String surveyEdat;
	/** 설문대상 */
	private String surveyTarget;

	/** 설문세트코드 */
	private int setId;
	/** 설문세트제목 */
	private String setTitle;
	/** 설문세트설명 */
	private String setDesc;
	/** 설문세트순서 */
	private int queSeq;
    
	/** 문항코드 */
	private int queId;
	/** 문항제목 */
	private String queTitle;
	/** 문항분류 */
	private String queOwner;
	/** 설문문항수 */
	private int queCount;
	/** 문항유형 */
	private String queType;
    /** 문항1 */
    private String queViw1;
    /** 문항2 */
    private String queViw2;
    /** 문항3 */
    private String queViw3;
    /** 문항4 */
    private String queViw4;
    /** 문항5 */
    private String queViw5;
    /** 문항6 */
    private String queViw6;
    /** 문항7 */
    private String queViw7;
    /** 문항8 */
    private String queViw8;
    /** 문항9 */
    private String queViw9;
    /** 문항10 */
    private String queViw10;
	/** 설문문항설명 */
	private String queDesc;
    /** 문항1 */
    private String ans1;
    /** 문항2 */
    private String ans2;
    /** 문항3 */
    private String ans3;
    /** 문항4 */
    private String ans4;
    /** 문항5 */
    private String ans5;
    /** 문항6 */
    private String ans6;
    /** 문항7 */
    private String ans7;
    /** 문항8 */
    private String ans8;
    /** 문항9 */
    private String ans9;
    /** 문항10 */
    private String ans10;
    /** 합계 */
    private String ansSum;
    /** 설문답변 */
    private String userAnsw;
	
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
	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * uptId attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	/**
	 * surveyId attribute 를 리턴한다.
	 * @return int
	 */
	public int getSurveyId() {
		return surveyId;
	}
	/**
	 * surveyId attribute 값을 설정한다.
	 * @param surveyId String
	 */
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * surveyTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getSurveyTitle() {
		return surveyTitle;
	}
	/**
	 * surveyTitle attribute 값을 설정한다.
	 * @param surveyTitle String
	 */
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	/**
	 * surveyDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getSurveyDesc() {
		return surveyDesc;
	}
	/**
	 * surveyDesc attribute 값을 설정한다.
	 * @param surveyDesc String
	 */
	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}
	/**
	 * surveySdat attribute 를 리턴한다.
	 * @return String
	 */
	public String getSurveySdat() {
		return surveySdat;
	}
	/**
	 * surveySdat attribute 값을 설정한다.
	 * @param surveySdat String
	 */
	public void setSurveySdat(String surveySdat) {
		this.surveySdat = surveySdat;
	}
	/**
	 * surveyEdat attribute 를 리턴한다.
	 * @return String
	 */
	public String getSurveyEdat() {
		return surveyEdat;
	}
	/**
	 * surveyEdat attribute 값을 설정한다.
	 * @param surveyEdat String
	 */
	public void setSurveyEdat(String surveyEdat) {
		this.surveyEdat = surveyEdat;
	}
	/**
	 * surveyTarget attribute 를 리턴한다.
	 * @return String
	 */
	public String getSurveyTarget() {
		return surveyTarget;
	}
	/**
	 * surveyTarget attribute 값을 설정한다.
	 * @param surveyTarget String
	 */
	public void setSurveyTarget(String surveyTarget) {
		this.surveyTarget = surveyTarget;
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
	 * @param queId String
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
	 * queOwner attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueOwner() {
		return queOwner;
	}
	/**
	 * queOwner attribute 값을 설정한다.
	 * @param queOwner String
	 */
	public void setQueOwner(String queOwner) {
		this.queOwner = queOwner;
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
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw1() {
		return queViw1;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param queViw1 String
	 */
	public void setQueViw1(String queViw1) {
		this.queViw1 = queViw1;
	}
	/**
	 * queViw2 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw2() {
		return queViw2;
	}
	/**
	 * queViw2 attribute 값을 설정한다.
	 * @param queViw2 String
	 */
	public void setQueViw2(String queViw2) {
		this.queViw2 = queViw2;
	}
	/**
	 * queViw3 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw3() {
		return queViw3;
	}
	/**
	 * queViw3 attribute 값을 설정한다.
	 * @param queViw3 String
	 */
	public void setQueViw3(String queViw3) {
		this.queViw3 = queViw3;
	}
	/**
	 * queViw4 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw4() {
		return queViw4;
	}
	/**
	 * queViw4 attribute 값을 설정한다.
	 * @param queViw4 String
	 */
	public void setQueViw4(String queViw4) {
		this.queViw4 = queViw4;
	}
	/**
	 * queViw5 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw5() {
		return queViw5;
	}
	/**
	 * queViw5 attribute 값을 설정한다.
	 * @param queViw5 String
	 */
	public void setQueViw5(String queViw5) {
		this.queViw5 = queViw5;
	}
	/**
	 * queViw6 attribute 를 리턴한다.
	 * @return int
	 */
	public String getQueViw6() {
		return queViw6;
	}
	/**
	 * queViw6 attribute 값을 설정한다.
	 * @param queViw6 String
	 */
	public void setQueViw6(String queViw6) {
		this.queViw6 = queViw6;
	}
	/**
	 * queViw7 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw7() {
		return queViw7;
	}
	/**
	 * queViw7 attribute 값을 설정한다.
	 * @param queViw7 String
	 */
	public void setQueViw7(String queViw7) {
		this.queViw7 = queViw7;
	}
	/**
	 * queViw8 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw8() {
		return queViw8;
	}
	/**
	 * queViw8 attribute 값을 설정한다.
	 * @param queViw8 String
	 */
	public void setQueViw8(String queViw8) {
		this.queViw8 = queViw8;
	}
	/**
	 * queViw9 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw9() {
		return queViw9;
	}
	/**
	 * queViw9 attribute 값을 설정한다.
	 * @param queViw9 String
	 */
	public void setQueViw9(String queViw9) {
		this.queViw9 = queViw9;
	}
	/**
	 * queViw10 attribute 를 리턴한다.
	 * @return String
	 */
	public String getQueViw10() {
		return queViw10;
	}
	/**
	 * queViw10 attribute 값을 설정한다.
	 * @param queViw10 String
	 */
	public void setQueViw10(String queViw10) {
		this.queViw10 = queViw10;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns1() {
		return ans1;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns2() {
		return ans2;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns3() {
		return ans3;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns4() {
		return ans4;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns5() {
		return ans5;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns5(String ans5) {
		this.ans5 = ans5;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns6() {
		return ans6;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns6(String ans6) {
		this.ans6 = ans6;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns7() {
		return ans7;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns7(String ans7) {
		this.ans7 = ans7;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns8() {
		return ans8;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns8(String ans8) {
		this.ans8 = ans8;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns9() {
		return ans9;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns9(String ans9) {
		this.ans9 = ans9;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAns10() {
		return ans10;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAns10(String ans10) {
		this.ans10 = ans10;
	}
	/**
	 * queViw1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAnsSum() {
		return ansSum;
	}
	/**
	 * queViw1 attribute 값을 설정한다.
	 * @param ans1 String
	 */
	public void setAnsSum(String ansSum) {
		this.ansSum = ansSum;
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
	 * setId attribute 를 리턴한다.
	 * @return int
	 */
	public int getSetId() {
		return setId;
	}
	/**
	 * setId attribute 값을 설정한다.
	 * @param setId String
	 */
	public void setSetId(int setId) {
		this.setId = setId;
	}
	/**
	 * setTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getSetTitle() {
		return setTitle;
	}
	/**
	 * setTitle attribute 값을 설정한다.
	 * @param setTitle String
	 */
	public void setSetTitle(String setTitle) {
		this.setTitle = setTitle;
	}
	/**
	 * setDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getSetDesc() {
		return setDesc;
	}
	/**
	 * setDesc attribute 값을 설정한다.
	 * @param setDesc String
	 */
	public void setSetDesc(String setDesc) {
		this.setDesc = setDesc;
	}
	/**
	 * queSeq attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueSeq() {
		return queSeq;
	}
	/**
	 * queSeq attribute 값을 설정한다.
	 * @param queSeq String
	 */
	public void setQueSeq(int queSeq) {
		this.queSeq = queSeq;
	}
	/**
	 * userAnsw attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserAnsw() {
		return userAnsw;
	}
	/**
	 * setDesc attribute 값을 설정한다.
	 * @param userAnsw String
	 */
	public void setUserAnsw(String userAnsw) {
		this.userAnsw = userAnsw;
	}

}
