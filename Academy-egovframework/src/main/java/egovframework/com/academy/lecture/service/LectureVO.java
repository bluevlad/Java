package egovframework.com.academy.lecture.service;

import java.io.Serializable;

/**
*
* 강의형태 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2023.11.00			rainend			강의형태 등록
* </pre>
*/

public class LectureVO implements Serializable {

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

	/** 과목코드 */
	private String subjectCd;
	/** 과목명 */
	private String subjectNm;
	/** 온라인 사용여부 */
	private String useOn;
    /** 오프라인 사용여부 */
    private String useOff;
    /** 학습형태코드 */
    private String formCode;
    /** 학습형태명 */
    private String formName;
 
    /** 강의코드 (D-단과, J-종합반, N-선택형종합반,P-패키지) */
    private String leccode;
    /** 직종코드 */
    private String categoryCd;
    /** 학습형태코드 단과반 (이론단과[M0101],문풀단과[M0102],유료특강[M0103],무료특강[M0104]) */
    private String learningCd;
    /** 담당교수아이디 */
    private String subjectTeacher;
    /** 담당교수 강사료 지급률 */
    private String subjectTeacherPayment;
    /** 단과명 */
    private String subjectTitle;
    /** 단과 상세설명 */
    private String subjectDesc;
    /** 키워드 */
    private String subjectKeyword;
    /** 기간 - 일수 */
    private int subjectPeriod;
    /** 할인율 */
    private int subjectDiscount;
    /** 원가 */
    private int subjectPrice;
    /** 포인트 */
    private int subjectPoint;
    /** 동영상 수강료 */
    private int subjectMovie;
    /** 동영상 기본경로(500K) */
    private String subjectVodDefaultPath;
    /** 보강 비밀번호 */
    private String subjectPass;
    /** 강의예정회차 */
    private String lecSchedule;
    /** 강의종류 (D-단과, J-종합반, N-선택형종합반,P-패키지) */
    private String lecTypeChoice;
    /** 강의등록상태 (C:업데이트 예정, I: 업데이트 중, E:업데이트 완료) */
    private String movIng;
    /** 강의수 */
    private String lecCount;
    /** 복사원본 강의코드 */
    private String orgLeccode;
    
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
	 * subjectCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectCd() {
		return subjectCd;
	}
	/**
	 * subjectCd attribute 값을 설정한다.
	 * @param subjectCd String
	 */
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	
	/**
	 * subjectNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectNm() {
		return subjectNm;
	}
	/**
	 * subjectNm attribute 값을 설정한다.
	 * @param subjectNm String
	 */
	public void setSubjectNm(String subjectNm) {
		this.subjectNm = subjectNm;
	}
	/**
	 * useOn attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseOn() {
		return useOn;
	}
	/**
	 * useOn attribute 값을 설정한다.
	 * @param useOn String
	 */
	public void setUseOn(String useOn) {
		this.useOn = useOn;
	}
	/**
	 * useOff attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseOff() {
		return useOff;
	}
	/**
	 * useOff attribute 값을 설정한다.
	 * @param useOff String
	 */
	public void setUseOff(String useOff) {
		this.useOff = useOff;
	}
	/**
	 * formCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getFormCode() {
		return formCode;
	}
	/**
	 * formCode attribute 값을 설정한다.
	 * @param formCode String
	 */
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	/**
	 * formName attribute 를 리턴한다.
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * formName attribute 값을 설정한다.
	 * @param formName String
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	/**
	 * leccode attribute 를 리턴한다.
	 * @return String
	 */
	public String getLeccode() {
		return leccode;
	}
	/**
	 * leccode attribute 값을 설정한다.
	 * @param leccode String
	 */
	public void setLeccode(String leccode) {
		this.leccode = leccode;
	}
	/**
	 * categoryCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	/**
	 * categoryCd attribute 값을 설정한다.
	 * @param categoryCd String
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	/**
	 * learningCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getLearningCd() {
		return learningCd;
	}
	/**
	 * learningCd attribute 값을 설정한다.
	 * @param learningCd String
	 */
	public void setLearningCd(String learningCd) {
		this.learningCd = learningCd;
	}
	/**
	 * subjectTeacher attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectTeacher() {
		return subjectTeacher;
	}
	/**
	 * subjectTeacher attribute 값을 설정한다.
	 * @param subjectTeacher String
	 */
	public void setSubjectTeacher(String subjectTeacher) {
		this.subjectTeacher = subjectTeacher;
	}
	/**
	 * subjectTeacherPayment attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectTeacherPayment() {
		return subjectTeacherPayment;
	}
	/**
	 * subjectTeacherPayment attribute 값을 설정한다.
	 * @param subjectTeacherPayment String
	 */
	public void setSubjectTeacherPayment(String subjectTeacherPayment) {
		this.subjectTeacherPayment = subjectTeacherPayment;
	}
	/**
	 * subjectTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectTitle() {
		return subjectTitle;
	}
	/**
	 * subjectTitle attribute 값을 설정한다.
	 * @param subjectTitle String
	 */
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	/**
	 * subjectDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectDesc() {
		return subjectDesc;
	}
	/**
	 * subjectDesc attribute 값을 설정한다.
	 * @param subjectDesc String
	 */
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	/**
	 * subjectKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectKeyword() {
		return subjectKeyword;
	}
	/**
	 * subjectKeyword attribute 값을 설정한다.
	 * @param subjectKeyword String
	 */
	public void setSubjectKeyword(String subjectKeyword) {
		this.subjectKeyword = subjectKeyword;
	}
	/**
	 * subjectPeriod attribute 를 리턴한다.
	 * @return int
	 */
	public int getSubjectPeriod() {
		return subjectPeriod;
	}
	/**
	 * subjectPeriod attribute 값을 설정한다.
	 * @param subjectPeriod int
	 */
	public void setSubjectPeriod(int subjectPeriod) {
		this.subjectPeriod = subjectPeriod;
	}
	/**
	 * subjectDiscount attribute 를 리턴한다.
	 * @return int
	 */
	public int getSubjectDiscount() {
		return subjectDiscount;
	}
	/**
	 * subjectDiscount attribute 값을 설정한다.
	 * @param subjectDiscount int
	 */
	public void setSubjectDiscount(int subjectDiscount) {
		this.subjectDiscount = subjectDiscount;
	}
	/**
	 * subjectPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getSubjectPrice() {
		return subjectPrice;
	}
	/**
	 * subjectPrice attribute 값을 설정한다.
	 * @param subjectPrice int
	 */
	public void setSubjectPrice(int subjectPrice) {
		this.subjectPrice = subjectPrice;
	}
	/**
	 * subjectPoint attribute 를 리턴한다.
	 * @return int
	 */
	public int getSubjectPoint() {
		return subjectPoint;
	}
	/**
	 * subjectPoint attribute 값을 설정한다.
	 * @param subjectPoint int
	 */
	public void setSubjectPoint(int subjectPoint) {
		this.subjectPoint = subjectPoint;
	}
	/**
	 * subjectMovie attribute 를 리턴한다.
	 * @return int
	 */
	public int getSubjectMovie() {
		return subjectMovie;
	}
	/**
	 * subjectMovie attribute 값을 설정한다.
	 * @param subjectMovie int
	 */
	public void setSubjectMovie(int subjectMovie) {
		this.subjectMovie = subjectMovie;
	}
	/**
	 * subjectVodDefaultPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectVodDefaultPath() {
		return subjectVodDefaultPath;
	}
	/**
	 * subjectVodDefaultPath attribute 값을 설정한다.
	 * @param subjectVodDefaultPath String
	 */
	public void setSubjectVodDefaultPath(String subjectVodDefaultPath) {
		this.subjectVodDefaultPath = subjectVodDefaultPath;
	}
	/**
	 * subjectPass attribute 를 리턴한다.
	 * @return String
	 */
	public String getSubjectPass() {
		return subjectPass;
	}
	/**
	 * subjectPass attribute 값을 설정한다.
	 * @param subjectPass String
	 */
	public void setSubjectPass(String subjectPass) {
		this.subjectPass = subjectPass;
	}
	/**
	 * lecSchedule attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecSchedule() {
		return lecSchedule;
	}
	/**
	 * lecSchedule attribute 값을 설정한다.
	 * @param lecSchedule String
	 */
	public void setLecSchedule(String lecSchedule) {
		this.lecSchedule = lecSchedule;
	}
	/**
	 * lecTypeChoice attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecTypeChoice() {
		return lecTypeChoice;
	}
	/**
	 * lecTypeChoice attribute 값을 설정한다.
	 * @param lecTypeChoice String
	 */
	public void setLecTypeChoice(String lecTypeChoice) {
		this.lecTypeChoice = lecTypeChoice;
	}
	/**
	 * movIng attribute 를 리턴한다.
	 * @return String
	 */
	public String getMovIng() {
		return movIng;
	}
	/**
	 * movIng attribute 값을 설정한다.
	 * @param movIng String
	 */
	public void setMovIng(String movIng) {
		this.movIng = movIng;
	}
	/**
	 * lecCount attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecCount() {
		return lecCount;
	}
	/**
	 * lecCount attribute 값을 설정한다.
	 * @param lecCount String
	 */
	public void setLecCount(String lecCount) {
		this.lecCount = lecCount;
	}
	/**
	 * orgLeccode attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrgLeccode() {
		return orgLeccode;
	}
	/**
	 * orgLeccode attribute 값을 설정한다.
	 * @param orgLeccode String
	 */
	public void setOrgLeccode(String orgLeccode) {
		this.orgLeccode = orgLeccode;
	}
	
}
