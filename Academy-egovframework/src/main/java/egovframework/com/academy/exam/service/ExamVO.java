package egovframework.com.academy.exam.service;

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

    
	/** 시험코드 */
	private int examId;
	/** 시험명 */
	private String examNm;
	/** 시험년도 */
	private int examYear;
	/** 시험차수 */
	private int examRound;
	/** 시험시작일시 */
	private String examOpen;
	/** 시험종료일시 */
	private String examEnd;
	/** 시험기간 */
	private int examPeriod;
	/** 시험시간 */
	private int examTime;
    /** 시험 개설 상태 */
    private String useFlag;
	/** 과목코드 */
	private int sbjCd;
    /** 과목명 */
	private String sbjNm;

	/** 설문세트코드 */
	private int setId;
	/** 설문세트제목 */
	private String setTitle;
	/** 설문세트설명 */
	private String setDesc;
	/** 설문세트순서 */
	private int queSeq;
	
	/** 문제은행번호 */
	private int queId;
	/** 지문수 */
    private int queCount;
    /** 문제등록옵션 */
    private int queOption;
	/** 문제제목 */
    private String queTitle;
	/** 문제지문 */
    private String queDesc;
    /** 문제파일번호 */
    private int queFileId;
	/** 정답해설 */
    private String ansDesc;
    /** 정답파일번호 */
    private int ansFileId;
    /** 문제정답 */
    private String passAns;
    /** 출제영역 */
    private int queRange;
    /** 출제난이도 */
    private int queLevel;
    /** 출제유형 */
    private String queType;
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
    
    /** 과목취득점수 */
    private int sbjPoint;
    /** 제출답안 */
    private String ans;
    /** 정답여부 */
    private String yn;
    /** 주문번호 */
    private int ordNo;
    /** 응시코드 */
    private String reqId;
	/** 사용자아이디 */
    private String userId;
	/** 사용자아이디 */
    private String userNm;
	/** 주문사항 */
    private String ordNote;
	/** 응시상태 */
    private String examFlag;
	/** 시작시간 */
    private String examStarttime;
	/** 종료시간 */
    private String examEndtime;
    /** 남은시간 */
    private int examSparetime;
	/** 응시IP */
    private String examIp;
 
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
	 * examId attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamId() {
		return examId;
	}
	/**
	 * examId attribute 값을 설정한다.
	 * @param examId String
	 */
	public void setExamId(int examId) {
		this.examId = examId;
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
	 * examYear attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamYear() {
		return examYear;
	}
	/**
	 * examRound attribute 값을 설정한다.
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
	 * examOpen attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamOpen() {
		return examOpen;
	}
	/**
	 * examOpen attribute 값을 설정한다.
	 * @param examOpen String
	 */
	public void setExamOpen(String examOpen) {
		this.examOpen = examOpen;
	}
	
	/**
	 * examEnd attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamEnd() {
		return examEnd;
	}
	/**
	 * examEnd attribute 값을 설정한다.
	 * @param examEnd String
	 */
	public void setExamEnd(String examEnd) {
		this.examEnd = examEnd;
	}
	
	/**
	 * examPeriod attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamPeriod() {
		return examPeriod;
	}
	/**
	 * examPeriod attribute 값을 설정한다.
	 * @param examPeriod int
	 */
	public void setExamPeriod(int examPeriod) {
		this.examPeriod = examPeriod;
	}
	
	/**
	 * examTime attribute 를 리턴한다.
	 * @return int
	 */
	public int getExamTime() {
		return examTime;
	}
	/**
	 * examTime attribute 값을 설정한다.
	 * @param examTime int
	 */
	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}
	
	/**
	 * useFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getUseFlag() {
		return useFlag;
	}
	/**
	 * useFlag attribute 값을 설정한다.
	 * @param useFlag String
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
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
	 * queOption attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueOption() {
		return queOption;
	}
	/**
	 * queOption attribute 값을 설정한다.
	 * @param queOption int
	 */
	public void setQueOption(int queOption) {
		this.queOption = queOption;
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
	 * queFileId attribute 를 리턴한다.
	 * @return int
	 */
	public int getQueFileId() {
		return queFileId;
	}
	/**
	 * queFileId attribute 값을 설정한다.
	 * @param queFileId String
	 */
	public void setQueFileId(int queFileId) {
		this.queFileId = queFileId;
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
	 * ansFileId attribute 를 리턴한다.
	 * @return int
	 */
	public int getAnsFileId() {
		return ansFileId;
	}
	/**
	 * ansFileId attribute 값을 설정한다.
	 * @param ansFileId String
	 */
	public void setAnsFileId(int ansFileId) {
		this.ansFileId = ansFileId;
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
	 * @param queLevel String
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
	 * yn attribute 를 리턴한다.
	 * @return String
	 */
	public String getYn() {
		return yn;
	}
	/**
	 * yn attribute 값을 설정한다.
	 * @param yn String
	 */
	public void setYn(String yn) {
		this.yn = yn;
	}

	/**
	 * ordNo attribute 를 리턴한다.
	 * @return String
	 */
	public int getOrdNo() {
		return ordNo;
	}
	/**
	 * ordNo attribute 값을 설정한다.
	 * @param ordNo int
	 */
	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	/**
	 * reqId attribute 를 리턴한다.
	 * @return String
	 */
	public String getReqId() {
		return reqId;
	}
	/**
	 * reqId attribute 값을 설정한다.
	 * @param reqId String
	 */
	public void setReqId(String reqId) {
		this.reqId = reqId;
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
	 * ordNote attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrdNote() {
		return ordNote;
	}
	/**
	 * ordNote attribute 값을 설정한다.
	 * @param ordNote String
	 */
	public void setOrdNote(String ordNote) {
		this.ordNote = ordNote;
	}

	/**
	 * examFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamFlag() {
		return examFlag;
	}
	/**
	 * examFlag attribute 값을 설정한다.
	 * @param examFlag String
	 */
	public void setExamFlag(String examFlag) {
		this.examFlag = examFlag;
	}

	/**
	 * examStarttime attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamStarttime() {
		return examStarttime;
	}
	/**
	 * examStarttime attribute 값을 설정한다.
	 * @param examStarttime String
	 */
	public void setExamStarttime(String examStarttime) {
		this.examStarttime = examStarttime;
	}

	/**
	 * examEndtime attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamEndtime() {
		return examEndtime;
	}
	/**
	 * examEndtime attribute 값을 설정한다.
	 * @param examEndtime String
	 */
	public void setExamEndtime(String examEndtime) {
		this.examEndtime = examEndtime;
	}

	/**
	 * examSparetime attribute 를 리턴한다.
	 * @return String
	 */
	public int getExamSparetime() {
		return examSparetime;
	}
	/**
	 * examSparetime attribute 값을 설정한다.
	 * @param examSparetime int
	 */
	public void setExamSparetime(int examSparetime) {
		this.examSparetime = examSparetime;
	}

	/**
	 * examIp attribute 를 리턴한다.
	 * @return String
	 */
	public String getExamIp() {
		return examIp;
	}
	/**
	 * examIp attribute 값을 설정한다.
	 * @param examIp String
	 */
	public void setExamIp(String examIp) {
		this.examIp = examIp;
	}

}
