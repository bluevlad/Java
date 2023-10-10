package egovframework.com.academy.box.service;

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

public class BoxVO implements Serializable {

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

	/** 사물함코드 */
	private String boxCd;
	/** 사물함명칭 */
	private String boxNm;
	/** 사물함갯수 */
	private int boxCount;
	/** 사물함사용금액 */
	private int boxPrice;
	/** 예치금 */
	private int deposit;
	/** 위치층수 */
	private int rowCount;
	/** 배열갯수 */
	private int rowNum;
	/** 시작번호 */
	private int startNum;
	/** 종료번호 */
	private int endNum;
	/** 사용갯수 */
	private int useCnt;
	/** 미사용갯수 */
	private int notCnt;

	/** 사물함번호 */
	private int boxNum;
	/** 사물함상태 */
	private String boxFlag;
	/** 사물함대여번호 */
	private int rentSeq;
	/** 사물함대여메모 */
	private String rentMemo;
    /** 대여자ID */
    private String userId;
    /** 대여자ID */
    private String userNm;
    /** 주문번호 */
    private String orderno;

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
	 * boxCd attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxCd() {
		return boxCd;
	}
	/**
	 * boxCd attribute 값을 설정한다.
	 * @param boxCd String
	 */
	public void setBoxCd(String boxCd) {
		this.boxCd = boxCd;
	}
	
	/**
	 * boxNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxNm() {
		return boxNm;
	}
	/**
	 * boxNm attribute 값을 설정한다.
	 * @param boxNm String
	 */
	public void setBoxNm(String boxNm) {
		this.boxNm = boxNm;
	}
	
	/**
	 * boxCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxCount() {
		return boxCount;
	}
	/**
	 * boxCount attribute 값을 설정한다.
	 * @param boxCount int
	 */
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	
	/**
	 * boxPrice attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxPrice() {
		return boxPrice;
	}
	/**
	 * boxPrice attribute 값을 설정한다.
	 * @param boxPrice int
	 */
	public void setBoxPrice(int boxPrice) {
		this.boxPrice = boxPrice;
	}
	
	/**
	 * deposit attribute 를 리턴한다.
	 * @return int
	 */
	public int getDeposit() {
		return deposit;
	}
	/**
	 * deposit attribute 값을 설정한다.
	 * @param deposit int
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	/**
	 * rowCount attribute 를 리턴한다.
	 * @return int
	 */
	public int getRowCount() {
		return rowCount;
	}
	/**
	 * rowCount attribute 값을 설정한다.
	 * @param rowCount String
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * rowNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * rowNum attribute 값을 설정한다.
	 * @param rowNum String
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * startNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * startNum attribute 값을 설정한다.
	 * @param startNum String
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * endNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * endNum attribute 값을 설정한다.
	 * @param endNum int
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * useCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getUseCnt() {
		return useCnt;
	}
	/**
	 * useCnt attribute 값을 설정한다.
	 * @param useCnt int
	 */
	public void setUseCnt(int useCnt) {
		this.useCnt = useCnt;
	}
	/**
	 * notCnt attribute 를 리턴한다.
	 * @return int
	 */
	public int getNotCnt() {
		return notCnt;
	}
	/**
	 * notCnt attribute 값을 설정한다.
	 * @param notCnt int
	 */
	public void setNotCnt(int notCnt) {
		this.notCnt = notCnt;
	}
	
	/**
	 * boxNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxNum() {
		return boxNum;
	}
	/**
	 * boxNum attribute 값을 설정한다.
	 * @param boxNum int
	 */
	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}
	/**
	 * boxFlag attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoxFlag() {
		return boxFlag;
	}
	/**
	 * boxFlag attribute 값을 설정한다.
	 * @param boxFlag String
	 */
	public void setBoxFlag(String boxFlag) {
		this.boxFlag = boxFlag;
	}
	/**
	 * rentSeq attribute 를 리턴한다.
	 * @return int
	 */
	public int getRentSeq() {
		return rentSeq;
	}
	/**
	 * rentSeq attribute 값을 설정한다.
	 * @param rentSeq int
	 */
	public void setRentSeq(int rentSeq) {
		this.rentSeq = rentSeq;
	}
	/**
	 * rentMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentMemo() {
		return rentMemo;
	}
	/**
	 * rentMemo attribute 값을 설정한다.
	 * @param rentMemo String
	 */
	public void setRentMemo(String rentMemo) {
		this.rentMemo = rentMemo;
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
	 * @param userNm String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * orderno attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderno() {
		return orderno;
	}
	/**
	 * orderno attribute 값을 설정한다.
	 * @param orderno String
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}
