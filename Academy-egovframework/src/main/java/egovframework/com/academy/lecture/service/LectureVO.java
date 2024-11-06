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
    private String lecCd;
    /** 직종코드 */
    private String categoryCd;
    /** 직종명 */
    private String categoryNm;
    /** 학습형태코드 단과반 (이론단과[M0101],문풀단과[M0102],유료특강[M0103],무료특강[M0104]) */
    private String learningCd;
    /** 담당교수아이디 */
    private String lecTeacher;
    /** 담당교수명 */
    private String lecTeacherNm;
    /** 담당교수 강사료 지급률 */
    private String lecTeacherPayment;
    /** 단과명 */
    private String lecTitle;
    /** 단과 상세설명 */
    private String lecDesc;
    /** 키워드 */
    private String lecKeyword;
    /** 기간 - 일수 */
    private int lecPeriod;
    /** 할인율 */
    private int lecDiscount;
    /** 원가 */
    private int lecPrice;
    /** 포인트 */
    private int lecPoint;
    /** 동영상 수강료 */
    private int lecMovie;
    /** 동영상 기본경로(500K) */
    private String lecVodDefaultPath;
    /** 보강 비밀번호 */
    private String lecPass;
    /** 강의예정회차 */
    private String lecSchedule;
    /** 강의종류 (D-단과, J-종합반, N-선택형종합반,P-패키지) */
    private String lecTypeChoice;
    /** 강의등록상태 (C:업데이트 예정, I: 업데이트 중, E:업데이트 완료) */
    private String lecFlag;
    /** 강의수 */
    private String lecCount;
    /** 복사원본 강의코드 */
    private String orgLeccode;
    /** 패키지 연결 강의코드 */
    private String bridgeLeccode;
    /** 유료 수강생수  */
    private int yCnt;
    /** 무료 수강생수 */
    private int nCnt;
    /** 교재구분 */
    private String bookFlag;
	/** 교재코드 */
	private String bookCd;
    /** 도서명 */
    private String bookNm;

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
	 * lecCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecCd() {
		return lecCd;
	}
	/**
	 * lecCd attribute 값을 설정한다.
	 * @param lecCd String
	 */
	public void setLecCd(String lecCd) {
		this.lecCd = lecCd;
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
	 * categoryNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getCategoryNm() {
		return categoryNm;
	}
	/**
	 * categoryNm attribute 값을 설정한다.
	 * @param categoryNm String
	 */
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
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
lecTeacher attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecTeacher() {
		return lecTeacher;
	}
	/**
	 * lecTeacher attribute 값을 설정한다.
	 * @param lecTeacher String
	 */
	public void setLecTeacher(String lecTeacher) {
		this.lecTeacher = lecTeacher;
	}
	/**
	 * lecTeacherNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecTeacherNm() {
		return lecTeacherNm;
	}
	/**
	 * lecTeacherNm attribute 값을 설정한다.
	 * @param lecTeacherNm String
	 */
	public void setLecTeacherNm(String lecTeacherNm) {
		this.lecTeacherNm = lecTeacherNm;
	}
	/**
	 * lecTeacherPayment attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecTeacherPayment() {
		return lecTeacherPayment;
	}
	/**
	 * lecTeacherPayment attribute 값을 설정한다.
	 * @param lecTeacherPayment String
	 */
	public void setLecTeacherPayment(String lecTeacherPayment) {
		this.lecTeacherPayment = lecTeacherPayment;
	}
	/**
	 * lecTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecTitle() {
		return lecTitle;
	}
	/**
	 * lecTitle attribute 값을 설정한다.
	 * @param subjectTitle String
	 */
	public void setLecTitle(String lecTitle) {
		this.lecTitle = lecTitle;
	}
	/**
	 * lecDesc attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecDesc() {
		return lecDesc;
	}
	/**
	 * lecDesc attribute 값을 설정한다.
	 * @param lecDesc String
	 */
	public void setLecDesc(String lecDesc) {
		this.lecDesc = lecDesc;
	}
	/**
	 * lecKeyword attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecKeyword() {
		return lecKeyword;
	}
	/**
	 * lecKeyword attribute 값을 설정한다.
	 * @param lecKeyword String
	 */
	public void setLecKeyword(String lecKeyword) {
		this.lecKeyword = lecKeyword;
	}
	/**
	 * lecPeriod attribute 를 리턴한다.
	 * @return int
	 */
	public int getLecPeriod() {
		return lecPeriod;
	}
	/**
	 * lecPeriod attribute 값을 설정한다.
	 * @param lecPeriod int
	 */
	public void setLecPeriod(int lecPeriod) {
		this.lecPeriod = lecPeriod;
	}
	/**
	 * lecDiscount attribute 를 리턴한다.
	 * @return int
	 */
	public int getLecDiscount() {
		return lecDiscount;
	}
	/**
	 * lecDiscount attribute 값을 설정한다.
	 * @param lecDiscount int
	 */
	public void setLecDiscount(int lecDiscount) {
		this.lecDiscount = lecDiscount;
	}
	/**
	 * lecPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getLecPrice() {
		return lecPrice;
	}
	/**
	 * lecPrice attribute 값을 설정한다.
	 * @param lecPrice int
	 */
	public void setLecPrice(int lecPrice) {
		this.lecPrice = lecPrice;
	}
	/**
	 * lecPoint attribute 를 리턴한다.
	 * @return int
	 */
	public int getLecPoint() {
		return lecPoint;
	}
	/**
	 * lecPoint attribute 값을 설정한다.
	 * @param lecPoint int
	 */
	public void setLecPoint(int lecPoint) {
		this.lecPoint = lecPoint;
	}
	/**
	 * lecMovie attribute 를 리턴한다.
	 * @return int
	 */
	public int getLecMovie() {
		return lecMovie;
	}
	/**
	 * lecMovie attribute 값을 설정한다.
	 * @param lecMovie int
	 */
	public void setLecMovie(int lecMovie) {
		this.lecMovie = lecMovie;
	}
	/**
	 * lecVodDefaultPath attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecVodDefaultPath() {
		return lecVodDefaultPath;
	}
	/**
	 * lecVodDefaultPath attribute 값을 설정한다.
	 * @param lecVodDefaultPath String
	 */
	public void setLecVodDefaultPath(String lecVodDefaultPath) {
		this.lecVodDefaultPath = lecVodDefaultPath;
	}
	/**
	 * lecPass attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecPass() {
		return lecPass;
	}
	/**
	 * lecPass attribute 값을 설정한다.
	 * @param lecPass String
	 */
	public void setLecPass(String lecPass) {
		this.lecPass = lecPass;
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
	 * lecFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getLecFlag() {
		return lecFlag;
	}
	/**
	 * lecFlag attribute 값을 설정한다.
	 * @param lecFlag String
	 */
	public void setLecFlag(String lecFlag) {
		this.lecFlag = lecFlag;
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
	/**
	 * bridgeLeccode attribute 를 리턴한다.
	 * @return String
	 */
	public String getBridgeLeccode() {
		return bridgeLeccode;
	}
	/**
	 * bridgeLeccode attribute 값을 설정한다.
	 * @param bridgeLeccode String
	 */
	public void setBridgeLeccode(String bridgeLeccode) {
		this.bridgeLeccode = bridgeLeccode;
	}
	/**
	 * yCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getYCnt() {
		return yCnt;
	}
	/**
	 * yCnt attribute 값을 설정한다.
	 * @param yCnt int
	 */
	public void setYCnt(int yCnt) {
		this.yCnt = yCnt;
	}
	/**
	 * nCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getNCnt() {
		return nCnt;
	}
	/**
	 * nCnt attribute 값을 설정한다.
	 * @param nCnt int
	 */
	public void setNCnt(int nCnt) {
		this.nCnt = nCnt;
	}
	/**
	 * bookFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookFlag() {
		return bookFlag;
	}
	/**
	 * bookFlag attribute 값을 설정한다.
	 * @param bookFlag String
	 */
	public void setBookFlag(String bookFlag) {
		this.bookFlag = bookFlag;
	}
	/**
	 * bookCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookCd() {
		return bookCd;
	}
	/**
	 * bookCd attribute 값을 설정한다.
	 * @param bookCd String
	 */
	public void setBookCd(String bookCd) {
		this.bookCd = bookCd;
	}
	/**
	 * bookNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBookNm() {
		return bookNm;
	}
	/**
	 * bookNm attribute 값을 설정한다.
	 * @param bookNm String
	 */
	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
	}
	
}
