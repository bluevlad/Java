package egovframework.com.academy.box.service;

import java.io.Serializable;

/**
*
* 사물함정보 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2020.05.21			rainend			사물함 정보 등록
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

	/** 사물함코드 */
	private String boxCd;
	/** 사물함이름 */
	private String boxNm;
	/** 사물함갯수 */
	private int boxCount;
    /** 사물함대여금 */
	private int boxPrice;
	/** 예치금 */
    private int deposit;
    /** 층수 */
    private int highNum;
	/** 가로갯수 */
    private int rowNum;
	/** 사용갯수 */
    private int useNum;
	/** 예약가능갯수 */
    private int reqNum;
	/** 사물함 시작번호 */
    private int startNum;
    /** 사물함 종료번호 */
    private int endNum;
    /** 등록일시 */
    private String regDt;
    /** 등록자 */
    private String regId;
    /** 수정일시 */
    private String updDt;
    /** 수정자 */
    private String updId;
    /** 사용자ID */
    private String userId;
    /** 사물함상태 */
    private String boxFlag;
    /** 사물함대여번호 */
    private int rentSeq;
    /** 사물함대여시메모 */
    private String rentMemo;
    /** 사물함번호 */
    private int boxNum;
    /** 사물함신청번호 */
    private int seq;
    /** 대여시작일 */
    private String rentStart;
    /** 대여종료일 */
    private String rentEnd;
    /** 예치금반환여부 */
    private String depositYn;
    /** 연장여부 */
    private String extendYn;
    /** 키반납여부 */
    private String keyYn;
    /** 신청구분 */
    private String rentType;
    /** 결제구분 */
    private String payGubun;
    /** 주문번호 */
    private String orderId;
    
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
	 * boxCd attribute 를 리턴한다.
	 * @return int
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
	 * Deposit attribute 값을 설정한다.
	 * @param deposit int
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	/**
	 * highNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getHighNum() {
		return highNum;
	}
	/**
	 * highNum attribute 값을 설정한다.
	 * @param highNum int
	 */
	public void setHighNum(int highNum) {
		this.highNum = highNum;
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
	 * @param rowNum int
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * useNum attribute 를 리턴한다.
	 * @return String
	 */
	public int getUseNum() {
		return useNum;
	}
	/**
	 * useNum attribute 값을 설정한다.
	 * @param useNum int
	 */
	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}

	/**
	 * reqNum attribute 를 리턴한다.
	 * @return String
	 */
	public int getReqNum() {
		return reqNum;
	}
	/**
	 * reqNum attribute 값을 설정한다.
	 * @param reqNum int
	 */
	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
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
	 * @param endNum String
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
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
	 * updDt attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdDt() {
		return updDt;
	}
	/**
	 * updDt attribute 값을 설정한다.
	 * @param updDt String
	 */
	public void setUpdDt(String updDt) {
		this.regDt = updDt;
	}

	/**
	 * updId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * updId attribute 값을 설정한다.
	 * @param updId String
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
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
	 * @param userId int
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return String
	 */
	public int getRentSeq() {
		return rentSeq;
	}
	/**
	 * rentSeq attribute 값을 설정한다.
	 * @param rentSeq String
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
	 * boxNum attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoxNum() {
		return boxNum;
	}
	/**
	 * boxNum attribute 값을 설정한다.
	 * @param boxNum String
	 */
	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}
	
	/**
	 * seq attribute 를 리턴한다.
	 * @return int
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * seq attribute 값을 설정한다.
	 * @param seq String
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	/**
	 * rentStart attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentStart() {
		return rentStart;
	}
	/**
	 * rentStart attribute 값을 설정한다.
	 * @param rentStart String
	 */
	public void setRentStart(String rentStart) {
		this.rentStart = rentStart;
	}
	
	/**
	 * rentEnd attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentEnd() {
		return rentEnd;
	}
	/**
	 * rentEnd attribute 값을 설정한다.
	 * @param rentEnd String
	 */
	public void setRentEnd(String rentEnd) {
		this.rentEnd = rentEnd;
	}
	
	/**
	 * depositYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getDepositYn() {
		return depositYn;
	}
	/**
	 * depositYn attribute 값을 설정한다.
	 * @param depositYn String
	 */
	public void setDepositYn(String depositYn) {
		this.depositYn = depositYn;
	}
	
	/**
	 * extendYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getExtendYn() {
		return extendYn;
	}
	/**
	 * extendYn attribute 값을 설정한다.
	 * @param extendYn String
	 */
	public void setExtendYn(String extendYn) {
		this.extendYn = extendYn;
	}
	
	/**
	 * keyYn attribute 를 리턴한다.
	 * @return String
	 */
	public String getKeyYn() {
		return keyYn;
	}
	/**
	 * keyYn attribute 값을 설정한다.
	 * @param keyYn String
	 */
	public void setKeyYn(String keyYn) {
		this.keyYn = keyYn;
	}
	
	/**
	 * rentType attribute 를 리턴한다.
	 * @return String
	 */
	public String getRentType() {
		return rentType;
	}
	/**
	 * rentType attribute 값을 설정한다.
	 * @param rentType String
	 */
	public void setRentType(String rentType) {
		this.rentType = rentType;
	}
	
	/**
	 * payGubun attribute 를 리턴한다.
	 * @return String
	 */
	public String getPayGubun() {
		return payGubun;
	}
	/**
	 * payGubun attribute 값을 설정한다.
	 * @param payGubun String
	 */
	public void setPayGubun(String payGubun) {
		this.payGubun = payGubun;
	}
	
	/**
	 * orderId attribute 를 리턴한다.
	 * @return String
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * orderId attribute 값을 설정한다.
	 * @param orderId String
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
